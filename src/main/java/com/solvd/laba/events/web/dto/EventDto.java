package com.solvd.laba.events.web.dto;

import com.solvd.laba.events.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDto {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime eventTime;
    private BigDecimal price;
    private Event.Type type;
    private String topic;
    private String country;
    private String city;
    private Point latitude;
    private Point longitude;
    private Event.Status status;

}
