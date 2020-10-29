package com.flight.service;

import java.math.BigInteger;
import java.util.List;
import com.flight.entities.Booking;
import com.flight.entities.GetSchedule;
import com.flight.entities.ScheduledFlight;

public interface BookingService {
	List<ScheduledFlight> searchBooking(GetSchedule schedule);

	List<Booking> getUserBookings(BigInteger userId);

	 Booking createBooking(Booking b);

}
