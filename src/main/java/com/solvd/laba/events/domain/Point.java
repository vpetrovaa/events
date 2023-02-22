package com.solvd.laba.events.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Point {

    private Double latitude;
    private Double longitude;

}
