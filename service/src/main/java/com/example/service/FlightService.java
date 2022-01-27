package com.example.service;

import com.example.dto.FlightDto;
import com.example.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {

    Flight saveFlight(FlightDto flightDto) throws Exception;

    Flight updateFlight(FlightDto flightDto, Long id);

    List<Flight> searchFlights(String origin, String destination, LocalDateTime departureDate, LocalDateTime arrivalDate) throws Exception;

    List<FlightDto> getFlights();

    void deleteFlight(Long id);

    Flight findById(Long id);
}
