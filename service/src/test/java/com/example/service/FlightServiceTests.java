package com.example.service;

import com.example.dto.FlightDto;
import com.example.model.Flight;
import com.example.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

public class FlightServiceTests {

    @InjectMocks
    FlightServiceImpl flightService;

    @Mock
    FlightRepository flightRepository;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void get_flights() {

    }

    @Test
    public void update_flight() throws Exception {
        Flight flight = new Flight();
        flight.setFlightId(0L);
        flight.setOrigin("Tirane");
        flight.setDestination("Paris");
        flight.setArrivalDate(LocalDateTime.of(2022, Month.JANUARY, 21, 10, 10));
        flight.setDepartureDate(LocalDateTime.of(2022, Month.JANUARY, 18, 12, 10));
        //flightRepository.save(flight);

        when(flightRepository.save(flight)).thenReturn(flight);

        FlightDto flightDto = new FlightDto();
        flightDto.setArrivalDate(LocalDateTime.of(2022, Month.JANUARY, 22, 10, 10));
        flightDto.setOrigin("Tirane");
        flightDto.setDestination("Milano");
        flightDto.setDepartureDate(LocalDateTime.of(2022, Month.JANUARY, 18, 12, 10));

        Flight flight1 = flightService.saveFlight(flightDto);

        System.out.println(flight1);

        Flight getFlight1 = flightRepository.getById(0L);

        verify(flightRepository, times(1)).save(flight);
    }

    @Test
    public void create_flight() {

    }

    @Test
    public void get_flight_by_id() {

        Flight flight = new Flight();
        flight.setFlightId(1L);
        flight.setOrigin("Tirane");
        flight.setDestination("Paris");
        flight.setArrivalDate(LocalDateTime.of(2022, Month.JANUARY, 21, 10, 10));
        flight.setDepartureDate(LocalDateTime.of(2022, Month.JANUARY, 18, 12, 10));

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        Flight flight1 = flightService.findById(1L);

        assertEquals(flight, flight1);
    }


    @Test
    public void delete_flight() throws Exception {
        Flight flight = new Flight();
        flight.setFlightId(120L);
        flight.setOrigin("Tirane");
        flight.setDestination("Paris");
        flight.setArrivalDate(LocalDateTime.of(2022, Month.JANUARY, 21, 10, 10));
        flight.setDepartureDate(LocalDateTime.of(2022, Month.JANUARY, 18, 12, 10));

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        System.out.println(flightRepository.findAll());

        //doNothing().when(flightService.deleteFlight(1L));

        Flight flight1 = flightService.findById(1L);

        assertNull(flight1);

    }


}
