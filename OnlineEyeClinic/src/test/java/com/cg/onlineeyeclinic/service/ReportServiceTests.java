package com.cg.onlineeyeclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import com.cg.onlineeyeclinic.model.Patient;
import com.cg.onlineeyeclinic.model.Report;
import com.cg.onlineeyeclinic.model.Spectacles;
import com.cg.onlineeyeclinic.repository.IReportRepository;

//@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ReportServiceTests {

	@InjectMocks
	private ReportServiceImpl reportService;

	@Mock
	private IReportRepository reportRepository;
	
	@Mock
	private PatientServiceImpl patientService;

	@Mock
	private SpectaclesServiceImpl spectaclesService;
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void addReportTest() {
		Report report = new Report(LocalDate.of(2016, 8, 07), "VisionProblem", "5Feet", "LongRange", "shortRange",(long) 1);
		when(reportRepository.save(report)).thenReturn(report);

		report = reportService.addReport(report);

		assertEquals("VisionProblem", report.getDescriptionOfReport());
		verify(reportRepository, times(1)).save(report);
	}

	@Test
	void updateReportTest() {
		Report report = new Report(LocalDate.of(2018, 11, 12), "NoVision", "Blur", "NoRange", "short",(long) 1);
		Integer reportId = report.getReportId();
		Optional<Report> reports = Optional.of(report);
		when(reportRepository.findById(reportId)).thenReturn(reports);
		when(reportRepository.save(report)).thenReturn(report);

		report.setVisualAcuity("Blur");

		report = reportService.updateReport(report);

		assertEquals("Blur", report.getVisualAcuity());
		verify(reportRepository, times(1)).save(report);
	}

	@Test
	void deleteReportTest() {
		Report report = new Report(LocalDate.of(2017, 05, 03), "DryEyes", "Watering", "LessRange", "NotVisible",(long) 1);
		Integer reportId = report.getReportId();
		Optional<Report> reports = Optional.of(report);
		when(reportRepository.findById(reportId)).thenReturn(reports);
		report = reportService.deleteReport(reportId);
		assertNull(report);
		verify(reportRepository, times(1)).deleteById(reportId);
	}

	@Test
	void viewReportTest()  {
		Patient patient = new Patient("Patient", 28, "873952963", "p@gmail.co", LocalDate.of(1991, 10, 1), "Patient", "Patient", "Nellore");
		Report report = new Report(LocalDate.of(2013, 02, 01), "EyeInfection", "Mirror", "NoRange", "NotClear", patient.getPatientId());
		Optional<Report> reports = Optional.of(report);
		Optional<Patient> patients = Optional.of(patient);
		when(patientService.findPatientById(patient.getPatientId())).thenReturn(patients);
		when(reportService.viewReportByPatientId(report.getPatientId())).thenReturn(reports);

		Optional<Report> newReports = reportService.viewReportByPatientId(report.getPatientId());

		assertEquals("EyeInfection", newReports.get().getDescriptionOfReport());
	}

	@Test
	void viewReportListTest() {

		List<Report> reportList = new ArrayList<Report>();
		Report reportOne = new Report(LocalDate.of(2019, 12, 01), "Floaters", "NotMention", "EightFeet", "SixFeet",(long) 1);
		Report reportTwo = new Report(LocalDate.of(2016, 12, 12), "Floaters", "NotMention", "EightFeet", "SixFeet",(long) 2);
		Report reportThree = new Report(LocalDate.of(2012, 10, 10), "Floaters", "NotMention", "EightFeet", "SixFeet", (long) 3);

		reportList.add(reportOne);
		reportList.add(reportTwo);
		reportList.add(reportThree);

		when(reportRepository.findAll()).thenReturn(reportList);

		List<Report> newReportList = reportService.viewReportList();

		assertEquals(3, newReportList.size());
		verify(reportRepository, times(1)).findAll();
	}

	@Test
	void viewAllReportsTest() {
		List<Report> reportList = new ArrayList<Report>();
		Report reportOne = new Report(LocalDate.of(2019, 12, 01), "Floaters", "NotMention", "EightFeet", "SixFeet",(long) 2);
		Report reportTwo = new Report(LocalDate.of(2019, 12, 01), "Floaters", "NotMention", "EightFeet", "SixFeet",(long) 2);
		//Report reportThree = new Report(LocalDate.of(2012, 10, 10), "Floaters", "NotMention", "EightFeet", "SixFeet",(long) 2);

		reportList.add(reportOne);
		reportList.add(reportTwo);

		when(reportRepository.viewReportsByDate(LocalDate.of(2019, 12, 01))).thenReturn(reportList);
		List<Report> newReportList = reportService.viewReportsByDate(LocalDate.of(2019, 12, 01));

		assertEquals(2,newReportList.size());

	}
	
	@Test
	 void viewSpectaclesListTest() {
		List<Spectacles> spectaclesList = new ArrayList<Spectacles>();
		spectaclesList.add(new Spectacles(01,"car","rect",540.0));
		spectaclesList.add(new Spectacles(02,"rail","square",640.0));
		spectaclesList.add(new Spectacles(03,"bus","semisq",940.0));
		
		when(spectaclesService.viewSpectaclesList()).thenReturn(spectaclesList);
		
		List<Spectacles> specsList = reportService.viewSpectaclesList();
		
		assertEquals(3, specsList.size());
		verify(spectaclesService, times(1)).viewSpectaclesList();
	}

}
