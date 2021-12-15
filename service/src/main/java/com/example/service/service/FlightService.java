package com.example.service.service;

import com.example.service.dto.FlightDto;
import com.example.service.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {

    Flight saveFlight(FlightDto flightDto) throws Exception;

    Flight updateFlight(FlightDto flightDto, Long id);

    List<Flight> searchFlights(String origin, String destination, LocalDateTime departureDate, LocalDateTime arrivalDate) throws Exception;

    List<Flight> getFlights();

    void deleteFlight(Long id);

    Flight findById(Long id);
}
