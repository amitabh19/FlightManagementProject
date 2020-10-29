package com.flight.controllers;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.dao.PassengerDao;
import com.flight.entities.Booking;
import com.flight.entities.GetSchedule;
import com.flight.entities.Passenger;
import com.flight.entities.ScheduledFlight;
import com.flight.entities.User;
import com.flight.exceptions.RecordAlreadyPresentException;
import com.flight.repositories.AirportRepository;
import com.flight.repositories.BookingRepository;
import com.flight.repositories.FlightRepository;
import com.flight.repositories.PassengerRepository;
import com.flight.repositories.ScheduleRepository;
import com.flight.repositories.ScheduledFlightRepository;
import com.flight.repositories.UserRepository;
import com.flight.service.BookingService;
import com.flight.service.PassengerService;
import com.flight.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private AirportRepository airRepo;

	@Autowired
	private FlightRepository flightRepo;

	@Autowired
	private PassengerRepository passengerRepo;

	@Autowired
	private ScheduledFlightRepository sfrRepo;

	@Autowired
	private ScheduleRepository scheduleRepo;

	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private PassengerService passengerService;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/bookings")
	public List<Booking> getAllBooking() {
		return bookingRepo.findAll();
	}

	@RequestMapping("hello")
	public String yo() {
		return "helloo";

	}

	/**
	 * @author amitabh
	 * @param User
	 * @return string valid or not
	 */
	@PostMapping("user")
	public String validateUser(@RequestBody User u) {

		if (userRepo.findById(u.getUserId()).get() != null) {
			return "valid user";
		}

		return "in valid user";
	}

	/**
	 * This function is used to add user to database
	 * 
	 * @author amitabh
	 * @param User
	 * @return ResponseEntity
	 * @since 29/10/2020
	 */
	@PostMapping("/addUser")
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public ResponseEntity<?> addUser(@RequestBody User u) {
		return userService.createUser(u);
	}

	
	/**
	 * This function is used to update user details to database
	 * 
	 * @author amitabh
	 * @param User
	 * @return ResponseEntity
	 * @since 29/10/2020
	 */
	@PutMapping("/updateUser")
	//@ExceptionHandler(RecordAlreadyPresentException.class)
	public ResponseEntity<?> updateUser(@RequestBody User u) {
		return userService.updateUser(u);
	}
	
	
	/**
	 * This function is used to search the bookings when arrival and departure destination is entered along with the date
	 * @param schedule will return the schedule input by the user
	 * @return will return the list of flights according to the schedule
	 * 
	 * @author Sahil Narula
	 * @version 1.0 
	 * @since 28-10-2020
	**/
	@PostMapping("/search")
	public List<ScheduledFlight> searchBooking(@RequestBody GetSchedule schedule){
		
		return bookingService.searchBooking(schedule);
	}
	

	/**
	 * This function is used to get the list of bookings by user id
	 * @param userId will return the schedule input by the user
	 * @return will return the list of bookings in the particular user id
	 * 
	 * @author Sahil Narula
	 * @version 1.0 
	 * @since 28-10-2020
	**/
	@PostMapping("/getBookings/{userId}")
	public List<Booking> getUserBookings(@PathVariable BigInteger userId){
		
		return bookingService.getUserBookings(userId);
	}
	
	@PostMapping("/createBooking")
	public Booking createBooking(@RequestBody Booking b)
	{
		List<Passenger> newList = new ArrayList();
		for(Passenger p: b.getPassenger())
		{
			newList.add(passengerService.addPassenger(p));
		}
		Booking bbcn = new Booking(b.getUserId(), b.getBookingDateTime(), b.getTicketCost(), b.getScheduledFlight(), b.getNoOfPassengers(), newList);
		return bookingService.createBooking(bbcn);
	}
	
	@GetMapping("/getAllBookings")
	public List<Booking> getAllBookings(){
		
		return bookingRepo.findAll();
	}
	
	@PostMapping("/createPassenger")
	public Passenger createPassenger(@RequestBody Passenger p)
	{
		return passengerService.addPassenger(p);
		
	}
	
	@GetMapping("/viewPassenger/{passengerId}")
	public Passenger viewPassenger(@PathVariable BigInteger passengerId)
	{
		System.out.println(passengerService.viewPassenger(passengerId));
		return passengerService.viewPassenger(passengerId);
		
	}
	
	@GetMapping("/viewAllPassengers")
	public List<Passenger> viewAllPassenger()
	{
		return passengerRepo.findAll();
		
	}
}
