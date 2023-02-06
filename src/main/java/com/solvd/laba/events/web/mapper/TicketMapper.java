package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.web.dto.TicketDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EventMapper.class, UserMapper.class})
public interface TicketMapper {

    List<TicketDto> entitiesToDto(List<Ticket> tickets);

}
