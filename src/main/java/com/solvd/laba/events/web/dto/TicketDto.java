package com.solvd.laba.events.web.dto;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDto {

    private Long id;
    private User user;
    private Event event;
    private Integer amount;
    private BigDecimal price;

}
