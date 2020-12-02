package com.cg.onlineeyeclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineeyeclinic.Exception.AppointmentIdNotFoundException;
import com.cg.onlineeyeclinic.Exception.InvalidAppointmentException;
import com.cg.onlineeyeclinic.dao.IAppointmentRepository;
import com.cg.onlineeyeclinic.model.Appointment;
@Service(value="appointmentService")
@Transactional
public class AppointmentServiceImpl implements IAppointmentService{
	@Autowired
	private IAppointmentRepository appointmentRepository;

	@Override
	public Appointment bookAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public Appointment updateAppointment(Appointment appointment) {
		Integer appointmentId = appointment.getAppointmentId();
		Optional<Appointment> appointments = appointmentRepository.findById(appointmentId);
		if(appointments.isEmpty())
		{
			throw new InvalidAppointmentException("Appointment with Id "+appointmentId+" doesn't exist");
		}
		appointment = appointmentRepository.save(appointment);
		return appointment;
	}

	@Override
	public Appointment cancelAppointment(Integer appointmentId)  {
		Optional<Appointment> appointments = appointmentRepository.findById(appointmentId);
		if(appointments.isEmpty())
		{
			throw new AppointmentIdNotFoundException("Appointment with Id "+appointmentId+" doesn't exist");
		}
		else
			appointmentRepository.deleteById(appointmentId);
		return null;
	 
	}

	@Override
	public Optional<Appointment> findAppointmentById(Integer appointmentId) {
		Optional<Appointment> appointments = appointmentRepository.findById(appointmentId);
	    if(appointments.isEmpty())
	    {
	    	throw new AppointmentIdNotFoundException("Appointment with Id "+appointmentId+" doesn't exist");
	    }
		return appointments;
	}

	@Override
	public List<Appointment> viewAllAppointments() {
		List<Appointment> list = appointmentRepository.findAll();
		return list;
	}

	@Override
	public List<Appointment> viewAppointments(LocalDate date) {
		List<Appointment> list = appointmentRepository.viewAppointments(date);
		return list;
	}

}
