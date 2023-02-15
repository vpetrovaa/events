package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.service.EmailService;
import com.solvd.laba.events.service.property.EmailProperties;
import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final Configuration configuration;
    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;

    @Override
    @SneakyThrows
    public void sendActivationEmail(User user, Map<String, Object> params) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Verifying Events account");
        helper.setTo(user.getEmail());
        String emailContent = getActivationEmailContent(user, params);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @Override
    @SneakyThrows
    public String getActivationEmailContent(User user, Map<String, Object> params) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("activation", emailProperties.getActivate() + params.get("token"));
        configuration.getTemplate("activation.ftlh")
                .process(model, stringWriter);
        return stringWriter.getBuffer()
                .toString();
    }

    @Override
    @SneakyThrows
    public void sendResetTokenEmail(User user, Map<String, Object> params) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Reset password of your account");
        helper.setTo(user.getEmail());
        String emailContent = getResetTokenEmailContent(user, params);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @Override
    @SneakyThrows
    public String getResetTokenEmailContent(User user, Map<String, Object> params) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("reset", emailProperties.getReset() + params.get("token"));
        configuration.getTemplate("reset.ftlh")
                .process(model, stringWriter);
        return stringWriter.getBuffer()
                .toString();
    }

    @Override
    @SneakyThrows
    public void sendRescheduledEventEmail(User user, Map<String, Object> params, Event event) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Rescheduling of event");
        helper.setTo(user.getEmail());
        String emailContent = getRescheduledEventEmailContent(user, params, event);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @Override
    @SneakyThrows
    public String getRescheduledEventEmailContent(User user, Map<String, Object> params, Event event) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("event", event);
        configuration.getTemplate("reschedule.ftlh")
                .process(model, stringWriter);
        return stringWriter.getBuffer()
                .toString();
    }

    @Override
    @SneakyThrows
    public void sendDeletedEventEmail(User user, Map<String, Object> params, Event event) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Deleting of event");
        helper.setTo(user.getEmail());
        String emailContent = getDeletedEventEmailContent(user, params, event);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @Override
    @SneakyThrows
    public String getDeletedEventEmailContent(User user, Map<String, Object> params, Event event) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("event", event);
        configuration.getTemplate("delete.ftlh")
                .process(model, stringWriter);
        return stringWriter.getBuffer()
                .toString();
    }

    @Override
    @SneakyThrows
    public void sendTicketReminderEmail(User user, Map<String, Object> params, List<Ticket> tickets) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Reminder of tickets");
        helper.setTo(user.getEmail());
        String emailContent = getTicketReminderEmailContent(user, params, tickets);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @Override
    @SneakyThrows
    public String getTicketReminderEmailContent(User user, Map<String, Object> params, List<Ticket> tickets) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("tickets", tickets);
        configuration.getTemplate("reminder.ftlh")
                .process(model, stringWriter);
        return stringWriter.getBuffer()
                .toString();
    }

}
