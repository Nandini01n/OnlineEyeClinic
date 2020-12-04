package com.cg.onlineeyeclinic.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineeyeclinic.model.Report;
import com.cg.onlineeyeclinic.model.Spectacles;
import com.cg.onlineeyeclinic.service.IReportService;

@RestController
@Validated
// @RequestMapping("/api/Report")

public class ReportController {

	@Autowired
	private IReportService reportService;

	// ADDING A REPORT
	@PostMapping("/add_report")
	public ResponseEntity<Report> addReport(@Valid @RequestBody Report report) {
		report = reportService.addReport(report);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	// UPDATING A REPORT
	@PutMapping("/update_report")
	public ResponseEntity<Report> updateReport(@Valid @RequestBody Report report) {
		report = reportService.updateReport(report);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	// DELETING A REPORT
	@DeleteMapping("/delete_report_by_id/{reportId}")
	public ResponseEntity<String> deleteReportById(@PathVariable("reportId") Integer reportId) {
		reportService.deleteReport(reportId);
		return new ResponseEntity<>("Report Deleted Successfully", HttpStatus.OK);
	}

	// VIEW REPORT BY ID
	@GetMapping("/view_report_by_patient-id/{patientId}")
	public ResponseEntity<Optional<Report>> viewReportById(@PathVariable("patientId") Long patientId) {
		Optional<Report> report = reportService.viewReportByPatientId(patientId);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	// VIEW ALL REPORT LIST
	@GetMapping("/view_all_reports")
	public ResponseEntity<List<Report>> viewAllReport() {
		return new ResponseEntity<>(reportService.viewReportList(), HttpStatus.OK);
	}

	// VIEW REPORT BY DATE
	@GetMapping("/view_all_reports_by_date/{date}")
	public ResponseEntity<List<Report>> viewAllReports(
			@Valid @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		List<Report> report = reportService.viewReportsByDate(date);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}
    //VIEW ALL SPECTACLES
	@GetMapping("/view_all_spectacles_by_report")
	public ResponseEntity<List<Spectacles>> viewAllSpectacles() {
		return new ResponseEntity<>(reportService.viewSpectaclesList(), HttpStatus.OK);
	}
}
