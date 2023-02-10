package com.solvd.laba.events.domain.criteria;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventCriteria {

    private List<String> cities;
    private Point userLocation;
    private Integer maxRadius;
    private List<Event.Type> types;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private List<String> topics;
    private LocalDateTime minDate;

}
