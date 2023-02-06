package com.solvd.laba.events.repository;

import com.solvd.laba.events.domain.Event;

import java.util.List;

public interface EventRepository {

    List<Event> getAllEvents();

}
