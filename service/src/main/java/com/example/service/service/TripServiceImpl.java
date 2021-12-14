package com.example.service.service;

import com.example.service.dto.FlightIdDto;
import com.example.service.dto.TripDto;
import com.example.service.dto.TripStatusDto;
import com.example.service.model.*;
import com.example.service.repository.FlightRepository;
import com.example.service.repository.TripRepository;
import com.example.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Trip saveTrip(TripDto tripDto, Long userId) throws Exception {

        if (tripDto.getDestination().equals(tripDto.getOrigin())) {
            throw new Exception("Origin and destination cannot be the same!!");
        }
        if (tripDto.getDepartureDate().isBefore(LocalDate.now()))
            throw new Exception("Invalid departure date!!");
        if (!tripDto.getArrivalDate().isAfter(tripDto.getDepartureDate()))
            throw new Exception("Invalid arrival date!!");

        Trip trip = new Trip();
        trip.setTripReason(tripDto.getTripReason());
        trip.setDescription(tripDto.getDescription());
        trip.setTripStatus(TripStatus.CREATED);
        trip.setOrigin(tripDto.getOrigin());
        trip.setDepartureDate(tripDto.getDepartureDate());
        trip.setArrivalDate(tripDto.getArrivalDate());
        trip.setDestination(tripDto.getDestination());
        trip.setUser(userRepository.getById(userId));

        return tripRepository.save(trip);
    }

    @Override
    public Trip updateTrip(TripDto tripDto, Long id, User user) throws Exception {

        Trip trip = tripRepository.getById(id);

        if (trip.getUser() != user)
            throw new Exception("Cannot update this trip!");

        if (trip.getTripStatus() != TripStatus.CREATED)
            throw new Exception("Trip cannot be edited after sent!");


        return tripRepository.save(trip);
    }

    @Override
    public Trip changeTripStatus(Long id, TripStatus tripStatus) {
        Trip trip = tripRepository.findById(id).get();
        trip.setTripStatus(tripStatus);
        return tripRepository.save(trip);
    }

    @Override
    public List<Trip> getAllSendTrips() {
        return tripRepository.filterTripsByStatus(TripStatus.WAITING);
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Override
    public List<Trip> filterTripsByStatus(TripStatus status) {
        return tripRepository.filterTripsByStatus(status);
    }

    @Override
    public List<Trip> filterTripsByReason(TripReason tripReason) {
        return tripRepository.filterTripsByReason(tripReason);
    }

    @Override
    public void deleteTrip(Long id) throws Exception {
        Trip trip = tripRepository.findById(id).get();
        if (trip.getTripStatus() != TripStatus.CREATED)
            throw new Exception("Trip cannot be deleted after sent!");
        tripRepository.delete(trip);
    }

    @Override
    public List<Trip> filterTripsByUser(Long userId) {
        return tripRepository.filterTripsByUser(userId);

    }

    @Override
    public Flight addFlight(Long tripId, FlightIdDto flightIdDto, String email) throws Exception {
        User currentUser = userRepository.findByEmail(email).get();
        Trip trip = tripRepository.findById(tripId).get();
        if (trip.getUser() != currentUser)
            throw new Exception("You cannot modify this trip!");
        if (trip.getTripStatus() != TripStatus.ACCEPTED)
            throw new Exception("You cannot add flights without permission from admin!");
        //TO DO
        Flight flight = flightRepository.findById(flightIdDto.getFlightId()).get();
        if (flight.getDepartureDate().toLocalDate().isBefore(trip.getDepartureDate()))
            throw new Exception("Flight is before trip date");
        if (flight.getArrivalDate().toLocalDate().isAfter(trip.getDepartureDate()))
            throw new Exception("Flight is after trip arrival date");
        flight.add(trip);

        flightRepository.save(flight);
        return flight;
    }

    @Override
    public Trip sendTrip(Long tripId) {
        return null;
    }

    @Override
    public Trip answerTripRequest(Long tripId, TripStatusDto tripStatusDto) throws Exception {
        Trip trip = tripRepository.getById(tripId);
        if (trip.getTripStatus() != TripStatus.WAITING ||
                tripStatusDto.getTripStatus() != TripStatus.ACCEPTED && tripStatusDto.getTripStatus() != TripStatus.REJECTED)
            throw new Exception("Not allowed!");
        trip.setTripStatus(tripStatusDto.getTripStatus());
        return tripRepository.save(trip);
    }

    @Override
    public Trip findById(Long id) {
        return tripRepository.findById(id).get();
    }
}