package com.example.repository;

import com.example.dto.TripDto;
import com.example.model.Trip;
import com.example.model.TripReason;
import com.example.model.TripStatus;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
