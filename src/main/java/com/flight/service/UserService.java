package com.flight.service;

import java.math.BigInteger;

import org.springframework.http.ResponseEntity;

import com.flight.entities.User;

public interface UserService {
	public ResponseEntity<?> createUser(User newUser);
	public ResponseEntity<?> updateUser(User newUser);
	ResponseEntity<?> findUserById(BigInteger id);
}
