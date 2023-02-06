package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findAllByUserId(Long id);

}
