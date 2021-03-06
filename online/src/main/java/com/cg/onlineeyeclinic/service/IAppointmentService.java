package com.cg.onlineeyeclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.cg.onlineeyeclinic.model.Appointment;
@Service
public interface IAppointmentService{
	Appointment bookAppointment(Appointment appointment);
	Appointment updateAppointment(Appointment appointment);
	Appointment cancelAppointment(Integer appointmentId);
	Optional<Appointment> findAppointmentById(Integer appointmentId);
	List<Appointment> viewAllAppointments();
	List<Appointment> viewAppointments(LocalDate date);
}
