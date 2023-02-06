package com.solvd.laba.events.repository.impl;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void create(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Ticket> findAllByUserId(Long id) {
        try(Session session = sessionFactory.openSession()) {
            Query<Ticket> query = session.createQuery("From Ticket where user.id=:id", Ticket.class);
            query.setParameter("id", id);
            return query.list();
        }
    }

}
