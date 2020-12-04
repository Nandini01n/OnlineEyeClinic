package com.cg.onlineeyeclinic.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.onlineeyeclinic.model.Report;



/** IReportRepository interface provides abstract methods to perform CRUD operations on report entity,  View reports by date and view Spectacles List.
 * 
 * @author D,Pradeep Kumar
 * 
 */

@Repository(value="reportRepository")
public interface IReportRepository extends JpaRepository<Report, Integer>{
	
	
	@Query("select rep from Report rep where rep.dateOfReport like ?1")
	List<Report> viewReportsByDate(LocalDate date);

	@Query("select r from Report r where r.patientId like ?1")
	Optional<Report> findByPatientId(Long patientId);



}
