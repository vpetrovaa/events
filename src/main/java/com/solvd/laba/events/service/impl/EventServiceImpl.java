package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.criteria.EventCriteria;
import com.solvd.laba.events.domain.exception.IllegalTimeException;
import com.solvd.laba.events.domain.exception.ResourceDoesNotExistException;
import com.solvd.laba.events.repository.EventRepository;
import com.solvd.laba.events.service.EmailService;
import com.solvd.laba.events.service.EventService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private static final int PAGE_SIZE = 20;

    @PersistenceContext
    private EntityManager entityManager;

    private final EventRepository eventRepository;
    private final EmailService emailService;

    @Override
    public Event create(Event event) {
        eventRepository.save(event);
        return event;
    }

    @Override
    public Event publish(Event event) {
        event.setStatus(Event.Status.PUBLISHED);
        eventRepository.save(event);
        return event;
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesNotExistException("Event with id " + id + " does not exist"));
    }

    @Override
    public List<Event> findAll(int currentPage, EventCriteria criteria) {
        List<Event> events;
        List<Event> eventsPaged;
        if (criteria != null) {
            events = findByCriteria(criteria);
        } else {
            events = eventRepository.findAll();
        }
        int startItem = currentPage * PAGE_SIZE;
        if (events.size() < startItem) {
            eventsPaged = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + PAGE_SIZE, events.size());
            eventsPaged = events.subList(startItem, toIndex);
        }
        return eventsPaged;
    }

    @Override
    public List<Event> findByCriteria(EventCriteria criteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        List<Predicate> predicates = new ArrayList<>();

        List<String> cities = criteria.getCities();
        List<Predicate> cityPredicates = new ArrayList<>();
        if (cities != null && !cities.isEmpty()) {
            for (String city : cities) {
                cityPredicates.add(criteriaBuilder.equal(eventRoot.get("country"), city));
            }
            Predicate cityFinalPredicate = criteriaBuilder.or(cityPredicates.toArray(new Predicate[0]));
            predicates.add(cityFinalPredicate);
        }

        if (userLocationAndMaxRadiusNotNull(criteria)) {
            Expression<Double> distance = criteriaBuilder.function("calculate_distance", Double.class,
                    eventRoot.get("coordinates").get("latitude"),
                    eventRoot.get("coordinates").get("longitude"),
                    criteriaBuilder.literal(criteria.getUserLocation().getLatitude()),
                    criteriaBuilder.literal(criteria.getUserLocation().getLongitude())
            );
            Predicate userLocationPredicate = criteriaBuilder.lessThanOrEqualTo(distance, criteria.getMaxRadius());
            predicates.add(userLocationPredicate);
        }

        List<Event.Type> types = criteria.getTypes();
        List<Predicate> typePredicates = new ArrayList<>();
        if (types != null && !types.isEmpty()) {
            for (Event.Type type : types) {
                typePredicates.add(criteriaBuilder.equal(eventRoot.get("type"), type));
            }
            Predicate typeFinalPredicate = criteriaBuilder.or(typePredicates.toArray(new Predicate[0]));
            predicates.add(typeFinalPredicate);
        }

        BigDecimal minPrice = criteria.getMinPrice();
        if (minPrice != null) {
            Predicate minPricePredicate = criteriaBuilder.greaterThanOrEqualTo(eventRoot.get("price"), minPrice);
            predicates.add(minPricePredicate);
        }

        BigDecimal maxPrice = criteria.getMaxPrice();
        if (maxPrice != null) {
            Predicate maxPricePredicate = criteriaBuilder.lessThanOrEqualTo(eventRoot.get("price"), maxPrice);
            predicates.add(maxPricePredicate);
        }

        LocalDateTime minDate = criteria.getMinDate();
        if (minDate != null) {
            Predicate minDatePredicate = criteriaBuilder.greaterThanOrEqualTo(eventRoot.get("eventTime"), minDate);
            predicates.add(minDatePredicate);
        }

        List<String> topics = criteria.getTopics();
        List<Predicate> topicPredicates = new ArrayList<>();
        if (topics != null && !topics.isEmpty()) {
            for (String topic : topics) {
                topicPredicates.add(criteriaBuilder.equal(eventRoot.get("topic"), topic));
            }
            Predicate topicFinalPredicate = criteriaBuilder.or(topicPredicates.toArray(new Predicate[0]));
            predicates.add(topicFinalPredicate);
        }

        Order eventTimeOrder = criteriaBuilder.asc(eventRoot.get("eventTime"));

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        criteriaQuery.where(finalPredicate);
        criteriaQuery.orderBy(eventTimeOrder);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Event reschedule(Long id, LocalDateTime newDate) {
        Event event = findById(id);
        if (newDate.isBefore(LocalDateTime.now())) {
            throw new IllegalTimeException("Chosen date was in past");
        }
        event.setEventTime(newDate);
        eventRepository.save(event);
        Map<String, Object> params = new HashMap<>();
        event.getTickets().stream()
                .map(Ticket::getUser)
                .forEach(u -> emailService.sendRescheduledEventEmail(u, params, event));
        return event;
    }

    @Override
    public Event updateImages(Long id, String filename) {
        Event event = findById(id);
        Set<String> images = event.getImages();
        images.add(filename);
        event.setImages(images);
        return eventRepository.save(event);
    }

    @Override
    public void delete(Long id) {
        Event event = findById(id);
        Map<String, Object> params = new HashMap<>();
        event.getTickets().stream()
                .map(Ticket::getUser)
                .forEach(u -> emailService.sendDeletedEventEmail(u, params, event));
        eventRepository.deleteById(id);
    }

    private boolean userLocationAndMaxRadiusNotNull(EventCriteria eventCriteria) {
        return eventCriteria.getUserLocation() != null && eventCriteria.getUserLocation().getLatitude() != null
                && eventCriteria.getUserLocation().getLongitude() != null && eventCriteria.getMaxRadius() != null;
    }

}
