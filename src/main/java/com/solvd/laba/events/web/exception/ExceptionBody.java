package com.solvd.laba.events.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionBody {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

}
