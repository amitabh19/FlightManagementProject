package com.flight.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.entities.Airport;
import com.flight.entities.Flight;
import com.flight.entities.Schedule;
import com.flight.entities.ScheduledFlight;

public interface ScheduledFlightService {
	
	public ResponseEntity<ScheduledFlight> scheduleFlight(ScheduledFlight scheduleFlight);
	public List<Flight> viewScheduledFlights(Airport srcAirport, Airport destAirport, String date);
	public ScheduledFlight viewScheduledFlightById(BigInteger id);
	public List<ScheduledFlight> viewScheduledFlightsByFlightNumber(BigInteger flightNumber);
	public List<ScheduledFlight> viewAllScheduledFlights();
	public ResponseEntity<ScheduledFlight> modifyScheduledFlight(Flight flight, Schedule schedule, int availableSeats);
	public ResponseEntity<ScheduledFlight> deleteScheduledFlight(BigInteger id);
	public boolean validateScheduledFlight(ScheduledFlight scheduledFlight);


}
