package com.solvd.laba.events.web.dto.criteria;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Point;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventCriteriaDto {

    private List<String> cities;
    private Point userLocation;
    private Integer maxRadius;
    private List<Event.Type> types;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private List<String> topics;
    private LocalDateTime minDate;

}
