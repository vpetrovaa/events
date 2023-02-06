package com.solvd.laba.events.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
public class Point {

    private Double latitude;
    private Double longitude;

}
