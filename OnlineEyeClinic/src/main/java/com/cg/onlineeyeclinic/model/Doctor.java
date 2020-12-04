package com.cg.onlineeyeclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/** This is entity class for patients with default constructor, parameterized constructor along with getters and setters
 * 
 * @author Kondraju Praneeth
 * @version 1.0
 */

@Entity
@Table(name = "Doctor")
public class Doctor {
	
	/**
	 * Data Fields
	 */
	/**
	 * @Id annotation is used to set a particular data field as primary key.
	 * @Column annotation is used to set column properties such as name, length.,etc.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "Doctor_Id")
	private Long doctorId;
	
	@Column(name = "Doctor_Name")
	@NotNull(message = "Name Should Not be Null")
	private String doctorName;
	
	@Column(name = "Consultaion_Time")
	@NotEmpty(message = "Cousultation Time should be Mentioned")
	private String doctorConsultationTime;
	
	@Column(name = "Mobile_Number")
	@Pattern(regexp = "^[6-9][0-9]{9}+$", message = "Enter Valid Mobile Number")
	private String doctorMobile;
	
	@Column(name = "Email")
	@NotNull(message = "Doctor Mail is Compulsory")
	@Email
	private String doctorEmail;
	
	@Column(name = "Username")
	@NotNull(message = "UserName should not be Empty")
	@Size(min = 5, max = 10)
	private String userName;
	
	@Column(name = "Password")
	@Pattern(regexp = "[A-Za-z0-9@#$%^&]{8,20}+$", message = "Password Must be between 8 to 20 characters")
	private String password;
	
	@Column(name = "Address")
	@NotEmpty(message = "Address is Compulsory")
	private String address;
	
	/**
	 * Default Constructor
	 */
	
	public Doctor() {

	}
	
	/**
	 * Parameterized Constructor
	 */
	
	

	public Doctor(String doctorName, String doctorConsultationTime, String doctorMobile, String doctorEmail,
			String userName, String password, String address) {
		super();
		this.doctorName = doctorName;
		this.doctorConsultationTime = doctorConsultationTime;
		this.doctorMobile = doctorMobile;
		this.doctorEmail = doctorEmail;
		this.userName = userName;
		this.password = password;
		this.address = address;
	}

	/**
	 * Getters and setters for All data fields
	 * 
	 */
	
//
	public Long getDoctorId() {
		return doctorId;
	}

	

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorConsultationTime() {
		return doctorConsultationTime;
	}

	public void setDoctorConsultationTime(String doctorConsultationTime) {
		this.doctorConsultationTime = doctorConsultationTime;
	}

	public String getDoctorMobile() {
		return doctorMobile;
	}

	public void setDoctorMobile(String doctorMobile) {
		this.doctorMobile = doctorMobile;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// Method to display Doctor Details
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", doctorConsultationTime="
				+ doctorConsultationTime + ", doctorMobile=" + doctorMobile + ", doctorEmail=" + doctorEmail
				+ ", userName=" + userName + ", password=" + password + ", address=" + address + "]";
	}
	
	
	

}
