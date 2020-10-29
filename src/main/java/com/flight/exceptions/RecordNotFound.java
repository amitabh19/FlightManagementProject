package com.flight.exceptions;

public class RecordNotFound extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordNotFound(String s) {
		super(s);
	}

}
