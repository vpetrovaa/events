package com.solvd.laba.events.domain;

import jakarta.persistence.*;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

    private BigDecimal price;

    private Type type;

    private String topic;

    private String country;

    private String city;

    private Point latitude;

    private Point longitude;

    private Status status;

    @OneToMany(mappedBy = "event")
    private Set<Ticket> tickets;

    public enum Status {

        ACTIVE,
        RESCHEDULED,
        CANCELED,
        FINISHED,
        DRAFT

    }

    public enum Type {

        CONFERENCES,
        TRADES,
        NETWORKING,
        CHARITY,
        FESTIVALS,
        SPORTS

    }

}
