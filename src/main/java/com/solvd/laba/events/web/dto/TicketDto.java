package com.solvd.laba.events.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.events.web.dto.validation.OnCreate;
import com.solvd.laba.events.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDto {

    @NotNull(message = "Id cannot be null", groups = {OnUpdate.class})
    private Long id;

    @NotNull(message = "User cannot be null", groups = {OnCreate.class, OnUpdate.class})
    private UserDto user;

    @NotNull(message = "Event cannot be null", groups = {OnCreate.class, OnUpdate.class})
    private EventDto event;

    @NotNull(message = "Amount cannot be null", groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 1, max = 30, message = "Amount must be between 1 and 30", groups = {OnCreate.class, OnUpdate.class})
    private Integer amount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal price;

}
