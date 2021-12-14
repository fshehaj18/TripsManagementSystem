package com.example.service.dto;

import com.example.service.model.TripReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TripDto {

    @Enumerated(EnumType.ORDINAL)
    private TripReason tripReason;

    private String description;

    private String origin;

    private String destination;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

}
