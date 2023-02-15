package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.exception.ResourceDoesNotExistException;
import com.solvd.laba.events.repository.TicketRepository;
import com.solvd.laba.events.service.EventService;
import com.solvd.laba.events.service.TicketService;
import com.solvd.laba.events.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    @PersistenceContext
    private EntityManager entityManager;

    private final TicketRepository ticketRepository;
    private final EventService eventService;
    private final UserService userService;

    @Override
    public List<Ticket> findAll(Long userId, LocalDateTime currentDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder.createQuery(Ticket.class);
        Root<Ticket> ticketRoot = criteriaQuery.from(Ticket.class);
        List<Predicate> predicates = new ArrayList<>();

        if(userId != null){
            Predicate userPredicate = criteriaBuilder.equal(ticketRoot.get("user").<Long>get("id"), userId);
            predicates.add(userPredicate);
        }

        if (currentDate != null) {
            LocalDateTime startOfTheDay = currentDate.plusHours(12);
            LocalDateTime endOfTheDay = currentDate.plusHours(36);
            Predicate startOfTheDayPredicate = criteriaBuilder.greaterThanOrEqualTo(ticketRoot.get("event").get("eventTime"), startOfTheDay);
            predicates.add(startOfTheDayPredicate);
            Predicate endOfTheDayPredicate = criteriaBuilder.lessThanOrEqualTo(ticketRoot.get("event").get("eventTime"), endOfTheDay);
            predicates.add(endOfTheDayPredicate);
        }

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        criteriaQuery.where(finalPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket create(Long userId, Long eventId, Integer amount) {
        Ticket ticket = new Ticket();
        Event event = eventService.findById(eventId);
        ticket.setAmount(amount);
        ticket.setEvent(event);
        ticket.setUser(userService.findById(userId));
        ticket.setPrice(event.getPrice());
        ticket = ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesNotExistException("There are no ticket with id" + id));
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

}
