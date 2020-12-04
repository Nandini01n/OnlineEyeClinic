package com.cg.onlineeyeclinic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.onlineeyeclinic.model.Appointment;
/** IAppointmentRepository interface provides abstract methods to perform CRUD operations on Appointment entity, view appointments by date.
 * 
 * @author Nandini Nanabala
 * 
 */
@Repository(value =  "appointmentRepository")
public interface IAppointmentRepository extends JpaRepository<Appointment, Integer>{
	@Query("select d from Appointment d where d.dateOfAppointment like ?1")
	List<Appointment> viewAppointments(LocalDate date);
	
}
