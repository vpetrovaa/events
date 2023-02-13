package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.service.TicketService;
import com.solvd.laba.events.web.dto.TicketDto;
import com.solvd.laba.events.web.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @GetMapping("/{id}")
    public TicketDto getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.findById(id);
        return ticketMapper.toDto(ticket);
    }

    @DeleteMapping("/{id}")
    public void deleteTicketById(@PathVariable Long id) {
        ticketService.deleteById(id);
    }

}
