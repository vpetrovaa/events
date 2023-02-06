package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.criteria.EventCriteria;
import com.solvd.laba.events.service.EventService;
import com.solvd.laba.events.web.dto.EventDto;
import com.solvd.laba.events.web.dto.criteria.EventCriteriaDto;
import com.solvd.laba.events.web.mapper.EventMapper;
import com.solvd.laba.events.web.mapper.criteria.EventCriteriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;
    private final EventCriteriaMapper eventCriteriaMapper;

    @GetMapping
    public List<EventDto> findByCriteria(EventCriteriaDto criteriaDto) {
        EventCriteria criteria = eventCriteriaMapper.dtoToEntity(criteriaDto);
        List<Event> events = eventService.findByCriteria(criteria);
        return eventMapper.entityToDto(events);
    }

}
