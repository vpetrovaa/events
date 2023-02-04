package com.solvd.laba.events.web.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public class EventMapper {
}
