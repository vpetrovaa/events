package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.web.dto.TicketDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EventMapper.class, UserMapper.class})
public interface TicketMapper {

    Ticket dtoToEntity(TicketDto ticketDto);

    TicketDto entityToDto(Ticket ticket);

}
