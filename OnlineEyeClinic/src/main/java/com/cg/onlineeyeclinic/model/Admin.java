package com.cg.onlineeyeclinic.model;

// This is an entity class for Admin with setters and getters 
public class Admin {
	private Integer adminId;
	private String adminUsername;
	private String adminPassword;
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}	
}
