package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.criteria.EventCriteria;

import java.util.List;

public interface EventService {

    List<Event> findByCriteria(EventCriteria criteria);

    List<Event> findByCriteria(EventCriteria criteria);

}
