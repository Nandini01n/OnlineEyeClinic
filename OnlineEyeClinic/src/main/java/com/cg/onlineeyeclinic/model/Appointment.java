package com.cg.onlineeyeclinic.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/** This is an entity class for Appointment with getters and setters and Parameterized constructor
 * 
 * @author Nandini Nanabala
 *
 */

@Entity
@Table(name = "Appointments")
public class Appointment {
	/**
	 * Data Fields
	 */
	@Id
	@Column(name = "appointment_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer appointmentId;
	@Column
	@NotNull(message = "Appointment Date should not be Empty")
	private LocalDate dateOfAppointment;
	@Column
	@NotNull(message = "Appointment Time should not be Empty")
	private LocalTime timeOfAppointment;
	@Column
	@Min(value = 100)
	@Max(value = 200000)
	@Positive(message = "fees limit between 100 to 200000")
	private Double consultationFee;

	private Long doctorId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	

	public Appointment() {

	}
	/** This is an Parameterized constructor for Appointment Class to initialize the Appointment details
	 * 
	 */
	public Appointment(LocalDate dateOfAppointment, LocalTime timeOfAppointment, Double consultationFee, Long doctorId) {
		super();
		this.dateOfAppointment = dateOfAppointment;
		this.timeOfAppointment = timeOfAppointment;
		this.consultationFee = consultationFee;
		this.doctorId = doctorId;

	}
	/**
	 * Getters and setters for All data fields
	 * 
	 */
	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public LocalDate getDateOfAppointment() {
		return dateOfAppointment;
	}

	public void setDateOfAppointment(LocalDate dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}

	public LocalTime getTimeOfAppointment() {
		return timeOfAppointment;
	}

	public void setTimeOfAppointment(LocalTime timeOfAppointment) {

		this.timeOfAppointment = timeOfAppointment;
	}

	public double getConsultationFee() {
		return consultationFee;
	}

	public void setConsultationFee(Double consultationFee) {
		this.consultationFee = consultationFee;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", dateOfAppointment=" + dateOfAppointment
				+ ", timeOfAppointment=" + timeOfAppointment + ", consultationFee=" + consultationFee + ", doctorId="
				+ doctorId + ", patient=" + patient + "]";
	}
	
}