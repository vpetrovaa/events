package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.repository.TicketRepository;
import com.solvd.laba.events.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> findByUser(User user) {
        List<Ticket> tickets = ticketRepository.findByUser(user);
        Event event = tickets.get(0).getEvent();
        return tickets;
    }

}
