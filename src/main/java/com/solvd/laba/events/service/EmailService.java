package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.User;

import java.util.List;
import java.util.Map;

public interface EmailService {

    void sendActivationEmail(User user, Map<String, Object> params);

    String getActivationEmailContent(User user, Map<String, Object> params);

    void sendResetTokenEmail(User user, Map<String, Object> params);

    String getResetTokenEmailContent(User user, Map<String, Object> params);

    void sendRescheduledEventEmail(User user, Map<String, Object> params, Event event);

    String getRescheduledEventEmailContent(User user, Map<String, Object> params, Event event);

    void sendDeletedEventEmail(User user, Map<String, Object> params, Event event);

    String getDeletedEventEmailContent(User user, Map<String, Object> params, Event event);

    void sendTicketReminderEmail(User user, Map<String, Object> params, List<Ticket> tickets);

    String getTicketReminderEmailContent(User user, Map<String, Object> params, List<Ticket> tickets);

}
