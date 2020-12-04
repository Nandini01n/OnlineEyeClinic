package com.cg.onlineeyeclinic.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineeyeclinic.exception.DoctorIdNotFoundException;
import com.cg.onlineeyeclinic.model.Appointment;
import com.cg.onlineeyeclinic.model.Doctor;
import com.cg.onlineeyeclinic.model.Tests;
import com.cg.onlineeyeclinic.repository.IDoctorRepository;

@Service(value = "doctorService")
@Transactional
public class DoctorServiceImpl implements IDoctorService {

	private static final String ERROR_MESSAGE = "Doctor ID Not Found";

	@Autowired
	private IDoctorRepository doctorRepository;

	@Autowired
	private IAppointmentService appointmentService;

	@Autowired
	private ITestService testService;

	@Override
	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
	public Doctor updateDoctor(Doctor doctor) {
		Long doctorId = doctor.getDoctorId();
		Optional<Doctor> doc = doctorRepository.findById(doctorId);
		if (doc.isEmpty())
			throw new DoctorIdNotFoundException(ERROR_MESSAGE);
		return doctorRepository.save(doctor);
	}

	@Override
	public Doctor deleteDoctor(Long doctorId) {
		Optional<Doctor> doc = doctorRepository.findById(doctorId);
		if (doc.isEmpty())
			throw new DoctorIdNotFoundException(ERROR_MESSAGE);
		doctorRepository.deleteById(doctorId);
		return null;

	}

	@Override
	public Optional<Doctor> viewDoctor(Long doctorId) {
		Optional<Doctor> doc = doctorRepository.findById(doctorId);
		if (doc.isEmpty())
			throw new DoctorIdNotFoundException(ERROR_MESSAGE);
		return doc;

	}

	@Override
	public List<Doctor> viewDoctorsList() {
		return doctorRepository.findAll();
	}

	@Override
	public List<Appointment> viewAppointments() {
		return appointmentService.viewAllAppointments();
	}

	@Override
	public Tests createTest(Tests test) {
		return testService.addTest(test);
	}

}
