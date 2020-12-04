package com.cg.onlineeyeclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineeyeclinic.exception.PatientIdNotFoundException;
import com.cg.onlineeyeclinic.model.Appointment;
import com.cg.onlineeyeclinic.model.Patient;
import com.cg.onlineeyeclinic.model.Report;
import com.cg.onlineeyeclinic.model.Tests;
import com.cg.onlineeyeclinic.repository.IPatientRepository;

/**
 * PatientServiceImpl class provides methods to perform CRUD operations on
 * Patient entity, Book appointment, View report and tests
 * 
 * @author Prudhvi Mypati
 * 
 */

@Service(value = "patientService")
@Transactional
public class PatientServiceImpl implements IPatientService {

	private static final String ERROR_MESSAGE = "Patient ID Not Found";

	@Autowired
	private IPatientRepository patientRepository;

	@Autowired
	private IAppointmentService appointmentService;

	@Autowired
	private IReportService reportService;

	@Autowired
	private ITestService testService;

	/**
	 * This method Adds Patient by calling the save method
	 * 
	 * @param Patient - Patient entity details
	 * @return Patient - Patient entity details
	 * 
	 */
	@Override
	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}

	/**
	 * This method Updates Patient by calling the save method
	 * 
	 * @param Patient - Patient entity details
	 * @return Patient - Patient entity details
	 * 
	 */
	@Override
	public Patient updatePatient(Patient patient) {
		Long patientId = patient.getPatientId();
		Optional<Patient> patients = patientRepository.findById(patientId);
		if (patients.isEmpty()) {
			throw new PatientIdNotFoundException(patientId + ERROR_MESSAGE);
		}
		patient = patientRepository.save(patient);
		return patient;
	}

	/**
	 * This method Deletes Patient by calling the deleteById method
	 * 
	 * @param patientId - Patient Id
	 * @return null
	 * 
	 */
	@Override
	public Patient deletePatient(Long patientId) {
		Optional<Patient> patients = patientRepository.findById(patientId);
		if (patients.isEmpty()) {
			throw new PatientIdNotFoundException(patientId + ERROR_MESSAGE);
		}
		patientRepository.deleteById(patientId);
		return null;
	}

	/**
	 * This method takes Patient Id and returns the Patient based on Id
	 * 
	 * @param patientId - Long value to display patient based on patientId
	 * @return Patient - Patient entity details
	 */
	@Override
	public Optional<Patient> findPatientById(Long patientId) {
		Optional<Patient> patients = patientRepository.findById(patientId);
		if (patients.isEmpty()) {
			throw new PatientIdNotFoundException(patientId + ERROR_MESSAGE);
		}
		return patients;
	}

	/**
	 * This method returns the Patient List present in the Patient Table
	 * 
	 * If patient list is null, returns null
	 * 
	 * @return list of Patient
	 * 
	 */
	@Override
	public List<Patient> viewPatientList() {
		return patientRepository.findAll();
	}

	/**
	 * This method books an Appointment
	 * 
	 * @param Appointment - Appointment entity details
	 * @return Appointment - Appointment entity details
	 * 
	 */

	@Override
	public Appointment bookAppointment(Appointment appointment) {
		return appointmentService.bookAppointment(appointment);
	}

	/**
	 * This method takes Appointment Id and returns the Appointment details based on Id
	 * 
	 * @param appointmentId - integer value to display Appointment based on
	 *                      appointmentId
	 * @return Appointment - Appointment entity details
	 */

	@Override
	public Optional<Appointment> findAppointmentById(Integer appointmentId) {
		return appointmentService.findAppointmentById(appointmentId);

	}

	/**
	 * This method takes the Patient Id and returns the corresponding report object
	 * 
	 * @param patientId - Integer value
	 */

	@Override
	public Optional<Report> findReportByPatientId(Long patientId) {
		return reportService.viewReportByPatientId(patientId);
	}

	/**
	 * This method returns the Tests List present in the Tests Table
	 * 
	 * If Tests list is null, returns null
	 * 
	 * @return list of Tests
	 * 
	 */

	@Override
	public List<Tests> viewAllTests() {
		return testService.viewTestsList();
	}

}
