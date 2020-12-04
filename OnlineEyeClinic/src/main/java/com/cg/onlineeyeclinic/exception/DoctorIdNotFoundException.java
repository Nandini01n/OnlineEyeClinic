package com.cg.onlineeyeclinic.exception;

@SuppressWarnings("serial")
public class DoctorIdNotFoundException extends RuntimeException {
	
public DoctorIdNotFoundException(String message) {
	super(message);
}
}
