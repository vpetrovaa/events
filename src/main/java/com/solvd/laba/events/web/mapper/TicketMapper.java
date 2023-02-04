package com.solvd.laba.events.web.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EventMapper.class, UserMapper.class})
public class TicketMapper {
}
