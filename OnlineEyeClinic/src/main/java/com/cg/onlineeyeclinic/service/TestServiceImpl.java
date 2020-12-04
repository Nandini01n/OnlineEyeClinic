package com.cg.onlineeyeclinic.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineeyeclinic.exception.TestIdNotFoundException;
import com.cg.onlineeyeclinic.model.Tests;
import com.cg.onlineeyeclinic.repository.ITestRepository;

/**
 * TestServiceImpl class provides methods to perform CRUD operations on Test entity
 *
 * @author Manasa Magasani
 * 
 */

@Service("name= testService")
@Transactional
public class TestServiceImpl implements ITestService {

	private static final String ERROR_MESSAGE = "Test ID Not Found";

	@Autowired
	private ITestRepository testRepository;
	/**
	 * This method Adds Test by calling the save method
	 * 
	 * @param Tests - Tests entity details
	 * @return Tests - Tests entity details
	 * 
	 */
	@Override
	public Tests addTest(Tests tests) {
		return testRepository.save(tests);
	}
	/**
	 * This method Updates Test by calling the save method
	 * 
	 * @param Tests - Tests entity details
	 * @return Tests - Tests entity details
	 * 
	 */
	@Override
	public Tests updateTest(Tests tests) {
		Integer testId = tests.getTestId();
		Optional<Tests> test = testRepository.findById(testId);
		if (test.isEmpty())
			throw new TestIdNotFoundException(ERROR_MESSAGE);
		return testRepository.save(tests);
	}
	/**
	 * This method removes Test by calling the deleteById method
	 * 
	 * @param testId - Test Id
	 * @return null
	 * 
	 */
	@Override
	public Tests removeTest(Integer testId) {
		Optional<Tests> test = testRepository.findById(testId);
		if (test.isEmpty())
			throw new TestIdNotFoundException(ERROR_MESSAGE);
		testRepository.deleteById(testId);
		return null;
	}
	/**
	 * This method takes Test Id and returns the Test based on Id
	 * 
	 * @param testId - Integer value to display Test based on testId
	 * @return Tests - Tests entity details
	 */
	@Override
	public Optional<Tests> viewTest(Integer testId) {
		Optional<Tests> test = testRepository.findById(testId);
		if (test.isEmpty())
			throw new TestIdNotFoundException(ERROR_MESSAGE);
		return test;

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
	public List<Tests> viewTestsList() {
		return testRepository.findAll();
	}

}