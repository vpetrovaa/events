package com.solvd.laba.events.repository.impl;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<Event> getAllEvents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Event", Event.class).getResultList();
        }
    }


}
