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

import com.cg.onlineeyeclinic.exception.AppointmentIdNotFoundException;
import com.cg.onlineeyeclinic.exception.InvalidAppointmentException;
import com.cg.onlineeyeclinic.model.Appointment;
import com.cg.onlineeyeclinic.model.Doctor;
import com.cg.onlineeyeclinic.repository.IAppointmentRepository;

@TestInstance(Lifecycle.PER_CLASS)
class AppointmentServiceTests {

	@InjectMocks
	private AppointmentServiceImpl appointmentService;

	@Mock
	private IAppointmentRepository appointmentRepository;

	@Mock
	private DoctorServiceImpl doctorService;

	@SuppressWarnings("deprecation")
	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void bookAppointmentTest() {
		Doctor doc = new Doctor("sai", "10:30", "9876543210", "sai.global@doctor.com", "saiglobal", "Sai&%6412",
				"Hyderabad");
		Optional<Doctor> doctor = Optional.of(doc);
		Appointment appointment = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 8000.0,
				doc.getDoctorId());
		when(doctorService.viewDoctor(appointment.getDoctorId())).thenReturn(doctor);
		when(appointmentRepository.save(appointment)).thenReturn(appointment);
		appointment = appointmentService.bookAppointment(appointment);
		assertEquals(LocalDate.of(2020, 12, 11), appointment.getDateOfAppointment());
		verify(appointmentRepository, times(1)).save(appointment);
	}

	@Test
	void updateAppointmentTest() throws InvalidAppointmentException {
		Doctor doc = new Doctor("sai", "10:30", "9876543210", "sai.global@doctor.com", "saiglobal", "Sai&%6412",
				"Hyderabad");
		Optional<Doctor> doctor = Optional.of(doc);
		Appointment appointment = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 8000.0,
				doc.getDoctorId());
		when(doctorService.viewDoctor(appointment.getDoctorId())).thenReturn(doctor);

		Integer appointmentId = appointment.getAppointmentId();
		Optional<Appointment> appointments = Optional.of(appointment);
		when(appointmentRepository.findById(appointmentId)).thenReturn(appointments);
		when(appointmentRepository.save(appointment)).thenReturn(appointment);
		appointment.setDateOfAppointment(LocalDate.of(2020, 12, 11));
		appointment = appointmentService.updateAppointment(appointment);
		assertEquals(LocalDate.of(2020, 12, 11), appointment.getDateOfAppointment());
		verify(appointmentRepository, times(1)).save(appointment);
	}

	@Test
	void cancelAppointmentTest() throws InvalidAppointmentException, AppointmentIdNotFoundException {
		Appointment appointment = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 900.0,
				(long) 1);
		Integer appointmentId = appointment.getAppointmentId();
		Optional<Appointment> appointments = Optional.of(appointment);
		when(appointmentRepository.findById(appointmentId)).thenReturn(appointments);
		appointment = appointmentService.cancelAppointment(appointmentId);
		assertNull(appointment);
		verify(appointmentRepository, times(1)).deleteById(appointmentId);
	}

	@Test
	void findAppointmentByIdTest() {
		Appointment appointment = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0,
				(long) 1);
		Integer appointmentId = appointment.getAppointmentId();
		Optional<Appointment> appointments = Optional.of(appointment);
		when(appointmentRepository.findById(appointmentId)).thenReturn(appointments);
		Optional<Appointment> AppointmentbyId = appointmentService.findAppointmentById(appointmentId);
		assertEquals(appointmentId, AppointmentbyId.get().getAppointmentId());
	}

	@Test
	void viewAllAppointmentsTest() {
		List<Appointment> appointmentlist = new ArrayList<Appointment>();
		Appointment appointmentOne = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0,
				(long) 1);
		Appointment appointmentTwo = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0,
				(long) 2);
		Appointment appointmentThree = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0,
				(long) 3);
		appointmentlist.add(appointmentOne);
		appointmentlist.add(appointmentTwo);
		appointmentlist.add(appointmentThree);
		when(appointmentRepository.findAll()).thenReturn(appointmentlist);
		List<Appointment> newAppointmentList = appointmentService.viewAllAppointments();
		assertEquals(3, newAppointmentList.size());
		verify(appointmentRepository, times(1)).findAll();
	}

	@Test
	void viewAppointmentsTest() {
		List<Appointment> appointmentByDate = new ArrayList<Appointment>();
		Appointment appointmentOne = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0,
				(long) 1);
		Appointment appointmentTwo = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(9, 30, 00), 4550.0,
				(long) 2);
		Appointment appointmentThree = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 1000.0,
				(long) 3);
		//Appointment appointmentFour = new Appointment(LocalDate.of(2020, 12, 6), LocalTime.of(18, 30, 00), 1000.0,(long) 4);
		appointmentByDate.add(appointmentOne);
		appointmentByDate.add(appointmentTwo);
		appointmentByDate.add(appointmentThree);
		when(appointmentRepository.viewAppointments(appointmentOne.getDateOfAppointment()))
				.thenReturn(appointmentByDate);
		List<Appointment> newAppointmentList = appointmentService
				.viewAppointments(appointmentOne.getDateOfAppointment());
		assertEquals(3, newAppointmentList.size());
	}

}
