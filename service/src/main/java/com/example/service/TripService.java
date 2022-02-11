package com.example.service;

import com.example.dto.FlightIdDto;
import com.example.dto.TripDto;
import com.example.dto.TripStatusDto;
import com.example.model.*;

import java.util.List;

public interface TripService {

    TripDto saveTrip(TripDto tripDto, Long userId) throws Exception;

    Trip updateTrip(TripDto tripDto, Long id, User user) throws Exception;

    List<TripDto> getAllSendTrips();

    List<Trip> getAllTrips();

    List<Trip> filterTripsByStatus(TripStatus tripStatus, User user);

    List<Trip> filterTripsByReason(TripReason tripReason, User user);

    void deleteTrip(Long id, User user) throws Exception;

    List<TripDto> filterTripsByUser(Long userId);

    Flight addFlight(Long tripId, FlightIdDto flightIdDto, String email) throws Exception;

    Trip answerTripRequest(Long tripId, TripStatusDto tripStatusDto) throws Exception;

    Trip findById(Long id);

    TripDto sendTrip(Long id, User user) throws Exception;

    List<Trip> filterSentTripsByReason(TripReason tripReason);

}
