package com.solvd.laba.events.repository;

import com.solvd.laba.events.domain.Ticket;

import java.util.List;

public interface TicketRepository {

    void create(Ticket ticket);

    List<Ticket> findAllByUserId(Long id);

}
