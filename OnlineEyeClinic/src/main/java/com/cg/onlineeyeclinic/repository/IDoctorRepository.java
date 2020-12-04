package com.cg.onlineeyeclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlineeyeclinic.model.Doctor;

@Repository(value = "doctorRepository")
public interface IDoctorRepository extends JpaRepository<Doctor, Long>{

}
