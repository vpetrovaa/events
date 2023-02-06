package com.solvd.laba.events.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String topic;

    private String country;

    private String city;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "longitude"))
    })
    private Point coordinates;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
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
