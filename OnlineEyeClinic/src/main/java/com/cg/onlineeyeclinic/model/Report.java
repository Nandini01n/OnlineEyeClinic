package com.cg.onlineeyeclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This is entity class for reports with default constructor, parameterized
 * constructor along with getters and setters
 * 
 * @author D,Pradeep kumar
 * @version 1.0
 *
 */

@Entity
@Table(name = "Report")
public class Report {
	/**
	 * Data Fields
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="report_id")
	private Integer reportId;

	@Column(name = "dateOfReport", length = 15)
	@NotNull(message = "Date Of Report should not be Empty")
	private LocalDate dateOfReport;

	@Column(name = "descriptionOfReport", length = 15)
	@NotEmpty(message = "Description  Of Report should not be Empty")
	@Size(min = 2, max = 15)
	private String descriptionOfReport;

	@Column(name = "visualAcuity", length = 15)
	@NotEmpty(message = "VisualAcuity Of Report should not be Empty")
	@Size(min = 2, max = 15)
	private String visualAcuity;

	@Column(name = "visualAcuityNear", length = 15)
	@NotEmpty(message = "VisualAcuityNear Of Report should not be Empty")
	@Size(min = 2, max = 15)
	private String visualAcuityNear;

	@Column(name = "visualAcuityDistance", length = 15)
	@NotEmpty(message = "VisualAcuityDistance Of Report should not be Empty")
	@Size(min = 2, max = 15)
	private String visualAcuityDistance;
	
	@Column(name = "patiend_id")
	@Min(1)
	private Long patientId;
	
	
	/**
	 * Default Constructor
	 */

	public Report() {
	}

	/**
	 * Parameterized Constructor
	 */

	public Report(LocalDate dateOfReport, String descriptionOfReport, String visualAcuity, String visualAcuityNear,
			String visualAcuityDistance, Long patientId) {
		super();

		this.dateOfReport = dateOfReport;
		this.descriptionOfReport = descriptionOfReport;
		this.visualAcuity = visualAcuity;
		this.visualAcuityNear = visualAcuityNear;
		this.visualAcuityDistance = visualAcuityDistance;
		this.patientId = patientId;

	}

	/**
	 * Getters and setters for All data fields
	 * 
	 */
	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public LocalDate getDateOfReport() {
		return dateOfReport;
	}

	public void setDateOfReport(LocalDate dateOfReport) {
		this.dateOfReport = dateOfReport;
	}

	public String getDescriptionOfReport() {
		return descriptionOfReport;
	}

	public void setDescriptionOfReport(String descriptionOfReport) {
		this.descriptionOfReport = descriptionOfReport;
	}

	public String getVisualAcuity() {
		return visualAcuity;
	}

	public void setVisualAcuity(String visualAcuity) {
		this.visualAcuity = visualAcuity;
	}

	public String getVisualAcuityNear() {
		return visualAcuityNear;
	}

	public void setVisualAcuityNear(String visualAcuityNear) {
		this.visualAcuityNear = visualAcuityNear;
	}

	public String getVisualAcuityDistance() {
		return visualAcuityDistance;
	}

	public void setVisualAcuityDistance(String visualAcuityDistance) {
		this.visualAcuityDistance = visualAcuityDistance;
	}
	
	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	// Method to display Report Details
	@Override
	public String toString() {
		return "Report [reportId= " + reportId + ", dateofReport=" + dateOfReport + ", descriptionOfReport="
				+ descriptionOfReport + ", visualAcuity=" + visualAcuity + ", visualAcuityNear=" + visualAcuityNear
				+ ", visualAcuityDistance=" + visualAcuityDistance + "]";
	}

}
