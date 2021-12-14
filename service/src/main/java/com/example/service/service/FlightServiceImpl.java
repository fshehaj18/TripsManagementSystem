package com.example.service.service;

import com.example.service.dto.FlightDto;
import com.example.service.model.Flight;
import com.example.service.repository.FlightRepository;
import com.example.service.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TripRepository tripRepository;

    @Override
    public Flight saveFlight(FlightDto flightDto) throws Exception {

        if (flightDto.getDestination().equals(flightDto.getOrigin())) {
            throw new Exception("Origin and destination cannot be the same!!");
        }
        if (flightDto.getDepartureDate().isBefore(LocalDateTime.now()))
            throw new Exception("Invalid departure date!!");
        if (!flightDto.getArrivalDate().isAfter(flightDto.getDepartureDate()))
            throw new Exception("Invalid arrival date!!");

        Flight flight = new Flight();
        flight.setOrigin(flightDto.getOrigin());
        flight.setDepartureDate(flightDto.getDepartureDate());
        flight.setArrivalDate(flightDto.getArrivalDate());
        flight.setDestination(flightDto.getDestination());

        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(FlightDto flightDto, Long id) {

        Flight flight = flightRepository.getById(id);

        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> searchFlights(String origin, String destination, LocalDateTime departureDate, LocalDateTime arrivalDate) {
        List<Flight> directFlights = flightRepository.getDirectFlights(origin, destination, departureDate, arrivalDate).get();
        if (!directFlights.isEmpty())
            return flightRepository.getFlightsByOrigin(origin, departureDate, arrivalDate);

        return directFlights;
    }
}
