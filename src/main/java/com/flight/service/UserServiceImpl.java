package com.flight.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.dao.UserDao;
import com.flight.entities.User;
import com.flight.exceptions.RecordAlreadyPresentException;
import com.flight.exceptions.RecordNotFound;
import com.flight.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserDao userDao;

	public boolean checkIfUserAlreadyPresent(User u) {
		List<User> listUsers = userRepo.findAll();
		for (User user : listUsers) {
			if (user.getEmail().equals(u.getEmail())) {
				return true;
			}
		}

		return false;

	}

	@Override
	public ResponseEntity<?> createUser(User u) {

		try {
			if (!checkIfUserAlreadyPresent(u)) {
				User user = new User(u.getUserType(), u.getUserName(), u.getUserPassword(), u.getUserPhone(),
						u.getEmail());
				user = userDao.addUser(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else {
				System.out.println("User with Id: " + u.getUserId() + " user already exists!!");
				throw new RecordAlreadyPresentException("User with Id: " + u.getUserId() + " user already exists!!");
			}
		} catch (RecordAlreadyPresentException e) {

			return new ResponseEntity<>("user already in database", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> updateUser(User u) {
		try {
			if (userRepo.findById(u.getUserId()).get() != null) {
				userDao.updateUser(u);
				return new ResponseEntity<User>(u, HttpStatus.OK);
			} else {
				System.out.println("User with Id: " + u.getUserId() + " user doesn't exist!!");
				throw new RecordNotFound("User with Id: " + u.getUserId() + "user doesn't exist!!");
			}
		} catch (RecordNotFound e) {

			return new ResponseEntity<>("user already in database", HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public ResponseEntity<?> findUserById(BigInteger id) {
		// TODO Auto-generated method stub
		return null;
	}

}
