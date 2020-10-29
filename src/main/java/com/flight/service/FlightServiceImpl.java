package com.flight.service;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.dao.FlightDao;

import com.flight.entities.Flight;
import com.flight.exceptions.RecordAlreadyPresentException;
import com.flight.exceptions.RecordNotFound;
import com.flight.repositories.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

Logger logger=LoggerFactory.getLogger(FlightServiceImpl.class);
	
	@Autowired
	private FlightDao flightDao;
	
	@Autowired
	private FlightRepository flightRep;
	
	
	/**
	 * This function is used to add Flight object to flight database
	 * 
	 * @author Garima
	 * @param Flight object to be added
	 * @return Flight object added
	 * @version 1.0
	 * @since 29-10-2020
	 */

	@Override
	public ResponseEntity<Flight> addFlight(Flight flight) {
		logger.trace("add flight method is accessed in service layer");
		try
		{
			if(validateFlight(flight)) {
				flightDao.addFlight(flight);
				return new ResponseEntity<Flight>(flight,HttpStatus.OK);
			}
			else
			{
				System.out.println("Flight with Flight Number: "+ flight.getFlightNumber()+" is already present!!!");
				throw new RecordAlreadyPresentException("Flight with Flight Number: "+ flight.getFlightNumber()+" is already present!!!");
			}
		}
		catch(RecordAlreadyPresentException e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}

	/**
	 * This functoin is use to view all the flights 
	 * 
	 * @author Garima
	 * @param none
	 * @return List of all the flights
	 * @version 1.0
	 * @since 29-10-2020
	 */

	@Override
	public List<Flight> viewAllFlight() {
		logger.trace("view all flight method is accessed in service layer");
		return flightDao.viewAllFlight();
	}
	
	/**
	 * This function is use to view Flight corresponding to the flight number
	 * 
	 * @author Garima
	 * @param flightNumber-BigInteger
	 * @return Flight object constituting flight details from the corresponding flight number
	 * @version 1.0
	 * @since 29-10-2020
	 */

	@Override
	public Flight viewFlight(BigInteger flightNumber) {
		logger.trace("view flight method is accessed in service layer");
		return flightDao.viewFlight(flightNumber);
		
	}
	
	/**
	 * This function is used to modify the existing flight object
	 * 
	 * @author Garima
	 * @param Flight object to be modified
	 * @return Flight object which is modified
	 * @version 1.0
	 * @since 29-10-2020
	 */

	@Override
	public ResponseEntity<Flight> modifyFlight(Flight flight) {
		logger.trace("modify flight method is accessed in service layer");
		try {
			List<Flight> flights=flightDao.viewAllFlight();
			if(flights.contains(flight))
			{
				flightDao.modifyFlight(flight);
				return new ResponseEntity<Flight>(flight,HttpStatus.OK);
			}
			else {
				throw new RecordNotFound("Flight with number: " + flight.getFlightNumber() + " not exists");
			}
		}
		catch(RecordNotFound e)
		{
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	/**
	 * This function is used to delete flight from database
	 * 
	 * @author Garima
	 * @param Flight Number of the flight to be deleted
	 * @return output on the console that flight is deleted
	 * @version 1.0
	 * @since 29-10-2020
	 */
	@Override
	public ResponseEntity<Flight> deleteFlight(BigInteger flightNumber) {
		logger.trace("delete flight method is accessed in service layer");
		try {
			List<Flight> flights=flightDao.viewAllFlight();
			Flight flight=flightRep.findById(flightNumber).get();
			if(flights.contains(flight)) {
				flightDao.removeFlight(flightNumber);
				return new ResponseEntity<Flight>(flight,HttpStatus.OK);
			}
			else
			{
				throw new RecordNotFound("Flight with number: " + flightNumber + " not exists");
			}
			
		}
		catch(RecordNotFound e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}
	/**
	 * This function is used to validate flight details before adding them to the database
	 * 
	 * @author Garima
	 * @param Flight object to be validated
	 * @return Prints output on console that flight object is valid or not
	 * @version 1.0
	 * @since 29-10-2020
	 */
	@Override
	public boolean validateFlight(Flight flight) {
		logger.trace("validate flight method is accessed in service layer");
		List<Flight> allFlights=viewAllFlight();
		for(Flight f: allFlights)
		{
			if(f.getFlightNumber()==flight.getFlightNumber() && f.getCarrierName().equals(flight.getCarrierName()) && f.getFlightModel().equals(flight.getFlightModel()) && f.getSeatCapacity()==flight.getSeatCapacity())
			{
				return false;
			}
		}
		return true;
	}
	
	

}
