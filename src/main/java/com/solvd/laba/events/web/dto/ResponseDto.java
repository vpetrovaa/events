package com.solvd.laba.events.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {

    private List<String> errors;

}
