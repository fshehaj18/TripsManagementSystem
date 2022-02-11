package com.example.web.controller;

import com.example.dto.*;
import com.example.model.ChangePassword;
import com.example.model.Flight;
import com.example.model.Trip;
import com.example.model.User;
import com.example.service.FlightServiceImpl;
import com.example.service.TripServiceImpl;
import com.example.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<TripDto> createTrip(@RequestBody TripDto tripDto, Principal principal) throws Exception {
        System.out.println(tripDto.getArrivalDate());
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok().body(tripService.saveTrip(tripDto, user.getId()));
    }

    @PutMapping("/trip/{id}")
    public ResponseEntity<Trip> updateTrip(@RequestBody TripDto tripDto, Principal principal, @PathVariable Long id) throws Exception {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok().body(tripService.updateTrip(tripDto, id, user));
    }


    @GetMapping("/trip")
    public ResponseEntity<List<TripDto>> getAllUserTrips(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok().body(tripService.filterTripsByUser(user.getId()));

    }

    @PutMapping("/trip/{id}/send")
    public ResponseEntity<TripDto> sendTrip(@PathVariable Long id, Principal principal) throws Exception {
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

    @PostMapping("/flights")
    public ResponseEntity<List<Flight>> searchFlights(@RequestBody FlightDto flightDto) throws Exception {
        logger.debug(flightDto);
        return ResponseEntity.ok().body(flightService.searchFlights(flightDto.getOrigin(), flightDto.getDestination(),
                flightDto.getDepartureDate(), flightDto.getArrivalDate()));
    }

    @GetMapping("/all-flights")
    public ResponseEntity<List<FlightDto>> getAllFlights(){
        return ResponseEntity.ok().body(flightService.getFlights());
    }

    @PostMapping("/password/{id}")
    public ResponseEntity<User> changePassword(@RequestBody ChangePassword changePassword, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(userService.changePassword(changePassword, id));
    }
}
