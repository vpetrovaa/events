package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.web.dto.EventDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto toDto(Event event);

    List<EventDto> toDto(List<Event> events);

    Event toEntity(EventDto eventDto);

}
