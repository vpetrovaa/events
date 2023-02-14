package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.service.EventService;
import com.solvd.laba.events.web.dto.EventDto;
import com.solvd.laba.events.web.dto.validation.OnCreate;
import com.solvd.laba.events.web.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping
    public EventDto saveDraft(@RequestBody EventDto eventDto) {
        Event event = eventMapper.toEntity(eventDto);
        event = eventService.create(event);
        return eventMapper.toDto(event);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventService.delete(id);
    }

    @PostMapping("/publish")
    public EventDto publish(@Validated(OnCreate.class) @RequestBody EventDto eventDto) {
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
