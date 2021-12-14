package com.example.service;

import com.example.service.model.Trip;
import com.example.service.repository.TripRepository;
import com.example.service.service.TripService;
import com.example.service.service.TripServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;


import static reactor.core.publisher.Mono.when;

public class TripServiceTests {

    @InjectMocks
    TripServiceImpl tripService;

    @Mock
    TripRepository tripRepository;

    @BeforeEach
    void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void get_trips() {

        Trip trip = new Trip();
        when(tripRepository.findById(1L)).thenReturn(java.util.Optional.of(trip));
    }

    @Test
    public void send_trip() {

    }

}
