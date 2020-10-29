package com.flight.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.dao.BookingDao;
import com.flight.entities.Booking;
import com.flight.entities.GetSchedule;
import com.flight.entities.ScheduledFlight;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingDao bookDao;

	/**
	 * This function is used to search the bookings when arrival and departure
	 * destination is entered along with the date
	 * 
	 * @param schedule will return the schedule input by the user
	 * @return will return the list of flights according to the schedule
	 * 
	 * @author Sahil Narula
	 * @version 1.0
	 * @since 28-10-2020
	 **/
	@Override
	public List<ScheduledFlight> searchBooking(GetSchedule schedule) {

		return bookDao.searchBooking(schedule);
	}

	/**
	 * This function is used to get the list of bookings by user id
	 * 
	 * @param userId will return the schedule input by the user
	 * @return will return the list of bookings in the particular user id
	 * 
	 * @author Sahil Narula
	 * @version 1.0
	 * @since 28-10-2020
	 **/
	@Override
	public List<Booking> getUserBookings(BigInteger userId) {
		return bookDao.getUserBookings(userId);
	}

	@Override
	public Booking createBooking(Booking b) {
		// TODO Auto-generated method stub
		return bookDao.createBooking(b);
	}

}
