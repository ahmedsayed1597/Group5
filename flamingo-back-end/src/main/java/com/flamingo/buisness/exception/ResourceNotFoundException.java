package com.flamingo.buisness.exception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException() {
	}
    
	public ResourceNotFoundException(String message) {
		super(message);
	}


}
