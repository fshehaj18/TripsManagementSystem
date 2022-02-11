package com.example.web.controller;

import com.example.dto.*;
import com.example.model.Flight;
import com.example.model.Trip;
import com.example.model.User;
import com.example.service.EmailSenderService;
import com.example.service.FlightServiceImpl;
import com.example.service.TripServiceImpl;
import com.example.service.UserServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @PatchMapping("/user/{id}")
    public ResponseEntity<User> editUser(@RequestBody UserDto userInfo, @PathVariable Long id) throws Exception {

        return ResponseEntity.ok().body(userService.updateUser(id, userInfo));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() throws NotFoundException {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body(null) ;
    }

    @GetMapping("/trip")
    public ResponseEntity<List<TripDto>> getAllSendTrips() {
        return ResponseEntity.ok().body(tripService.getAllSendTrips());
    }

    @PutMapping("/trip/{id}/send")
    public ResponseEntity<Trip> answerRequest(@RequestBody TripStatusDto tripStatusDto, @PathVariable Long id) throws Exception {
        System.out.println(tripStatusDto);
        return ResponseEntity.ok().body(tripService.answerTripRequest(id, tripStatusDto));
    }

    @GetMapping("/trip/reason")
    public ResponseEntity<List<Trip>> filterSentTripsByReason(@RequestBody TripReasonDto tripReasonDto) {
        return ResponseEntity.ok().body(tripService.filterSentTripsByReason(tripReasonDto.getTripReason()));

    }

    @PostMapping("flight")
    public ResponseEntity<Flight> createFlight(@RequestBody FlightDto flightDto) throws Exception {
        return ResponseEntity.ok().body(flightService.saveFlight(flightDto));
    }

    @PutMapping("flight/{id}")
    public ResponseEntity<Flight> updateFlight(@RequestBody FlightDto flightDto, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(flightService.updateFlight(flightDto, id));
    }

    @GetMapping("/flights")
    public ResponseEntity<List<FlightDto>> getFlights(){

        return ResponseEntity.ok().body(flightService.getFlights());
    }

    @DeleteMapping("flight/{id}")
    public ResponseEntity<Void> deleteFlight( @PathVariable Long id) throws Exception {

        flightService.deleteFlight(id);

        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id){
        return ResponseEntity.ok().body(tripService.findById(id));
    }

    @GetMapping("/emails")
    public ResponseEntity<Void> getEmails(Principal principal) {

        EmailSenderService.checkMail("fridishehaj26@gmail.com", "urxidwzxpwqundle");
        return ResponseEntity.ok().body(null);

    }

}
