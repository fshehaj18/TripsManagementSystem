package com.example.service;

import com.example.repository.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static reactor.core.publisher.Mono.when;

public class TripServiceTests {

    @InjectMocks
    TripServiceImpl tripService;

    @Mock
    TripRepository tripRepository;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }



}
