package com.solvd.laba.events.repository;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.criteria.EventCriteria;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    void create(Event event);

    Optional<Event> findById(Long id);

    void updateStatus(Event event);

    List<Event> findByCriteria(EventCriteria criteria);

}
