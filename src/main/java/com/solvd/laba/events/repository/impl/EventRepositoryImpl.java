package com.solvd.laba.events.repository.impl;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void create(Event event) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<Event> findById(Long id) {
        Session session = sessionFactory.openSession();
        Query<Event> query = session.createQuery("From Event where id=:id", Event.class);
        query.setParameter("id", id);
        Event event = query.uniqueResult();
        return Optional.of(event);
    }

    @Override
    public void updateStatus(Event event) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

}
