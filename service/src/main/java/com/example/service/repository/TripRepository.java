package com.example.service.repository;

import com.example.service.model.Trip;
import com.example.service.model.TripReason;
import com.example.service.model.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("select t from Trip t where t.tripReason=?1")
    List<Trip> filterTripsByReason(TripReason tripReason);

    @Query("select t from Trip t where t.tripStatus=?1")
    List<Trip> filterTripsByStatus(TripStatus status);

    @Query("select t from Trip t where t.user.id=?1")
    List<Trip> filterTripsByUser(Long userId);

    @Query("select t from Trip t where t.tripStatus<>?1")
    List<Trip> getSendTrips(TripStatus tripStatus);


}
