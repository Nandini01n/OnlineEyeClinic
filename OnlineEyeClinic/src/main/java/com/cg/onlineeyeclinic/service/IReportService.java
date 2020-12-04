package com.cg.onlineeyeclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.cg.onlineeyeclinic.model.Report;
import com.cg.onlineeyeclinic.model.Spectacles;

public interface IReportService {

	Report addReport(Report report);

	Report updateReport(Report report);

	Report deleteReport(Integer reportId);

	Optional<Report> viewReportByPatientId(Long patientId);

	List<Report> viewReportList();
	
	List<Report> viewReportsByDate(LocalDate date);

	List<Spectacles> viewSpectaclesList();
}
