package com.cg.onlineeyeclinic.exception;

@SuppressWarnings("serial")
//@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Id not Found")
public class SpectaclesIdNotFoundException extends RuntimeException {
	
public SpectaclesIdNotFoundException(int id) {
	System.out.println(id + " Not Found");
}
public SpectaclesIdNotFoundException(String message) {
	super(message);
}
}
