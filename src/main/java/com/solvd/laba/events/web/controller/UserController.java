package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.service.TicketService;
import com.solvd.laba.events.service.UserService;
import com.solvd.laba.events.web.dto.PasswordDto;
import com.solvd.laba.events.web.dto.TicketDto;
import com.solvd.laba.events.web.dto.UserDto;
import com.solvd.laba.events.web.mapper.TicketMapper;
import com.solvd.laba.events.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TicketService ticketService;
    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return userMapper.toDto(user);
    }

    @PutMapping("/{userId}/password")
    public void changePassword(@PathVariable Long userId,
                               @RequestBody PasswordDto passwordDto) {
        userService.updatePassword(passwordDto.getNewPassword(), passwordDto.getOldPassword(), userId);
    }

    @GetMapping("/{userId}/tickets")
    public List<TicketDto> getUserTickets(@PathVariable Long userId,
                                          @RequestParam LocalDateTime currentDate) {
        List<Ticket> tickets = ticketService.findAll(userId, currentDate);
        return ticketMapper.toDto(tickets);
    }

    @PostMapping("/{userId}/tickets/{eventId}")
    public TicketDto addTicket(@PathVariable Long userId,
                               @PathVariable Long eventId,
                               @RequestParam(required = false) Integer amount) {
        Ticket ticket = ticketService.create(userId, eventId, amount);
        return ticketMapper.toDto(ticket);
    }

}
