package com.example.demo.exceptions;



public class ResourceNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7267963160306970805L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
