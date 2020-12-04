package com.cg.onlineeyeclinic.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.cg.onlineeyeclinic.model.Doctor;
import com.cg.onlineeyeclinic.model.Tests;
import com.cg.onlineeyeclinic.service.IDoctorService;

/**
 * 
 * @author Kondraju Praneeth
 *
 */

/*
 * Rest Controller is combination of Annotations: Controller + ResponseBody
 */
@RestController
//@RequestMapping("/api/doctor")
@Validated
public class DoctorController {

	@Autowired
	private IDoctorService doctorService;

	/*
	 * To insert Doctor record to DB
	 */
	@PostMapping("/add_doctor")
	public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor) {
		doctor = doctorService.addDoctor(doctor);
		return new ResponseEntity<>(doctor, HttpStatus.OK);
	}

	/*
	 * To update Doctor Record in DB if exists.
	 */
	@PutMapping("/update_doctor")
	public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor) {
		doctor = doctorService.updateDoctor(doctor);
		return new ResponseEntity<>(doctor, HttpStatus.OK);
	}

	/*
	 * To Delete Doctor Record from DB if exists.
	 */
	@DeleteMapping("/delete_doctor_by_id/{doctorId}")
	public ResponseEntity<String> deleteDoctorById(@PathVariable("doctorId") Long doctorId) {
		doctorService.deleteDoctor(doctorId);
		return new ResponseEntity<>("Doctor Deleted Successfully", HttpStatus.OK);
	}

	/*
	 * To view Doctor Record from DB if exists
	 */
	@GetMapping("/view_doctor_by_id/{doctorId}")
	public ResponseEntity<Optional<Doctor>> viewDoctorById(@PathVariable("doctorId") Long doctorId) {
		Optional<Doctor> doctor = doctorService.viewDoctor(doctorId);
		return new ResponseEntity<>(doctor, HttpStatus.OK);
	}

	/*
	 * To view All Records of Doctor from Doctor DB
	 */
	@GetMapping("/view_doctors_list")
	public ResponseEntity<List<Doctor>> viewAllDoctors() {
		return new ResponseEntity<>(doctorService.viewDoctorsList(), HttpStatus.OK);
	}

	/*
	 * To view all records form Appointment DB
	 */
	@GetMapping("/view_all_appointments_by_doctor")
	public ResponseEntity<List<Appointment>> viewAllAppointments() {
		return new ResponseEntity<>(doctorService.viewAppointments(), HttpStatus.OK);
	}

	/*
	 * To add test to Test DB
	 */
	@PostMapping("/add_test_by_doctor")
	public ResponseEntity<Tests> addTest(@Valid @RequestBody Tests test) {
		test = doctorService.createTest(test);
		return new ResponseEntity<>(test, HttpStatus.OK);
	}

}
