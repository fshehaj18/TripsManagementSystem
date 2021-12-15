package com.example.service.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long flightId;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Column(nullable = false)
    private LocalDateTime arrivalDate;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Trip_Flight",
            joinColumns = {@JoinColumn(name = "flight_id")},
            inverseJoinColumns = {@JoinColumn(name = "trip_id")}
    )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Trip> trips;

    public void add(Trip trip) {
        if (trips == null)
            trips = new ArrayList<>();
        trips.add(trip);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
