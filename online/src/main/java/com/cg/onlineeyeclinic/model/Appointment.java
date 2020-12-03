package com.cg.onlineeyeclinic.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
/**  Appointment class for OnlineEyeClinic 
 * 
 * @author Nandini Nanabala
 * @version 1.0
 */

@Entity
@Table(name="Appointments")
public class Appointment {
	//attributes for appointment module
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer appointmentId;
	@Column
	@NotNull(message="Appointment Date should not be Empty")
	private LocalDate dateOfAppointment;
	@Column
	@NotNull(message="Appointment Time should not be Empty")
	private LocalTime timeOfAppointment;
	@Column
	@Min(value=100)
	@Max(value=200000)
	@Positive(message="fees limit between 100 to 200000")
	private Double consultationFee;
	/*private Doctor doctor;
	private Patient patient;*/
// Default Constructor
public Appointment()
{
	
}
// Parameterized Constructor 
public Appointment(LocalDate dateOfAppointment, LocalTime timeOfAppointment, Double consultationFee)
{
	super();
    this.dateOfAppointment = dateOfAppointment;
	this.timeOfAppointment = timeOfAppointment;
	this.consultationFee = consultationFee;
	/*this.doctor = doctor;
	this.patient = patient;*/
}
//Setters and Getters 
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
/*public Doctor getDoctor() {
	return doctor;
}
public void setDoctor(Doctor doctor) {
	this.doctor = doctor;
}
public Patient getPatient() {
	return patient;
}
public void setPatient(Patient patient) {
	this.patient = patient;
}
*/
// toString method to print objects in stringform
@Override
public String toString() {
	return "Appointment [appointmentId=" + appointmentId + ", dateOfAppointment="+dateOfAppointment+", timeOfAppointment="+timeOfAppointment+", consultationFee="+consultationFee+"]";
}

}