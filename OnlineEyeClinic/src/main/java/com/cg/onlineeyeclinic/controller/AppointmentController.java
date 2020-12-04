package com.cg.onlineeyeclinic.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineeyeclinic.model.Appointment;
import com.cg.onlineeyeclinic.service.IAppointmentService;

@Validated
@RestController
//@RequestMapping("api/appointments")
public class AppointmentController {

	@Autowired
	private IAppointmentService appointmentService;
    //BOOKING AN APPOINTMENT
	@PostMapping("/book_appointment")
	public ResponseEntity<Appointment> bookAppointment(@Valid @RequestBody Appointment appointment) {
		Appointment entity = appointmentService.bookAppointment(appointment);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
    //UPDATING AN APPOINTMENT
	@PutMapping("/update_appointment")
	public ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment appointment) {
		Appointment entity = appointmentService.updateAppointment(appointment);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
    //DELETING AN APPOINTMENT
	@DeleteMapping("/delete_appointment_by_id/{appointmentId}")
	public ResponseEntity<String> cancelAppointment(@PathVariable("appointmentId") Integer appointmentId) {
		appointmentService.cancelAppointment(appointmentId);
		return new ResponseEntity<>("Appointment Deleted", HttpStatus.OK);
	}
    //VIEW APPOINTMENT BY ID
	@GetMapping("/find_appointment_by_id/{appointmentId}")
	public ResponseEntity<Optional<Appointment>> findAppointmentById(
			@PathVariable("appointmentId") Integer appointmentId) {
		Optional<Appointment> appointment = appointmentService.findAppointmentById(appointmentId);
		return new ResponseEntity<>(appointment, HttpStatus.OK);
	}
    //VIEW ALL APPOINTMENTS
	@GetMapping("/view_all_appointments")
	public ResponseEntity<List<Appointment>> viewAllAppointments() {
		List<Appointment> appointmentList = appointmentService.viewAllAppointments();
		return new ResponseEntity<>(appointmentList, HttpStatus.OK);
	}
    //VIEW APPOINTMENTS BY DATE
	@GetMapping("/view_appointment_by_date/{date}")
	public ResponseEntity<List<Appointment>> viewAppointments(
			@Valid @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		List<Appointment> appointmentListByDate = appointmentService.viewAppointments(date);
		return new ResponseEntity<>(appointmentListByDate, HttpStatus.OK);
	}
	
}
