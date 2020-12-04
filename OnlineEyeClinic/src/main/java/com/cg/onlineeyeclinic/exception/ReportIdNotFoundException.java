package com.cg.onlineeyeclinic.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Id not Found")
public class ReportIdNotFoundException extends RuntimeException {
	
	/**This is user defined Exception class for ReportId not found
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ReportIdNotFoundException(String message) {
		super(message);
	}
	
}
