package com.cg.onlineeyeclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlineeyeclinic.model.Patient;


/** IPatientRepository interface provides abstract methods to perform CRUD operations on Patient entity, Book appointment, View report and tests
 * 
 * @author Prudhvi Mypati
 * 
 */


@Repository(value="patientRepository")
public interface IPatientRepository extends JpaRepository<Patient, Long>{
		
}
