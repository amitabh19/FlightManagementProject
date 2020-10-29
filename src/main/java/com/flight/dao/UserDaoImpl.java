package com.flight.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flight.entities.User;
import com.flight.repositories.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User addUser(User u) {
		return userRepo.save(u);
	}

	@Override
	public User updateUser(User u) {
		return userRepo.save(u);
	}

}
