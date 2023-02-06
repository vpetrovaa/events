package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Ticket;

import java.util.List;

public interface TicketService {

    Ticket create(Ticket ticket);

    List<Ticket> findAllByUserId(Long id);

}
