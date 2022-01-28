package com.example.service;

import com.example.dto.FlightIdDto;
import com.example.dto.TripDto;
import com.example.dto.TripStatusDto;
import com.example.model.*;
import com.example.repository.FlightRepository;
import com.example.repository.TripRepository;
import com.example.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ModelMapper modelMapper;

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


        trip.setTripReason(tripDto.getTripReason());
        trip.setDescription(tripDto.getDescription());

        trip.setArrivalDate(tripDto.getArrivalDate());
        trip.setOrigin(tripDto.getOrigin());
        trip.setDepartureDate(tripDto.getDepartureDate());
        trip.setArrivalDate(tripDto.getArrivalDate());
        trip.setDestination(tripDto.getDestination());


        return tripRepository.save(trip);
    }

    @Override
    public Trip changeTripStatus(Long id, TripStatus tripStatus) {
        Trip trip = tripRepository.findById(id).get();
        trip.setTripStatus(tripStatus);
        return tripRepository.save(trip);
    }

    @Override
    public List<TripDto> getAllSendTrips() {

        //get all trips except the ones which are not sent(tripStatus = CREATED)

        List<Trip> trips = tripRepository.getSendTrips(TripStatus.CREATED);

        List<TripDto> tripDtoList = Arrays.asList(modelMapper.map(trips, TripDto[].class));
        return tripDtoList;
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Override
    public List<Trip> filterTripsByStatus(TripStatus status, User user) {

        return tripRepository.filterTripsByStatus(status, user);
    }

    @Override
    public List<Trip> filterTripsByReason(TripReason tripReason, User user) {
        return tripRepository.filterTripsByReason(tripReason, user);
    }

    @Override
    public void deleteTrip(Long id, User user) throws Exception {
        Trip trip = tripRepository.findById(id).get();
        if (trip.getTripStatus() != TripStatus.CREATED)
            throw new Exception("Trip cannot be deleted after sent!");

        if (trip.getUser() != user)
            throw new Exception("Cannot delete this trip! Not your trip");

        tripRepository.delete(trip);
    }

    @Override
    public List<TripDto> filterTripsByUser(Long userId) {

        List<Trip> trips = tripRepository.filterTripsByUser(userId);

        List<TripDto> tripDtoList = Arrays.asList(modelMapper.map(trips, TripDto[].class));

        return tripDtoList;

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
        if (flight.getArrivalDate().toLocalDate().isAfter(trip.getArrivalDate()))
            throw new Exception("Flight is after trip arrival date");
        trip.addFlight(flight);

        flightRepository.save(flight);
        return flight;
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

    @Override
    public Trip sendTrip(Long id, User user) throws Exception {
        Trip trip = tripRepository.findById(id).get();
        if(user != trip.getUser())
            throw new Exception("You are not allowed to send this trip!");
        if (trip.getTripStatus() != TripStatus.CREATED)
            throw new Exception("Already sent!");
        return changeTripStatus(id, TripStatus.WAITING);
    }

    @Override
    public List<Trip> filterSentTripsByReason(TripReason tripReason) {
        return null;
    }

}
//to be done