package com.cg.onlineeyeclinic.exception;

@SuppressWarnings("serial")
public class AppointmentIdNotFoundException extends RuntimeException {
public AppointmentIdNotFoundException() {
	
}
public AppointmentIdNotFoundException(String message) {
super(message);
}
}
