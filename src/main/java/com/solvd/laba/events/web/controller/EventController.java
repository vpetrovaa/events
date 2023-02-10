package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.criteria.EventCriteria;
import com.solvd.laba.events.service.EventService;
import com.solvd.laba.events.web.dto.EventDto;
import com.solvd.laba.events.web.dto.criteria.EventCriteriaDto;
import com.solvd.laba.events.web.mapper.EventMapper;
import com.solvd.laba.events.web.mapper.criteria.EventCriteriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;
    private final EventCriteriaMapper eventCriteriaMapper;

    @GetMapping
    public List<EventDto> findAll(@RequestParam(required = false) Integer currentPage,
                                  @RequestBody EventCriteriaDto criteriaDto) {
        EventCriteria criteria = eventCriteriaMapper.toEntity(criteriaDto);
        List<Event> events = eventService.findAll(currentPage, criteria);
        return eventMapper.toDto(events);
    }

    @PostMapping
    public EventDto saveDraft(@RequestBody EventDto eventDto) {
        Event event = eventMapper.toEntity(eventDto);
        event = eventService.create(event);
        return eventMapper.toDto(event);
    }

    @GetMapping("/{id}")
    public EventDto findById(@PathVariable Long id) {
        Event event = eventService.findById(id);
        return eventMapper.toDto(event);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventService.delete(id);
    }

    @PostMapping("/publish")
    public EventDto publish(@RequestBody EventDto eventDto) {
        Event event = eventMapper.toEntity(eventDto);
        event = eventService.publish(event);
        return eventMapper.toDto(event);
    }

    @PostMapping("/{id}/reschedule")
    public EventDto reschedule(@PathVariable Long id,
                               @RequestParam LocalDateTime newDate) {
        Event event = eventService.reschedule(id, newDate);
        return eventMapper.toDto(event);
    }

}
