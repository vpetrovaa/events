package com.solvd.laba.events.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Point;
import com.solvd.laba.events.web.dto.validation.OnCreate;
import com.solvd.laba.events.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventDto {

    @NotNull(message = "Id is required", groups = {OnUpdate.class})
    private Long id;

    @NotNull(message = "Name is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 45, message = "Name must be between 3 and 45 characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(message = "Description is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 1000, message = "Description must be between 3 and 1000 characters", groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @NotNull(message = "Event time is required", groups = {OnCreate.class, OnUpdate.class})
    private LocalDateTime eventTime;

    @NotNull(message = "Price is required", groups = {OnCreate.class, OnUpdate.class})
    @Min(value = 0, message = "Price must be greater than 0", groups = {OnCreate.class, OnUpdate.class})
    private BigDecimal price;

    @NotNull(message = "Type is required", groups = {OnCreate.class, OnUpdate.class})
    private Event.Type type;

    @NotNull(message = "Topic is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 45, message = "Topic must be between 3 and 45 characters", groups = {OnCreate.class, OnUpdate.class})
    private String topic;

    @NotNull(message = "Country is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 45, message = "Country must be between 3 and 45 characters", groups = {OnCreate.class, OnUpdate.class})
    private String country;

    @NotNull(message = "City is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 45, message = "City must be between 3 and 45 characters", groups = {OnCreate.class, OnUpdate.class})
    private String city;

    @NotNull(message = "Coordinates is required", groups = {OnCreate.class, OnUpdate.class})
    private Point coordinates;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Event.Status status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> images;

}
