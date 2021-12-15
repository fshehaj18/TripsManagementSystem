package com.example.web.controller;

import com.example.service.dto.FlightDto;
import com.example.service.dto.TripStatusDto;
import com.example.service.dto.UserDto;
import com.example.service.model.Flight;
import com.example.service.model.Trip;
import com.example.service.model.User;
import com.example.service.service.FlightServiceImpl;
import com.example.service.service.TripServiceImpl;
import com.example.service.service.UserServiceImpl;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TripServiceImpl tripService;

    @Autowired
    private FlightServiceImpl flightService;


    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userInfo) throws Exception {

        return ResponseEntity.ok().body(userService.saveUser(userInfo));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() throws NotFoundException {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/trip")
    public ResponseEntity<List<Trip>> getAllSendTrips() {
        return ResponseEntity.ok().body(tripService.getAllSendTrips());
    }

    @PutMapping("/trip/{id}/send")
    public ResponseEntity<Trip> answerRequest(@RequestBody TripStatusDto tripStatusDto, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(tripService.answerTripRequest(id, tripStatusDto));
    }

    @PostMapping("flight")
    public ResponseEntity<Flight> createFlight(@RequestBody FlightDto flightDto) throws Exception {
        return ResponseEntity.ok().body(flightService.saveFlight(flightDto));
    }

    @PutMapping("flight/{id}")
    public ResponseEntity<Flight> updateFlight(@RequestBody FlightDto flightDto, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(flightService.saveFlight(flightDto));
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getFlights(){
        return ResponseEntity.ok().body(flightService.getFlights());
    }

    @DeleteMapping("flight/{id}")
    public ResponseEntity<Void> deleteFlight( @PathVariable Long id) throws Exception {

        flightService.deleteFlight(id);

        return ResponseEntity.ok().body(null);
    }
}
