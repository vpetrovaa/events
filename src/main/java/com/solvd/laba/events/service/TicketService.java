package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.User;

import java.util.List;

public interface TicketService {

    List<Ticket> findByUser(User user);

}
