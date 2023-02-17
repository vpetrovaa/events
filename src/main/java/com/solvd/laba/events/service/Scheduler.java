package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final EmailService emailService;
    private final TicketService ticketService;

    @Scheduled(cron = "0 12 0 * * *")
    public void sendTicketInformation() {
        List<Ticket> tickets = ticketService.findAll();
        Map<String, Object> params = new HashMap<>();
//        Set<User> users = tickets.stream() TODO
//                .map(Ticket::getUser)
//                .collect(Collectors.toSet());
//        users.forEach(u -> emailService.sendTicketReminderEmail(u, params, ticketService.findAll(u.getId(), LocalDateTime.now())));
    }

}
