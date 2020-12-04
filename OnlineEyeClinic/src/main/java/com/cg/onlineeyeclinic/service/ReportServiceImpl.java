package com.cg.onlineeyeclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineeyeclinic.exception.PatientIdNotFoundException;
import com.cg.onlineeyeclinic.exception.ReportIdNotFoundException;
import com.cg.onlineeyeclinic.model.Patient;
import com.cg.onlineeyeclinic.model.Report;
import com.cg.onlineeyeclinic.model.Spectacles;
import com.cg.onlineeyeclinic.repository.IReportRepository;

/**
 * ReportServiceImpl class provides methods to perform CRUD operations on Report Entity, View reports by date and view Spectacles List.
 * 
 * 
 * @author D,Pradeep kumar
 */

@Service(value = "reportService")
@Transactional
public class ReportServiceImpl implements IReportService {

	private static final String ERROR_MESSAGE = "Report ID Not Found";

	@Autowired
	private IReportRepository reportRepository;

	@Autowired
	private IPatientService patientService;

	@Autowired
	private ISpectaclesService spectaclesService;

	/**
	 * This method Adds Report by calling the save method
	 * 
	 * @param report - report entity details
	 * @return report - report entity details
	 * 
	 */
	@Override
	public Report addReport(Report report) {
		return reportRepository.save(report);

	}

	/**
	 * This method Updates Report by calling the save method
	 * 
	 * @param report - report entity details
	 * @return report - report entity details
	 * 
	 */
	@Override
	public Report updateReport(Report report) {
		Integer reportId = report.getReportId();
		Optional<Report> reports = reportRepository.findById(reportId);
		if (reports.isEmpty())
			throw new ReportIdNotFoundException(ERROR_MESSAGE);
		return reportRepository.save(report);
	}

	/**
	 * This method Deletes Report by calling the deleteById method
	 * 
	 * @param reportId - Report Id
	 * @return null
	 * 
	 */
	@Override
	public Report deleteReport(Integer reportId) {
		Optional<Report> report = reportRepository.findById(reportId);

		if (report.isEmpty())
			throw new ReportIdNotFoundException(ERROR_MESSAGE);

		reportRepository.deleteById(reportId);

		return null;
	}

	/**
	 * This method takes Report Id and returns the Report based on Id
	 * 
	 * @param reportId - integer value to display report based on patientId
	 * @return report - report entity details
	 */

	@Override
	public Optional<Report> viewReportByPatientId(Long patientId) {
		Optional<Patient> patient = patientService.findPatientById(patientId);
		if (patient.isEmpty()) {
			throw new PatientIdNotFoundException("Patient ID does not exist");
		}
		return reportRepository.findByPatientId(patientId);
	}

	/**
	 * This method returns the Report List present in the Report Table
	 * 
	 * If Report list is null, returns null
	 * 
	 * @return list of reports
	 * 
	 */

	@Override
	public List<Report> viewReportList() {
		return reportRepository.findAll();
	}

	/**
	 * This method returns the Report List present in the Report Table
	 * 
	 * If Report list is null, returns null
	 * 
	 * @return list of reports in date
	 * 
	 */
	@Override
	public List<Report> viewReportsByDate(LocalDate date) {
		return reportRepository.viewReportsByDate(date);
	}
	/**
	 * This method returns the Spectacles List present in the Spectacles Table
	 * 
	 * If Spectacles list is null, returns null
	 * 
	 * @return list of Spectacles
	 * 
	 */
	@Override
	public List<Spectacles> viewSpectaclesList() {
		return spectaclesService.viewSpectaclesList();
	}

}
