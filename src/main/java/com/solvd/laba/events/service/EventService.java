package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.criteria.EventCriteria;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    Event create(Event event);

    Event publish(Event event);

    Event findById(Long id);

    List<Event> findAll(EventCriteria criteria, int currentPage);

    Event reschedule(Long id, LocalDateTime newDate);

    Event updateImages(Long id, String filename);

    void delete(Long id);

}
