package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Event;

public interface EventService {

    Event create(Event event);

    Event findById(Long id);

    Event updateStatus(String status, Long id);

}
