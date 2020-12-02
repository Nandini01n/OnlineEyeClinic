package com.cg.onlineeyeclinic.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineeyeclinic.model.Appointment;
import com.cg.onlineeyeclinic.service.IAppointmentService;
@Validated
@RestController
@RequestMapping("api/appointments")
@CrossOrigin("http://localhost:8085")
public class AppointmentController {
   @Autowired
   private IAppointmentService service;
   @PostMapping("/bookAppointment")
   public ResponseEntity<Appointment> bookAppointment(@Valid @RequestBody Appointment appointment)
   {
	   Appointment entity = service.bookAppointment(appointment);
	   return new ResponseEntity<Appointment>(entity, new HttpHeaders(), HttpStatus.OK);   
   }
   @PutMapping("/updateAppointment")
   public ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment appointment) 
   {
	   Appointment entity = service.updateAppointment(appointment);
	   return new ResponseEntity<Appointment>(entity, new HttpHeaders(), HttpStatus.OK);
   }
   @DeleteMapping("/deleteAppointment/{appointmentId}")
   public String cancelAppointment(@PathVariable("appointmentId")Integer appointmentId) 
   {
	   service.cancelAppointment(appointmentId);
	   return "Appointment Deleted";
   }
   @GetMapping("/findById/{appointmentId}")
   public ResponseEntity<Optional<Appointment>> findAppointmentById(@PathVariable("appointmentId")Integer appointmentId)  
   {
	  Optional<Appointment> appointment =null;
	  appointment = service.findAppointmentById(appointmentId);
	  return new ResponseEntity<Optional<Appointment>>(appointment,new HttpHeaders(), HttpStatus.OK);
   }
   @GetMapping("/viewAll")
   public ResponseEntity<List<Appointment>> viewAllAppointments()
   {
	   List<Appointment> list = service.viewAllAppointments();
	   return new ResponseEntity<List<Appointment>>(list, new HttpHeaders(), HttpStatus.OK);
   }
   @GetMapping("/viewByDate/{date}")
   public ResponseEntity<List<Appointment>> viewAppointments(@Valid @PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date)
   {
	   List<Appointment> listByDate = service.viewAppointments(date);
	   return new ResponseEntity<List<Appointment>>(listByDate, new HttpHeaders(), HttpStatus.OK);
   }
}
