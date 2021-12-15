package com.example.service.repository;

import com.example.service.model.Trip;
import com.example.service.model.TripReason;
import com.example.service.model.TripStatus;
import com.example.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("select t from Trip t where t.tripReason=?1 and t.user=?2")
    List<Trip> filterTripsByReason(TripReason tripReason, User user);

    @Query("select t from Trip t where t.tripStatus=?1 and t.user=?2")
    List<Trip> filterTripsByStatus(TripStatus status, User user);

    @Query("select t from Trip t where t.user.id=?1")
    List<Trip> filterTripsByUser(Long userId);

    @Query("select t from Trip t where t.tripStatus<>?1")
    List<Trip> getSendTrips(TripStatus tripStatus);

    @Query("select t from Trip t where t.tripReason=?1 and t.tripStatus<>?2")
    List<Trip> filterSentTripsByReason(TripReason tripReason, TripStatus tripStatus);
}
