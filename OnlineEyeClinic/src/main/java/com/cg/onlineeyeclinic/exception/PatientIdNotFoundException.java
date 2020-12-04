package com.cg.onlineeyeclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Id not Found")
public class PatientIdNotFoundException extends RuntimeException {
	
	/**This is user defined Exception class for PatientId not found
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PatientIdNotFoundException(String message) {
		super(message);
	}
	
}
