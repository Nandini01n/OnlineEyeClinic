package com.cg.onlineeyeclinic.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.cg.onlineeyeclinic.model.Patient;
import com.cg.onlineeyeclinic.model.Report;
import com.cg.onlineeyeclinic.model.Tests;
import com.cg.onlineeyeclinic.service.IPatientService;

@RestController
//@RequestMapping("api/patient")
@Validated
public class PatientController {

	@Autowired
	private IPatientService patientService;

	// ADDING A PATIENT
	@PostMapping("/add_patient")
	public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
		patient = patientService.addPatient(patient);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	// UPDATING A PATIENT
	@PutMapping("/update_patient")
	public ResponseEntity<Patient> updatePatient(@Valid @RequestBody Patient patient) {
		patient = patientService.updatePatient(patient);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	// DELETING A PATIENT
	@DeleteMapping("/delete_patient_by_id/{patientId}")
	public ResponseEntity<String> deletePatientById(@PathVariable("patientId") Long patientId) {
		patientService.deletePatient(patientId);
		return new ResponseEntity<>("Patient Deleted", HttpStatus.OK);
	}

	// GET PATIENT BY ID
	@GetMapping("/find_patient_by_id/{patientId}")
	public ResponseEntity<Optional<Patient>> findPatientById(@PathVariable("patientId") Long patientId) {
		Optional<Patient> patient = patientService.findPatientById(patientId);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	// GET ALL PATIENTS LIST
	@GetMapping("/view_all_patients")
	public ResponseEntity<List<Patient>> getPatientsList() {
		List<Patient> patientList = patientService.viewPatientList();
		return new ResponseEntity<>(patientList, HttpStatus.OK);
	}

	// BOOKING AN APPOINTMENT 
	@PostMapping("/book_appointment_by_patient")
	public ResponseEntity<Appointment> bookAppointment(@Valid @RequestBody Appointment appointment) {
		appointment = patientService.bookAppointment(appointment);
		return new ResponseEntity<>(appointment, HttpStatus.OK);
	}
    // VIEW APPOINTMENT BY ID
	@GetMapping("/find_appointment_by_patient_id/{appointmentId}")
	public ResponseEntity<Optional<Appointment>> findAppointmentById(
			@PathVariable("appointmentId") Integer appointmentId) {
		Optional<Appointment> appointment = patientService.findAppointmentById(appointmentId);
		return new ResponseEntity<>(appointment, new HttpHeaders(), HttpStatus.OK);
	}

	// VIEW REPORT BY ID

	@GetMapping("/view_report_by_patient_id/{patientId}")
	public ResponseEntity<Optional<Report>> viewReportById(@PathVariable("patientId") Long patientId) {
		Optional<Report> report = patientService.findReportByPatientId(patientId);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	// GET ALL Tests LIST
	@GetMapping("/view_all_tests")
	public ResponseEntity<List<Tests>> getTestsList() {
		List<Tests> testsList = patientService.viewAllTests();
		return new ResponseEntity<>(testsList, HttpStatus.OK);
	}
}
