package com.example.service;

import com.example.service.repository.FlightRepository;
import com.example.service.service.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FlightServiceTests {

    @InjectMocks
    FlightServiceImpl flightService;

    @Mock
    FlightRepository flightRepository;

    @BeforeEach
    void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void get_flights() {

    }

    @Test
    public void create_flight() {

    }


}
