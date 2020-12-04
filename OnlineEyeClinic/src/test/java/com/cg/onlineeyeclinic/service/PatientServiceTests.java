package com.cg.onlineeyeclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.onlineeyeclinic.exception.PatientIdNotFoundException;
import com.cg.onlineeyeclinic.model.Appointment;
import com.cg.onlineeyeclinic.model.Patient;
import com.cg.onlineeyeclinic.model.Report;
import com.cg.onlineeyeclinic.model.Tests;
import com.cg.onlineeyeclinic.repository.IPatientRepository;


@TestInstance(Lifecycle.PER_CLASS)
class PatientServiceTests {
	
	@InjectMocks
	private PatientServiceImpl patientService;

	@Mock
	private IPatientRepository patientRepository;
	
	@Mock
	private AppointmentServiceImpl appointmentService;
	
	@Mock
	private TestServiceImpl testService;
	
	@Mock
	private ReportServiceImpl reportService;

	@SuppressWarnings("deprecation")
	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void addPatientTest() {
		Patient patient = new Patient("Patient", 28,  "873952963", "p@gmail.co", LocalDate.of(1991, 10, 1), "Patient", "Patient", "Nellore");

		when(patientRepository.save(patient)).thenReturn(patient);

		patient = patientService.addPatient(patient);

		assertEquals(28, patient.getPatientAge());
		verify(patientRepository, times(1)).save(patient);
	}

	@Test
	void updatePatientTest() throws PatientIdNotFoundException {
		Patient patient = new Patient("Patient", 28, "873952963", "p@gmail.co", LocalDate.of(1991, 10, 1), "Patient", "Patient", "Nellore");
		Long patientId = patient.getPatientId();
		Optional<Patient> patients = Optional.of(patient);
		when(patientRepository.findById(patientId)).thenReturn(patients);
		when(patientRepository.save(patient)).thenReturn(patient);
		
		patient.setPatientAddress("Chennai");

		patient = patientService.updatePatient(patient);

		assertEquals("Chennai", patient.getPatientAddress());
		verify(patientRepository, times(1)).save(patient);
	}

	@Test
	void deletePatientTest() throws PatientIdNotFoundException {
	    Patient patient = new Patient("Patient", 28, "873952963", "p@gmail.co", LocalDate.of(1991, 10, 1), "Patient", "Patient", "Nellore");
	    Long patientId = patient.getPatientId();
		Optional<Patient> patients = Optional.of(patient);
		when(patientRepository.findById(patientId)).thenReturn(patients);
		patient = patientService.deletePatient(patientId);

		assertNull(patient);
		verify(patientRepository, times(1)).deleteById(patientId);
	}

	@Test
	void viewPatientByIdTest() throws PatientIdNotFoundException {
	    Patient patient = new Patient("Patient", 28, "873952963", "p@gmail.co", LocalDate.of(1991, 10, 1), "Patient", "Patient", "Nellore");
		Long patientId = patient.getPatientId();
		Optional<Patient> patients = Optional.of(patient);
		
		when(patientRepository.findById(patientId)).thenReturn(patients);

		Optional<Patient> newPatients = patientService.findPatientById(patientId);

		assertEquals("Patient", newPatients.get().getPatientName());
		verify(patientRepository, times(3)).findById(patientId);
	}

	@Test
	void viewPatientListTest() {

		List<Patient> patientList = new ArrayList<Patient>();

	    Patient patientOne = new Patient("Patient", 28, "873952963", "p@gmail.co", LocalDate.of(1991, 10, 1), "Patient", "Patient", "Nellore");
	    Patient patientTwo = new Patient("Patient", 28, "873952963", "p@gmail.co", LocalDate.of(1991, 10, 1), "Patient", "Patient", "Nellore");
	    Patient patientThree = new Patient("Patient", 28, "873952963", "p@gmail.co", LocalDate.of(1991, 10, 1), "Patient", "Patient", "Nellore");


	    patientList.add(patientOne);
	    patientList.add(patientTwo);
	    patientList.add(patientThree);

		when(patientRepository.findAll()).thenReturn(patientList);

		List<Patient> newPatientList = patientService.viewPatientList();

		assertEquals(3, newPatientList.size());
		verify(patientRepository, times(1)).findAll();
	}
	
	@Test
	void bookAppointmentTest() {
		Appointment appointment = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 8000.0,(long) 1);
		when(appointmentService.bookAppointment(appointment)).thenReturn(appointment);
		appointment = patientService.bookAppointment(appointment);
        assertEquals(LocalDate.of(2020, 12, 11), appointment.getDateOfAppointment());
		verify(appointmentService, times(1)).bookAppointment(appointment);
	}
	
	@Test
	void viewAppointmentByIdTest()  {
		Appointment appointment = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0,(long) 1);
		Integer appointmentId = appointment.getAppointmentId();
		Optional<Appointment> appointments = Optional.of(appointment);
		when(appointmentService.findAppointmentById(appointmentId)).thenReturn(appointments);
		Optional<Appointment> AppointmentbyId = patientService.findAppointmentById(appointmentId);
		assertEquals(appointmentId, AppointmentbyId.get().getAppointmentId());
		verify(appointmentService, times(1)).findAppointmentById(appointmentId);
	} 
	
	@Test
	void viewReportByPatientIdTest()  {
	    Patient patient = new Patient("Patient", 28, "873952963", "p@gmail.co", LocalDate.of(1991, 10, 1), "Patient", "Patient", "Nellore");
		Report report = new Report(LocalDate.of(2013, 02, 01), "EyeInfection", "Mirror", "NoRange", "NotClear", patient.getPatientId());
		Optional<Report> reports = Optional.of(report);

		when(reportService.viewReportByPatientId(report.getPatientId())).thenReturn(reports);

		Optional<Report> newReports = patientService.findReportByPatientId(report.getPatientId());

		assertEquals("EyeInfection", newReports.get().getDescriptionOfReport());
		verify(reportService, times(1)).viewReportByPatientId(report.getPatientId());

	}
	
	@Test
	 void viewAllTestsListTest() {
		List<Tests> testList = new ArrayList<Tests>();
		Tests testsOne = new Tests("visual acuity","Snellen", "The Snellen test uses a chart of letters or symbols", 3000.0);
		Tests testsTwo = new Tests("visual acuity","random E", "In the random E test identify the direction the letter “E” is facing", 3000.0);

		testList.add(testsOne);
		testList.add(testsTwo);
		
		when(testService.viewTestsList()).thenReturn(testList);
		
		testList = patientService.viewAllTests();
		
		assertEquals(2, testList.size());
		verify(testService, times(1)).viewTestsList();
	}
}
