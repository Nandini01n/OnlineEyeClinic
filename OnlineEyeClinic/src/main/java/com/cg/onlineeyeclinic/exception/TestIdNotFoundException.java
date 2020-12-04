package com.cg.onlineeyeclinic.exception;

@SuppressWarnings("serial")
public class TestIdNotFoundException extends RuntimeException {
	
	public TestIdNotFoundException(int id) {
		System.out.println(id + " Not Found");
	}
public TestIdNotFoundException(String message) {
	super(message);
}
}
