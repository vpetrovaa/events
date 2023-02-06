package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.criteria.EventCriteria;

import java.util.List;

public interface EventService {

    Event create(Event event);

    Event findById(Long id);

    Event updateStatus(String status, Long id);

    List<Event> findByCriteria(EventCriteria criteria);

}
