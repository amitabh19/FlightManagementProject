package com.flight.entities;

public class AuthLogin {
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthLogin(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public AuthLogin() {
	}

	@Override
	public String toString() {
		return "AuthLogin [email=" + email + ", password=" + password + "]";
	}

}
