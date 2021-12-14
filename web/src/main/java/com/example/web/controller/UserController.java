package com.example.web.controller;

import com.example.service.dto.*;
import com.example.service.model.Flight;
import com.example.service.model.Trip;
import com.example.service.model.TripStatus;
import com.example.service.model.User;
import com.example.service.service.FlightServiceImpl;
import com.example.service.service.MyUserDetailsService;
import com.example.service.service.TripServiceImpl;
import com.example.service.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TripServiceImpl tripService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private FlightServiceImpl flightService;

    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);


    @PostMapping("/trip")
    public ResponseEntity<Trip> createTrip(@RequestBody TripDto tripDto, Principal principal) throws Exception {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok().body(tripService.saveTrip(tripDto, user.getId()));
    }

    @PutMapping("/trip/{id}")
    public ResponseEntity<Trip> updateTrip(@RequestBody TripDto tripDto, Principal principal, @PathVariable Long id) throws Exception {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok().body(tripService.updateTrip(tripDto, id, user));
    }


    @GetMapping("/trip")
    public ResponseEntity<List<Trip>> getAllUserTrips(Principal principal) {
        User user = myUserDetailsService.getUserByEmail(principal.getName());
        return ResponseEntity.ok().body(tripService.filterTripsByUser(user.getId()));

    }

    @PutMapping("/trip/{id}/send")
    public ResponseEntity<Trip> sendTrip(@PathVariable Long id) {
        return ResponseEntity.ok().body(tripService.changeTripStatus(id, TripStatus.WAITING));
    }

    @GetMapping("/trip/status")
    public ResponseEntity<List<Trip>> filterTripsByStatus(@RequestBody TripStatusDto tripStatusDto) {
        return ResponseEntity.ok().body(tripService.filterTripsByStatus(tripStatusDto.getTripStatus()));

    }

    @GetMapping("/trip/reason")
    public ResponseEntity<List<Trip>> filterTripsByReason(@RequestBody TripReasonDto tripReasonDto) {
        return ResponseEntity.ok().body(tripService.filterTripsByReason(tripReasonDto.getTripReason()));

    }

    @PutMapping("trip/{id}/flight")
    public ResponseEntity<Flight> addFlight(@PathVariable Long id, @RequestBody FlightIdDto flightIdDto, Principal principal) throws Exception {
        User user = userService.findByEmail(principal.getName());
        System.out.println(flightIdDto);
        return ResponseEntity.ok().body(tripService.addFlight(id, flightIdDto, principal.getName()));
    }


    @DeleteMapping("/trip/{id}")
    public void deleteTrip(@PathVariable Long id, Principal principal) throws Exception {
        User user = userService.findByEmail(principal.getName());

        tripService.deleteTrip(id);
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getAllFlights(@RequestBody FlightDto flightDto) {
        System.out.println(flightDto.getArrivalDate().toLocalDate());
        return ResponseEntity.ok().body(flightService.searchFlights(flightDto.getOrigin(), flightDto.getDestination(),
                flightDto.getDepartureDate(), flightDto.getArrivalDate()));
    }


}
