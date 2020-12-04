package com.cg.onlineeyeclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineeyeclinic.exception.AppointmentIdNotFoundException;
import com.cg.onlineeyeclinic.exception.DoctorIdNotFoundException;
import com.cg.onlineeyeclinic.exception.InvalidAppointmentException;
import com.cg.onlineeyeclinic.model.Appointment;
import com.cg.onlineeyeclinic.model.Doctor;
import com.cg.onlineeyeclinic.repository.IAppointmentRepository;

/**
 * AppointmentServiceImpl class provides methods to perform CRUD operations on Appointment entity and view Appointment by date.
 * Appointment entity
 * 
 * @author Nandini Nanabala
 * 
 */
@Service(value = "appointmentService")
@Transactional
public class AppointmentServiceImpl implements IAppointmentService {

	private static final String ERROR_MESSAGE = "Appointment ID Not Found";

	@Autowired
	private IAppointmentRepository appointmentRepository;

	@Autowired
	private IDoctorService doctorService;

	/**
	 * This method Book Appointment by calling the save method
	 * @param appointment - Appointment entity details
	 * @return appointment - Appointment entity details
	 * 
	 */
	@Override
	public Appointment bookAppointment(Appointment appointment) {
		Optional<Doctor> doctor = doctorService.viewDoctor(appointment.getDoctorId());
		if (doctor.isEmpty()) {
			throw new DoctorIdNotFoundException("Doctor Id does not exist");
		}
		return appointmentRepository.save(appointment);
	}
	/**
	 * This method update Appointment by calling the save method
	 * @param appointment - Appointment entity details 
	 * @return appointment - Appointment entity details
	 * 
	 */
	@Override
	public Appointment updateAppointment(Appointment appointment) {
		Integer appointmentId = appointment.getAppointmentId();
		Optional<Appointment> appointments = appointmentRepository.findById(appointmentId);
		if (appointments.isEmpty()) {
			throw new InvalidAppointmentException(ERROR_MESSAGE);
		}
		Optional<Doctor> doctor = doctorService.viewDoctor(appointment.getDoctorId());
		if (doctor.isEmpty()) {
			throw new DoctorIdNotFoundException("Doctor Id does not exist");
		}
		appointment = appointmentRepository.save(appointment);
		return appointment;
	}
	/**
	 * This method cancel Appointment by calling the deleteById method
	 * @param appointmentId - AppointmentId
	 * @return null
	 * 
	 */
	@Override
	public Appointment cancelAppointment(Integer appointmentId) {
		Optional<Appointment> appointments = appointmentRepository.findById(appointmentId);
		if (appointments.isEmpty()) {
			throw new AppointmentIdNotFoundException(ERROR_MESSAGE);
		}

		appointmentRepository.deleteById(appointmentId);

		return null;

	}
	/**
	 * This method takes Appointment Id by calling the findById method and returns Appointment
	 * @param appointmentId - integer value to display appointment based on appointmentId
	 * @return appointment - Appointment entity details
	 * 
	 */
	@Override
	public Optional<Appointment> findAppointmentById(Integer appointmentId) {
		Optional<Appointment> appointments = appointmentRepository.findById(appointmentId);
		if (appointments.isEmpty()) {
			throw new AppointmentIdNotFoundException(ERROR_MESSAGE);
		}
		return appointments;
	}
	/**
	 * This method returns the List Of Appointments in Appointment Table
	 * If appointment list is null, returns null
	 * @return list of appointments
	 * 
	 */
	@Override
	public List<Appointment> viewAllAppointments() {
		return appointmentRepository.findAll();
	}
	/**
	 * This method returns the List Of Appointments By Date
	 * @param date - LocalDate to display appointment based on date
	 * @return list of appointments by date
	 */
	@Override
	public List<Appointment> viewAppointments(LocalDate date) {
		return appointmentRepository.viewAppointments(date);
	}

}
