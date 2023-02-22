package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.web.dto.TicketDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    Ticket toEntity(TicketDto ticketDto);

    TicketDto toDto(Ticket ticket);

    List<TicketDto> toDto(List<Ticket> tickets);

}
