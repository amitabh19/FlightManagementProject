package com.flight.controllers;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.flight.dao.PassengerDao;
import com.flight.entities.Booking;
import com.flight.entities.Flight;
import com.flight.entities.GetSchedule;
import com.flight.entities.Passenger;
import com.flight.entities.ScheduledFlight;
import com.flight.entities.User;
import com.flight.exceptions.RecordAlreadyPresentException;
import com.flight.exceptions.RecordNotFound;
import com.flight.repositories.AirportRepository;
import com.flight.repositories.BookingRepository;
import com.flight.repositories.FlightRepository;
import com.flight.repositories.PassengerRepository;
import com.flight.repositories.ScheduleRepository;
import com.flight.repositories.ScheduledFlightRepository;
import com.flight.repositories.UserRepository;
import com.flight.service.BookingService;
import com.flight.service.FlightService;
import com.flight.service.PassengerService;
import com.flight.service.ScheduledFlightService;
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
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private ScheduledFlightService sfService;
	
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
	
	/**
	 * This function is used to add Flight object to flight database
	 * 
	 * @author Garima
	 * @param flight object
	 * @return Response Entity from service if flight object is added successfully
	 * @version 1.0
	 * @since 29-10-2020
	 */
	
	@PostMapping("/addFlight")
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public void addFlight(@RequestBody Flight flight) {
		flightService.addFlight(flight);
	}

	/**
	 * This function is used to get the list of all the flights
	 *  
	 * @author Garima
	 * @param None
	 * @return return List of all the flights 
	 * @version 1.0
	 * @since 29-10-2020
	 
	*/
	
	@GetMapping("/allFlight")
	public List<Flight> viewAllFlight() {
		return flightService.viewAllFlight();
	}

	/**
	 * This function is used to get the flight object using flight number
	 * 
	 * @author Garima
	 * @param flight object
	 * @return flight object from the corresponding flight number
	 * @version 1.0
	 * @since 29-10-2020
	 
	*/
	@GetMapping("/viewFlight/{id}")
	@ExceptionHandler(RecordNotFound.class)
	public Flight viewFlight(@PathVariable("id") BigInteger flightNo) {
		return flightService.viewFlight(flightNo);
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
	@PutMapping("/updateFlight")
	@ExceptionHandler(RecordNotFound.class)
	public void modifyFlight(@RequestBody Flight flight) {
		flightService.modifyFlight(flight);
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
	
	@DeleteMapping("/deleteFlight/{id}")
	//@ExceptionHandler(RecordNotFound.class)
	public void removeFlight(@PathVariable("id") BigInteger flightNumber) {
		flightService.deleteFlight(flightNumber);
	}
	
	/**
	 * This function is used to get the list of the ScheduledFlight
	 * 
	 * @author Garima
	 * @param None
	 * @return List of all the scheduled flights
	 * @version 1.0
	 * @since 29-10-2020
	 
	*/
	@GetMapping("/viewAll")
	public Iterable<ScheduledFlight> viewAllScheduledFlight() {
		return sfService.viewAllScheduledFlights();
	}
	
	
	/**
	 * This function is used to view Scheduled Flights using Flight number 
	 * 
	 * @author Garima
	 * @param Flight Number
	 * @return shows scheduled flights information using flight number provided
	 * @version 1.0
	 * @since 29-10-2020
	 */
	
	@GetMapping("/viewF")
	//@ExceptionHandler(RecordNotFound.class)
	public ResponseEntity<ScheduledFlight> viewScheduledFlightByFlightId(@RequestParam BigInteger flightId) {
		ScheduledFlight searchSFlight = (ScheduledFlight) sfService.viewScheduledFlightsByFlightNumber(flightId);
		if (searchSFlight == null) {
			return new ResponseEntity("Flight not present", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ScheduledFlight>(searchSFlight, HttpStatus.OK);
		}
	}
	
	/**
	 * This function is used to delete the scheduledFlight whose schedule ID is given
	 * 
	 * @author Garima
	 * @param schedule ID
	 * @return ScheduledFlight with the given Schedule ID is deleted
	 */
	
	@DeleteMapping("/delete")
	//@ExceptionHandler(RecordNotFound.class)
	public ResponseEntity<ScheduledFlight> deleteScheduledFlight(@RequestParam BigInteger flightId) {
		return sfService.deleteScheduledFlight(flightId);
	}
	
	/**
	 * This function is used to modify the ScheduledFlight object by using flight,schedule objects and
	 * number of available seats
	 * 
	 * @author Garima
	 * @param ScheduledFlight object
	 * @return modify the schedule for the given Flight
	 * @version 1.0
	 * @since 29-10-2020
	 
	*/
	@PutMapping("/modify")
	//@ExceptionHandler(RecordNotFound.class)
	public ResponseEntity<ScheduledFlight> modifyScheduleFlight(@RequestBody ScheduledFlight scheduleFlight) {
		
		ResponseEntity<ScheduledFlight> modifySFlight = sfService.modifyScheduledFlight(scheduleFlight.getFlight(),scheduleFlight.getSchedule(),scheduleFlight.getAvailableSeats());
		if (modifySFlight == null) {
			return new ResponseEntity("Flight not modified", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<ScheduledFlight>(HttpStatus.OK);
		}
	}
	
	/**
	 * This function is used to get ScheduledFlight object using scheduleId
	 * 
	 * @author Garima
	 * @param Schedule Id
	 * @return shows the schedules for the given schedule Id
	 * @version 1.0
	 * @since 29-10-2020
	 
	*/
	@GetMapping("/viewSf")
	//@ExceptionHandler(RecordNotFound.class)
	public ResponseEntity<ScheduledFlight> viewScheduledFlight(@RequestParam BigInteger scheduledFlightId){
		ScheduledFlight searchSFlight = sfService.viewScheduledFlightById(scheduledFlightId);
		if (searchSFlight == null) {
			return new ResponseEntity("Flight not present", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ScheduledFlight>(searchSFlight, HttpStatus.OK);
		}
	}
	
	/**
	 * This function is used to add the ScheduledFlight object to database
	 * 
	 * @author Garima
	 * @param ScheduleFlight object to be added
	 * @return scheduledFlight object added
	 * @version 1.0
	 * @since 29-10-2020
	 
*/

	
	@PostMapping("/addSCheduledFlights")
	public ResponseEntity<ScheduledFlight> addSF(@RequestBody ScheduledFlight scheduledFlight)
	{
		return sfService.scheduleFlight(scheduledFlight);
	}
}
