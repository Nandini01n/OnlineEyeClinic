package com.cg.onlineeyeclinic.service;

import java.util.List;
import java.util.Optional;

import com.cg.onlineeyeclinic.model.Appointment;
import com.cg.onlineeyeclinic.model.Doctor;
import com.cg.onlineeyeclinic.model.Tests;

public interface IDoctorService {
	
	Doctor addDoctor(Doctor doctor);
	Doctor updateDoctor(Doctor doctor);
	Doctor deleteDoctor(Long doctorId);
	Optional<Doctor> viewDoctor(Long doctorId);
	List<Doctor> viewDoctorsList();
	List<Appointment> viewAppointments();
	Tests createTest(Tests test);

}
