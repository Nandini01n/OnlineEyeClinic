package com.cg.onlineeyeclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.cg.onlineeyeclinic.exception.TestIdNotFoundException;
import com.cg.onlineeyeclinic.model.Tests;
import com.cg.onlineeyeclinic.repository.ITestRepository;

@TestInstance(Lifecycle.PER_CLASS)
 class TestSericeTests {

	
	@InjectMocks
	private TestServiceImpl testService;
	
	@Mock
	private ITestRepository testRepository;
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	 void addTestTest() {
		Tests tests = new Tests("visual acuity","Snellen", "The Snellen test uses a chart of letters or symbols", 3000.0);
		
		when(testRepository.save(tests)).thenReturn(tests);
		
		tests = testService.addTest(tests);
		
		assertEquals(3000.0, tests.getTestCost());
		verify(testRepository, times(1)).save(tests);
	}
	
	@Test
	void updateTestTest() throws TestIdNotFoundException {
		Tests tests = new Tests("visual acuity","Snellen", "The Snellen test uses a chart of letters or symbols", 3000.0);
		
		Integer testId = tests.getTestId();
		Optional<Tests> newTests = Optional.of(tests);
		
		when(testRepository.findById(testId)).thenReturn(newTests);
		when(testRepository.save(tests)).thenReturn(tests);
		
		tests.setTestType("random E");
		
		tests = testService.updateTest(tests);
		
		assertEquals("random E", tests.getTestType());
		verify(testRepository, times(1)).save(tests);
	}
	
	@Test
	 void deleteTestTest() throws TestIdNotFoundException {
		Tests tests = new Tests("visual acuity","Snellen", "The Snellen test uses a chart of letters or symbols", 3000.0);
		
		Integer testId = tests.getTestId();
		Optional<Tests> newTests = Optional.of(tests);
		
		when(testRepository.findById(testId)).thenReturn(newTests);
		
		tests = testService.removeTest(testId);
		
		assertNull(tests);
		verify(testRepository, times(1)).deleteById(testId);
	}
	
	@Test
	 void viewTestTest() throws TestIdNotFoundException {
		Tests tests = new Tests("visual acuity","Snellen", "The Snellen test uses a chart of letters or symbols", 3000.0);
		Integer testId = tests.getTestId();
		Optional<Tests> newTests = Optional.of(tests);
		
		when(testRepository.findById(testId)).thenReturn(newTests);
		
		Optional<Tests> viewTests = testService.viewTest(testId);
		
		assertEquals("Snellen", viewTests.get().getTestType());;
		verify(testRepository, times(1)).findById(testId);
	}
	
	@Test
	 void viewTestListTest() {
		List<Tests> testList = new ArrayList<Tests>();
		Tests testsOne = new Tests("visual acuity","Snellen", "The Snellen test uses a chart of letters or symbols", 3000.0);
		Tests testsTwo = new Tests("visual acuity","random E", "In the random E test identify the direction the letter “E” is facing", 3000.0);

		testList.add(testsOne);
		testList.add(testsTwo);
		
		when(testRepository.findAll()).thenReturn(testList);
		
		testList = testService.viewTestsList();
		
		assertEquals(2, testList.size());
		verify(testRepository, times(1)).findAll();
	}
}
