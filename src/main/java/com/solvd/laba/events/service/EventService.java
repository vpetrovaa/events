package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.criteria.EventCriteria;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    Event create(Event event);

    Event publish(Event event);

    Event findById(Long id);

    List<Event> findAll(int currentPage, EventCriteria criteria);

    List<Event> findByCriteria(EventCriteria criteria);

    Event reschedule(Long id, LocalDateTime newDate);

    void delete(Long id);

}
