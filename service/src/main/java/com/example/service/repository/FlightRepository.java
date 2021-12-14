package com.example.service.repository;

import com.example.service.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("select f from Flight f where f.origin=?1 and f.destination=?2 and f.arrivalDate>=?3 and f.departureDate<=?4 ")
    Optional<List<Flight>> getDirectFlights(String origin, String destination, LocalDateTime departureDate, LocalDateTime arrivalDate);

    @Query("SELECT f from Flight f where f.origin=?1 and f.arrivalDate<=?2 and f.departureDate<=?3 ")
    List<Flight> getFlightsByOrigin(String origin, LocalDateTime arrivalDate, LocalDateTime departureDate);
}
