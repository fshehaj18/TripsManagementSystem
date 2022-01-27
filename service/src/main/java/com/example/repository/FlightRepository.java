package com.example.repository;

import com.example.dto.FlightDto;
import com.example.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("select f from Flight f where f.origin=?1 and f.destination=?2 and f.arrivalDate<=?3 and f.departureDate>=?4 ")
    Optional<List<Flight>> getDirectFlights(String origin, String destination, LocalDateTime arrivalDate, LocalDateTime departureDate);

    @Query("SELECT f from Flight f where f.origin=?1 and f.arrivalDate<=?2 and f.departureDate>=?3 ")
    Optional<List<Flight>> getFlightsByOrigin(String origin, LocalDateTime arrivalDate, LocalDateTime departureDate);

    @Query("SELECT f from Flight f join Trip t where t.tripId=?1 ")
    Optional<List<Flight>> getFlightsByTripId(Long tripId);

}
