package com.cg.onlineeyeclinic.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);
	}

	@ExceptionHandler(value = PatientIdNotFoundException.class)
	public ResponseEntity<Object> handlePatientIdNotFoundException(PatientIdNotFoundException exception,
			WebRequest webRequest) {
		ExceptionResponse errorDetails = new ExceptionResponse(404, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ReportIdNotFoundException.class)
	public ResponseEntity<Object> handleReportIdNotFoundException(ReportIdNotFoundException exception,
			WebRequest webRequest) {
		ExceptionResponse errorDetails = new ExceptionResponse(404, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = AppointmentIdNotFoundException.class)
	public ResponseEntity<Object> handleAppointmentIdNotFoundException(AppointmentIdNotFoundException exception,
			WebRequest webRequest) {
		ExceptionResponse errorDetails = new ExceptionResponse(404, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InvalidAppointmentException.class)
	public ResponseEntity<Object> handleInvalidAppointmentException(InvalidAppointmentException exception,
			WebRequest webRequest) {
		ExceptionResponse errorDetails = new ExceptionResponse(404, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Object> handleDoctorIdNotFoundException(DoctorIdNotFoundException exception,
			WebRequest webRequest) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(404);
		exceptionResponse.setTimeStamp(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = TestIdNotFoundException.class)
	public ResponseEntity<Object> handleTestIdNotFoundException(TestIdNotFoundException exception,
			WebRequest webRequest) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(404, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = SpectaclesIdNotFoundException.class)
	public ResponseEntity<Object> handlePatientIdNotFoundException(SpectaclesIdNotFoundException exception,
			WebRequest webRequest) {
		ExceptionResponse errorDetails = new ExceptionResponse(404, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
