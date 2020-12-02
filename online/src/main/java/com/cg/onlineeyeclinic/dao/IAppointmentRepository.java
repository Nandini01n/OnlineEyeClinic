package com.cg.onlineeyeclinic.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.onlineeyeclinic.model.Appointment;
public interface IAppointmentRepository extends JpaRepository<Appointment, Integer>{
	@Query("select d from Appointment d where d.dateOfAppointment like ?1")
	List<Appointment> viewAppointments(LocalDate date);
	
}
