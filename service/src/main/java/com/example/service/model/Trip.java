package com.example.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long tripId;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TripReason tripReason;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TripStatus tripStatus;

    @Column(nullable = false)
    private LocalDate departureDate;

    @Column(nullable = false)
    private LocalDate arrivalDate;


    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id)"))
    private User user;

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", tripReason=" + tripReason +
                ", description='" + description + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", tripStatus=" + tripStatus +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
