package com.example.dto;

import com.example.model.Flight;
import com.example.model.TripReason;
import com.example.model.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripDto implements Serializable {

    private Long tripId;

    private TripStatus tripStatus;

    @Enumerated(EnumType.ORDINAL)
    private TripReason tripReason;

    private String description;

    private String origin;

    private String destination;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private Long userId;

    private Set<FlightDto> flights;

    public TripDto(Long tripId, TripStatus tripStatus, TripReason tripReason, String description, String origin, String destination, LocalDate departureDate, LocalDate arrivalDate, Long userId) {
        this.tripId = tripId;
        this.tripStatus = tripStatus;
        this.tripReason = tripReason;
        this.description = description;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.userId = userId;
    }
}
