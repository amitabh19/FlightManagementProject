package com.flight.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.entities.Flight;

public interface FlightService {
	
	public ResponseEntity<?> addFlight(Flight flight);
	public List<Flight> viewAllFlight();
	public Flight viewFlight(BigInteger flightNumber);
	public ResponseEntity<Flight> modifyFlight(Flight flight);
	public ResponseEntity<Flight> deleteFlight(BigInteger flightNumber);
	public boolean validateFlight(Flight flight);

}
