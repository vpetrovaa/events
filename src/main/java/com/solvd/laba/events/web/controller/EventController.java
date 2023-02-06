package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.criteria.EventCriteria;
import com.solvd.laba.events.service.EventService;
import com.solvd.laba.events.web.dto.EventDto;
import com.solvd.laba.events.web.dto.criteria.EventCriteriaDto;
import com.solvd.laba.events.web.mapper.EventMapper;
import com.solvd.laba.events.web.mapper.criteria.EventCriteriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;
    private final EventCriteriaMapper eventCriteriaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto create(@RequestBody EventDto eventDto) {
        Event event = eventMapper.dtoToEntity(eventDto);
        event = eventService.create(event);
        return eventMapper.entityToDto(event);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto updateStatus(@PathVariable(name = "id") Long id, @RequestParam("status") String status) {
        Event event = eventService.updateStatus(status, id);
        return eventMapper.entityToDto(event);
    }

    @GetMapping
    public List<EventDto> findByCriteria(EventCriteriaDto criteriaDto) {
        EventCriteria criteria = eventCriteriaMapper.dtoToEntity(criteriaDto);
        List<Event> events = eventService.findByCriteria(criteria);
        return eventMapper.entityToDto(events);
    }

}
