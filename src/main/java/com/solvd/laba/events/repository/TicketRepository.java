package com.solvd.laba.events.repository;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.User;

import java.util.List;

public interface TicketRepository {

    List<Ticket> findByUser(User user);

}
