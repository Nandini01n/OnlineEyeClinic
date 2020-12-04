package com.cg.onlineeyeclinic.exception;

@SuppressWarnings("serial")
public class InvalidAppointmentException extends RuntimeException {
public InvalidAppointmentException() {
}
public InvalidAppointmentException(String message) {
	super(message);
}
}
