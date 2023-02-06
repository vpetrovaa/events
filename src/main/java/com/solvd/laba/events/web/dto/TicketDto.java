package com.solvd.laba.events.web.dto;

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
    private UserDto user;
    private EventDto event;
    private Integer amount;
    private BigDecimal price;

}
