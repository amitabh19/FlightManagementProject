package com.flight.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.dao.PassengerDao;
import com.flight.entities.Passenger;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerDao passengerDao;
	@Override
	public Passenger addPassenger(Passenger p) {
		// TODO Auto-generated method stub
		return passengerDao.addPassenger(p);
	}

	@Override
	public Passenger viewPassenger(BigInteger id) {
		// TODO Auto-generated method stub
		return passengerDao.viewPassenger(id);
	}

	@Override
	public Passenger getPassengerByPnr(BigInteger pnrNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
