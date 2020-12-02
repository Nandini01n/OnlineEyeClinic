package com.example.one;

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
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlineeyeclinic.Exception.AppointmentIdNotFoundException;
import com.cg.onlineeyeclinic.Exception.InvalidAppointmentException;
import com.cg.onlineeyeclinic.dao.IAppointmentRepository;
import com.cg.onlineeyeclinic.model.Appointment;
import com.cg.onlineeyeclinic.service.AppointmentServiceImpl;

//@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class OnlineApplicationTests {
	@InjectMocks
	private AppointmentServiceImpl appointmentService;

	@Mock
	private IAppointmentRepository appointmentRepository;
	@SuppressWarnings("deprecation")
	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void bookAppointmentTest() {
		Appointment appointment = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 8000.0);
		when(appointmentRepository.save(appointment)).thenReturn(appointment);
		appointment = appointmentService.bookAppointment(appointment);
        assertEquals(LocalDate.of(2020, 12, 11), appointment.getDateOfAppointment());
		verify(appointmentRepository, times(1)).save(appointment);
	}
	@Test
	public void updateAppointmentTest() throws InvalidAppointmentException {
		Appointment appointment = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0);
		Integer appointmentId = appointment.getAppointmentId();
		Optional<Appointment> appointments = Optional.of(appointment);
		when(appointmentRepository.findById(appointmentId)).thenReturn(appointments);
		when(appointmentRepository.save(appointment)).thenReturn(appointment);
		appointment.setDateOfAppointment(LocalDate.of(2020, 12, 11));
		appointment=appointmentService.updateAppointment(appointment);
		assertEquals(LocalDate.of(2020, 12, 11), appointment.getDateOfAppointment());
		verify(appointmentRepository, times(1)).save(appointment);
	}
	@Test
	public void cancelAppointmentTest() throws InvalidAppointmentException, AppointmentIdNotFoundException {
		Appointment appointment = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 900.0);
		Integer appointmentId = appointment.getAppointmentId();
		Optional<Appointment> appointments = Optional.of(appointment);
		when(appointmentRepository.findById(appointmentId)).thenReturn(appointments);
		appointment = appointmentService.cancelAppointment(appointmentId);
		assertNull(appointment);
		verify(appointmentRepository,times(1)).deleteById(appointmentId);
	}
	@Test
	public void findAppointmentByIdTest()  {
		Appointment appointment = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0);
		Integer appointmentId = appointment.getAppointmentId();
		Optional<Appointment> appointments = Optional.of(appointment);
		when(appointmentRepository.findById(appointmentId)).thenReturn(appointments);
		Optional<Appointment> AppointmentbyId = appointmentService.findAppointmentById(appointmentId);
		assertEquals(appointmentId, AppointmentbyId.get().getAppointmentId());
	} 
	@Test
	public void viewAllAppointments()
	{
		List<Appointment> appointmentlist = new ArrayList<Appointment>();
		Appointment appointmentOne = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0);
		Appointment appointmentTwo = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0);
		Appointment appointmentThree = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0);
		appointmentlist.add(appointmentOne);
		appointmentlist.add(appointmentTwo);
		appointmentlist.add(appointmentThree);
		when(appointmentRepository.findAll()).thenReturn(appointmentlist);
		List<Appointment> newAppointmentList = appointmentService.viewAllAppointments();
		assertEquals(3,newAppointmentList.size());
		verify(appointmentRepository, times(1)).findAll();
	}
    @Test
	public void viewAppointments() 
	{
		List<Appointment> appointmentByDate = new ArrayList<Appointment>();
		Appointment appointmentOne = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 10000.0);
		Appointment appointmentTwo = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(9, 30, 00), 4550.0);
		Appointment appointmentThree = new Appointment(LocalDate.of(2020, 12, 11), LocalTime.of(10, 30, 00), 1000.0);
		Appointment appointmentFour = new Appointment(LocalDate.of(2020, 12, 6), LocalTime.of(18, 30, 00), 1000.0);
		appointmentByDate.add(appointmentOne);
		appointmentByDate.add(appointmentTwo);
		appointmentByDate.add(appointmentThree);
		when(appointmentRepository.viewAppointments(appointmentOne.getDateOfAppointment())).thenReturn(appointmentByDate);
		List<Appointment> newAppointmentList = appointmentService.viewAppointments(appointmentOne.getDateOfAppointment());
		assertEquals(3,newAppointmentList.size());
	}

}
