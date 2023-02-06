package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.web.dto.EventDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface EventMapper {

    EventDto entityToDto(Event event);

    List<EventDto> entityToDto(List<Event> events);

    Event dtoToEntity(EventDto eventDto);

}
