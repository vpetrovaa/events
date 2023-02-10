package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.repository.TicketRepository;
import com.solvd.laba.events.service.TicketService;
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

    @Override
    public List<Ticket> findAll(Long userId, LocalDateTime currentDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder.createQuery(Ticket.class);
        Root<Ticket> ticketRoot = criteriaQuery.from(Ticket.class);
        List<Predicate> predicates = new ArrayList<>();

        Predicate userPredicate = criteriaBuilder.equal(ticketRoot.get("user").<Long>get("id"), userId);
        predicates.add(userPredicate);

        if (currentDate != null) {
            // TODO create startOfDay and endOfDay and add it co criteriaBuilder
        }

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        criteriaQuery.where(finalPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Ticket create(Long userId, Long eventId, Integer amount) {
        return null;
    }

    @Override
    public Ticket findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

}
