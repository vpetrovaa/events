package com.solvd.laba.events.domain.criteria;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventCriteria {

    private String city;
    private Point userLocation;
    private Integer maxRadius;
    private Event.Type type;
    private BigDecimal maxPrice;
    private String topic;

}
