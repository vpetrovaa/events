package com.solvd.laba.events.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime eventTime;

    private BigDecimal price;

    private Event.Type type;

    private String topic;

    private String country;

    private String city;

    private Point coordinates;

    private Event.Status status;

}
