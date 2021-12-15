package com.example.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {

    private String origin;

    private String destination;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;
}
