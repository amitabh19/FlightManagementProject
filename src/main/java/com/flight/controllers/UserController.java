package com.flight.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.entities.Airport;
import com.flight.entities.Booking;
import com.flight.entities.GetSchedule;
import com.flight.entities.Passenger;
import com.flight.entities.ScheduledFlight;
import com.flight.entities.User;
import com.flight.repositories.BookingRepository;
import com.flight.service.AirportService;
import com.flight.service.BookingService;
import com.flight.service.PassengerService;
import com.flight.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private PassengerService passengerService;

	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private AirportService airportService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * This function is used to add user to database
	 * 
	 * @author amitabh
	 * @param User object
	 * @return ResponseEntity
	 * @version 1.0
	 * @since 29-10-2020
	 */
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User u) {
		logger.trace("addUser method accessed in UserController");
		return userService.createUser(u);
	}

	/**
	 * This function is used to view user present in the database by id
	 * 
	 * @author amitabh
	 * @param User object
	 * @return ResponseEntity
	 * @version 1.0
	 * @since 29-10-2020
	 */

	@GetMapping("/viewUser/{id}")
	public ResponseEntity<?> viewUser(@PathVariable BigInteger id) {
		logger.trace("viewUser method accessed in UserController");
		return userService.viewUserById(id);
	}

	/**
	 * This function is used to update user details to database
	 * 
	 * @author amitabh
	 * @param User object
	 * @return ResponseEntity
	 * @version 1.0
	 * @since 29-10-2020
	 */

	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User u) {
		logger.trace("updateUser method accessed in UserController");
		return userService.updateUser(u);
	}

	/**
	 * @author amitabh
	 * @param User object
	 * @return string valid or not
	 * @version 1.0
	 * @since 29-10-2020
	 */
	@PostMapping("user")
	public String validateUser(@RequestBody User u) {
		logger.trace("validateUser method accessed in UserController");

		if (userService.validateUser(u)) {
			return "valid user";
		}

		return "invalid user";
	}

	/**
	 * This function is used to get all users present in the database
	 * 
	 * @author amitabh
	 * @param User object
	 * @return List of users present in the database
	 * @version 1.0
	 * @since 29-10-2020
	 */
	@GetMapping("/users")
	public List<User> getUsers() {
		logger.trace("getUsers method accessed in UserController");
		return userService.displayAllUsers();
	}

	/**
	 * This function is used to delete a user from the database
	 * 
	 * @author amitabh
	 * @param UserId of type BigInteger
	 * @return will return if user is deleted or not from the databse in form of
	 *         string
	 * @version 1.0
	 * @since 29-10-2020
	 */
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable BigInteger id) {
		logger.info("deleteUser method accessed in UserController");
		return userService.deleteUser(id);

	}

	/**
	 * This function is used to search the bookings when arrival and departure
	 * destination is entered along with the date
	 * 
	 * @param schedule will return the schedule input by the user
	 * @return will return the list of flights according to the schedule
	 * 
	 * @author Sahil Narula
	 * @version 1.0
	 * @throws FlightNotFound
	 * @since 28-10-2020
	 **/
	@PostMapping("/search")
	public List<ScheduledFlight> searchBooking(@RequestBody GetSchedule schedule) {

		logger.trace("Search Booking controller accessed");
		return bookingService.searchBooking(schedule);
	}

	/**
	 * This function is used to get the list of bookings by user id
	 * 
	 * @param userId will return the schedule input by the user
	 * @return will return the list of bookings in the particular user id
	 * 
	 * @author Sahil Narula
	 * @version 1.0
	 * @throws FlightNotFound
	 * @throws BookingNotFound
	 * @since 28-10-2020
	 **/
	@PostMapping("/getBookings/{userId}")
	// @ExceptionHandler(RecordNotFound.class)
	public List<Booking> getUserBookings(@PathVariable BigInteger userId) {

		logger.trace("Get Booking controller accessed");
		return bookingService.getUserBookings(userId);
	}

	/**
	 * This function is used to create booking for a user
	 * 
	 * @param b will return the booking object input by the user
	 * @return will return the booked booking
	 * 
	 * @author Sahil Narula and Amitabh Saxena
	 * @version 1.0
	 * @since 28-10-2020
	 **/
	@PostMapping("/createBooking")
	public Booking createBooking(@RequestBody Booking b) {
		logger.trace("Create Booking controller accessed");
		List<Passenger> newList = new ArrayList<>();
		for (Passenger p : b.getPassenger()) {
			newList.add(passengerService.addPassenger(p));
		}
		Booking bbcn = new Booking(b.getUserId(), b.getBookingDateTime(), b.getTicketCost(), b.getScheduledFlight(),
				b.getNoOfPassengers(), newList);
		return bookingService.createBooking(bbcn);
	}

	@GetMapping("/getAllBookings")
	public List<Booking> getAllBookings() {

		return bookingRepo.findAll();
	}

	/**
	 * This method is used to get all Airports
	 * 
	 * @return returns all the Airports
	 */
	@GetMapping("/airports")
	public List<Airport> getAllAirports() {
		logger.trace("getAllAirports method accessed");
		return airportService.getAllAirports();
	}

	/**
	 * This method is used to delete Airport
	 * 
	 * @PathVariable id This is the parameter passed to get the id
	 * @return This returns the boolean expression generated
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/deleteAirport/{id}")
	public boolean deleteAirport(@PathVariable String id) {
		logger.trace("deleteAirport method accessed");
		airportService.deleteAirportById(id);
		return true;

	}

	/**
	 * This method is used to add Airport
	 * 
	 * @RequestBody airport This is the parameter passed to get the Airport object
	 * @return This returns the object of Airport generated
	 * @throws AirportAlreadyExistException
	 */
	@PostMapping("/addAirport")
	public Airport addAirport(@RequestBody Airport airport) {
		logger.trace("addAirport method accessed");
		return airportService.addAirport(airport);
	}

}
