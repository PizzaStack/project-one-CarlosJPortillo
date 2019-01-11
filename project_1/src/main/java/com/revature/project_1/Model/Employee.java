package com.revature.project_1.Model;

import java.util.ArrayList;

public class Employee {
	
	private int employeeID;
	private String username;
	private String password;
	private ArrayList<ReimbursementRequest> requests;

	public Employee(int employeeID, String username, String password){
		this.setEmployeeID(employeeID);
		this.setUsername(username);
		this.setPassword(password);
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<ReimbursementRequest> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<ReimbursementRequest> requests) {
		this.requests = requests;
	}
}
