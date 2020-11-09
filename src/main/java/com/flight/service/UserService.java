package com.flight.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.flight.entities.AuthLogin;
import com.flight.entities.User;
import com.flight.exceptions.RecordIsEmptyException;
import com.flight.exceptions.RecordNotFoundException;

public interface UserService {
	public ResponseEntity<?> createUser(User newUser);

	public ResponseEntity<?> updateUser(User newUser);

	public ResponseEntity<?> viewUserById(BigInteger id) throws RecordNotFoundException;

	public boolean validateUser(User u);
	
	public List<User> displayAllUsers() throws RecordIsEmptyException; 
	
	public String deleteUser(BigInteger id);
	
	public ResponseEntity<?> authenticateUser(AuthLogin authLogin);
}
