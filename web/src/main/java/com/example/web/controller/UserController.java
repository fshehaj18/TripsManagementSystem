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
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok().body(tripService.filterTripsByUser(user.getId()));

    }

    @PutMapping("/trip/{id}/send")
    public ResponseEntity<Trip> sendTrip(@PathVariable Long id, Principal principal) throws Exception {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok().body(tripService.sendTrip(id, user));
    }

    @GetMapping("/trip/status")
    public ResponseEntity<List<Trip>> filterTripsByStatus(@RequestBody TripStatusDto tripStatusDto, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok().body(tripService.filterTripsByStatus(tripStatusDto.getTripStatus(), user));

    }

    @GetMapping("/trip/reason")
    public ResponseEntity<List<Trip>> filterTripsByReason(@RequestBody TripReasonDto tripReasonDto, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok().body(tripService.filterTripsByReason(tripReasonDto.getTripReason(), user));

    }

    @PutMapping("trip/{id}/flight")
    public ResponseEntity<Flight> addFlight(@PathVariable Long id, @RequestBody FlightIdDto flightIdDto, Principal principal) throws Exception {
        User user = userService.findByEmail(principal.getName());
        logger.debug(flightIdDto);
        return ResponseEntity.ok().body(tripService.addFlight(id, flightIdDto, principal.getName()));
    }


    @DeleteMapping("/trip/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id, Principal principal) throws Exception {
        User user = userService.findByEmail(principal.getName());

        tripService.deleteTrip(id, user);

        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> searchFlights(@RequestBody FlightDto flightDto) throws Exception {
        logger.debug(flightDto);
        return ResponseEntity.ok().body(flightService.searchFlights(flightDto.getOrigin(), flightDto.getDestination(),
                flightDto.getDepartureDate(), flightDto.getArrivalDate()));
    }

    @GetMapping("/all-flights")
    public ResponseEntity<List<Flight>> getAllFlights(){
        return ResponseEntity.ok().body(flightService.getFlights());
    }
}
