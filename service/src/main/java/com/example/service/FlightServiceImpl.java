package com.example.service;

import com.example.dto.FlightDto;
import com.example.dto.TripDto;
import com.example.model.Flight;
import com.example.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ModelMapper modelMapper;

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
        flight.setOrigin(flightDto.getOrigin());
        flight.setDepartureDate(flightDto.getDepartureDate());
        flight.setArrivalDate(flightDto.getArrivalDate());
        flight.setDestination(flightDto.getDestination());

        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> searchFlights(String origin, String destination, LocalDateTime departureDate, LocalDateTime arrivalDate) throws Exception {

        if (origin.equals(destination))
            throw new Exception("Origin cannot be equal with destination!");
        if (departureDate.isAfter(arrivalDate))
            throw new Exception("Invalid dates");

        List<Flight> directFlights = flightRepository.getDirectFlights(origin, destination, arrivalDate, departureDate).get();
        if (directFlights.isEmpty())
            return flightRepository.getFlightsByOrigin(origin, arrivalDate, departureDate).get();

        return directFlights;
    }

    @Override
    public List<FlightDto> getFlights() {

        List<Flight> flights = flightRepository.findAll();

        return Arrays.asList(modelMapper.map(flights, FlightDto[].class));
    }

    /*public List<FlightDto> getFlightsByTripId(Long tripId){
        return flightRepository.getFlightsByTripId(tripId).get();
    }*/

    @Override
    public void deleteFlight(Long id) {
        flightRepository.delete(flightRepository.findById(id).get());
    }

    @Override
    public Flight findById(Long id) {
        return flightRepository.findById(id).get();
    }



}
