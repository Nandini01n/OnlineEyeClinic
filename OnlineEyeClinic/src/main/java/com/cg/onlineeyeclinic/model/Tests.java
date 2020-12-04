package com.cg.onlineeyeclinic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
/** This is an entity class for Tests with getters and setters and Parameterized constructor
 * 
 * @author Manasa Magasani
 *
 */
@Entity
public class Tests {
	/**
	 * Data Fields
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer testId;
	
	@NotEmpty(message = "Test Name cannot be Empty")
	private String testName;
	
	@NotEmpty(message = "Test Type Cannot be Empty")
	private String testType;
	
	@NotEmpty(message = "Test Description Cannot be Empty")
	private String testDescription;
	
	@Min(2000)
	private Double testCost;
	/**
	 * Default Constructor
	 */
	public Tests() {
		
	}
	/** This is an Parameterized constructor for Tests Class to initialize the Tests details
	 * 
	 */
	public Tests(String testName, String testType, String testDescription, Double testCost) {
		super();
		this.testName = testName;
		this.testType = testType;
		this.testDescription = testDescription;
		this.testCost = testCost;
	}

	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getTestDescription() {
		return testDescription;
	}
	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}
	public double getTestCost() {
		return testCost;
	}
	public void setTestCost(Double testCost) {
		this.testCost = testCost;
	}
	// Method to display Tests Details
	@Override
	public String toString() {
		return "Test [testId=" + testId + ", testName=" + testName + ", testType=" + testType + ", testDescription="
				+ testDescription + ", testCost=" + testCost + "]";
	}
	
	
}
