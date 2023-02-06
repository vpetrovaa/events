package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.service.TicketService;
import com.solvd.laba.events.web.dto.TicketDto;
import com.solvd.laba.events.web.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketContoller {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @PostMapping
    public TicketDto create(@RequestBody TicketDto ticketDto){
        Ticket ticket = ticketMapper.dtoToEntity(ticketDto);
        ticket = ticketService.create(ticket);
        return ticketMapper.entityToDto(ticket);
    }

}
