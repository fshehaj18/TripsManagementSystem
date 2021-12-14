package com.example.service;

import com.example.service.repository.TripRepository;
import com.example.service.service.TripService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TripServiceTests {

    @InjectMocks
    TripService tripService;

    @Mock
    TripRepository tripRepository;

    @Test
    public void get_trips(){

    }
}
