package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.exception.ResourceDoesNotExistException;
import com.solvd.laba.events.repository.EventRepository;
import com.solvd.laba.events.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event create(Event event) {
        eventRepository.create(event);
        return event;
    }

    @Override
    public Event findById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesNotExistException("There are no event with id" + id));
        return event;
    }

    @Override
    public Event updateStatus(String status, Long id) {
        Event event = findById(id);
        event.setStatus(Event.Status.valueOf(status.toUpperCase()));
        eventRepository.updateStatus(event);
        return event;
    }

}
