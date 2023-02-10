package com.solvd.laba.events.repository;

import com.solvd.laba.events.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
