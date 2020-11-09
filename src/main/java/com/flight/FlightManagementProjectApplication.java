package com.flight;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import com.flight.entities.Airport;
import com.flight.entities.Booking;
import com.flight.entities.Flight;
import com.flight.entities.Passenger;
import com.flight.entities.Schedule;
import com.flight.entities.ScheduledFlight;
import com.flight.entities.User;
import com.flight.repositories.AirportRepository;
import com.flight.repositories.BookingRepository;
import com.flight.repositories.FlightRepository;
import com.flight.repositories.PassengerRepository;
import com.flight.repositories.ScheduleRepository;
import com.flight.repositories.ScheduledFlightRepository;
import com.flight.repositories.UserRepository;

@EntityScan(basePackages = "com.flight.entities")
@SpringBootApplication(scanBasePackages = { "com.flight.repositories", "com.flight.enitites", "com.flight.controllers",
		"com.flight.dao", "com.flight.service","com.flight.exceptions" })
public class FlightManagementProjectApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(FlightManagementProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		User u = new User("user", "amitabh", "amitabhs", "1234567890", "amitabh@gmail.com");
		User u1 = new User("admin", "sahil", "sahilpass", "7550172933", "admin@gmail.com");
		User u2 = new User("user", "test", "testpass", "3434343434", "test@u.com");
		userRepo.save(u);
		userRepo.save(u1);
		userRepo.save(u2);

		Airport a = new Airport("BarcelonaAirport", "Barcelona", "BCN");
		Airport b = new Airport("MunichAirport", "Munich", "MCN");

		airRepo.save(a);
		airRepo.save(b);

		Flight f = new Flight(new BigInteger("4334367"), "Etihad", "Boeing", 700);
		f = flightRepo.save(f);

		LocalDateTime d = LocalDateTime.now();
		LocalDateTime d1 = LocalDateTime.now();
		Schedule sch = new Schedule(a, b, d, d1);
		sch = scheduleRepo.save(sch);
		ScheduledFlight sf = new ScheduledFlight(f, 700, sch);
		sf = sfrRepo.save(sf);

		Passenger p = new Passenger("adsa", new BigInteger("4334367"), 21, 222);
		p = passengerRepo.save(p);

		Passenger p1 = new Passenger("adsa1", new BigInteger("4334357"), 11, 212);
		p1 = passengerRepo.save(p1);

		List<Passenger> lp = new ArrayList<Passenger>();
		lp.add(p);
		Booking bbcn = new Booking(u, d, 500.0, sf, 1, lp);
		bbcn = bookingRepo.save(bbcn);
		List<Passenger> lp1 = new ArrayList<Passenger>();
		lp1.add(p1);
		Booking bbcn1 = new Booking(u1, d, 500.0, sf, 1, lp1);
		bbcn1 = bookingRepo.save(bbcn1);
		//userRepo.deleteById(u.getUserId());
	}

}
