package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {

    Ticket create(Long userId, Long eventId, Integer amount);

    List<Ticket> findAll(Long userId, LocalDateTime currentDate);

    Ticket findById(Long id);

    void deleteById(Long id);

}
