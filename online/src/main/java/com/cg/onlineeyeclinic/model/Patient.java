package com.cg.onlineeyeclinic.model;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;



/** This is an entity class for Patient with getters and setters and Parameterized constructor
 * 
 * @author Prudhvi Mypati
 *
 */


@Entity
@Table(name="patient")
public class Patient {
	
	@Id
	@Column(name="patient_id",length=10)
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long patientId;
	
	@Column(name="patient_name", length=15)
	@NotEmpty(message="Patient Name should not be Empty")
	@Size(min=2, max=15)
	private String patientName;
	
	@Column(name="patient_age", length=3)
	@Positive(message="Patient Age should be Positive")
	@Min(value=1)
	@Max(value=150)
	private Integer patientAge;
	
	@Column(name="patient_mobile", length=10)
	@NotEmpty(message="Mobile Number should not be Empty")
	@Pattern(regexp="(^[6-9][0-9]{9}+$)" ,message="Mobile Number is Not valid")
	private String patientMobile;
	
	@Column(name="patient_email", length=20)
	@Email
	@NotEmpty(message="Patient Email should not be Empty")
	@Size(min=5, max=20)
	private String patientEmail;
	
	@Column(name="patient_dob", length=20)
	@NotNull(message="Patient DOB should not be Empty")
	private LocalDate patientDOB;
	
	@Column(name="patient_username", length=20)
	@NotEmpty(message="Patient Username should not be Empty")
	@Size(min=5, max=20)
	private String patientUserName;
	
	@Column(name="patient_password", length=20)
	@NotEmpty(message="Patient Password should not be Empty")
	@Size(min=5, max=20)
	private String patientPassword;
	
	@Column(name="patient_address", length=30)
	@NotEmpty(message="Patient Address should not be Empty")
	@Size(min=5, max=20)
	private String patientAddress;
	
	
	//private Appointment appointment;
	
	public Patient() {
	}

	/** This is an Parameterized constructor for Patient Class to initialize the Patient details
	 * 
	 */

	public Patient(String patientName, Integer patientAge, String patientMobile, String patientEmail,
			LocalDate patientDOB, String patientUserName, String patientPassword, String patientAddress) {
		super();
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientMobile = patientMobile;
		this.patientEmail = patientEmail;
		this.patientDOB = patientDOB;
		this.patientUserName = patientUserName;
		this.patientPassword = patientPassword;
		this.patientAddress = patientAddress;
	}

	
	//getters and setters
	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientMobile() {
		return patientMobile;
	}

	public void setPatientMobile(String patientMobile) {
		this.patientMobile = patientMobile;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public LocalDate getPatientDOB() {
		return patientDOB;
	}

	public void setPatientDOB(LocalDate patientDOB) {
		this.patientDOB = patientDOB;
	}

	public String getPatientUserName() {
		return patientUserName;
	}

	public void setPatientUserName(String patientUserName) {
		this.patientUserName = patientUserName;
	}

	public String getPatientPassword() {
		return patientPassword;
	}

	public void setPatientPassword(String patientPassword) {
		this.patientPassword = patientPassword;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	/*public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}*/

	// Method to display Patient Details
	@Override
	public String toString() {
		return "Patient [Patient Id=" + patientId + "\n Patient Name=" + patientName + "\n Patient Age=" + patientAge
				+ "\n Patient Mobile=" + patientMobile + "\n Patient Email=" + patientEmail + "\n Patient DOB=" + patientDOB
				+ "\n Patient UserName=" + patientUserName + "\n Patient Password=" + patientPassword + "\n Patient Address="
				+ patientAddress +"]";
	}
	
	
	
}
