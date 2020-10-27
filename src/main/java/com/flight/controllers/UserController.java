package com.flight.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.entities.Booking;
import com.flight.entities.User;
import com.flight.repositories.AirportRepository;
import com.flight.repositories.BookingRepository;
import com.flight.repositories.FlightRepository;
import com.flight.repositories.PassengerRepository;
import com.flight.repositories.ScheduleRepository;
import com.flight.repositories.ScheduledFlightRepository;
import com.flight.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
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
	
	@GetMapping("/users")
	public List<User> getUsers()
	{
		return userRepo.findAll();
	}
	
	@GetMapping("/bookings")
	public List<Booking> getAllBooking()
	{
		return bookingRepo.findAll();
	}
	
	@RequestMapping("hello")
	public String yo()
	{
		return "helloo";
		
	}
}
