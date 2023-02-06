package com.solvd.laba.events.web.dto.criteria;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Point;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class EventCriteriaDto {

    private String city;
    private Point userLocation;
    private Integer maxRadius;
    private Event.Type type;
    private BigDecimal maxPrice;
    private String topic;

}
