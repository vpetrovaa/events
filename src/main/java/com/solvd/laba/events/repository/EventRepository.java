package com.solvd.laba.events.repository;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.criteria.EventCriteria;

import java.util.List;

public interface EventRepository {

    List<Event> findByCriteria(EventCriteria criteria);

}
