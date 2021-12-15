package com.example.service.service;

import com.example.service.dto.FlightIdDto;
import com.example.service.dto.TripDto;
import com.example.service.dto.TripStatusDto;
import com.example.service.model.*;

import java.util.List;

public interface TripService {

    Trip saveTrip(TripDto tripDto, Long userId) throws Exception;

    Trip updateTrip(TripDto tripDto, Long id, User user) throws Exception;

    Trip changeTripStatus(Long id, TripStatus tripStatus);

    List<Trip> getAllSendTrips();

    List<Trip> getAllTrips();

    List<Trip> filterTripsByStatus(TripStatus tripStatus, User user);

    List<Trip> filterTripsByReason(TripReason tripReason, User user);

    void deleteTrip(Long id, User user) throws Exception;

    List<Trip> filterTripsByUser(Long userId);

    Flight addFlight(Long tripId, FlightIdDto flightIdDto, String email) throws Exception;

    Trip answerTripRequest(Long tripId, TripStatusDto tripStatusDto) throws Exception;

    Trip findById(Long id);

    Trip sendTrip(Long id, User user) throws Exception;

    List<Trip> filterSentTripsByReason(TripReason tripReason);

}
