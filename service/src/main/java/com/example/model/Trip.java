package com.example.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Trip_Flight",
            joinColumns = {@JoinColumn(name = "trip_id")},
            inverseJoinColumns = {@JoinColumn(name = "flight_id")}
    )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<Flight> flights = new HashSet<>(0);


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public void addFlight(Flight f){
        flights.add(f);
    }

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
