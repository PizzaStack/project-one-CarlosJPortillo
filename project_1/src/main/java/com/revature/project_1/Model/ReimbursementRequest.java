package com.revature.project_1.Model;
import java.util.Date;

public class ReimbursementRequest {
	
	private int requestID;
	private String requestType;
	private Date dateSubmitted;
	private String employeeUsername;
	private boolean accepted;
	private int acceptedManagerID;
	
	public ReimbursementRequest(int requestID, String requestType, Date dateSubmitted, String employeeUsername, boolean accepted, int acceptedManagerID) {
		
		this.requestID = requestID;
		this.requestType = requestType;
		this.dateSubmitted = dateSubmitted;
		this.employeeUsername = employeeUsername;
		this.accepted = accepted;
		this.acceptedManagerID = acceptedManagerID;
	}
	
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public int getAcceptedManagerID() {
		return acceptedManagerID;
	}
	public void setAcceptedManagerID(int acceptedManagerID) {
		this.acceptedManagerID = acceptedManagerID;
	}
	public Date getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(Date date_submitted) {
		this.dateSubmitted = date_submitted;
	}

	public String getEmployeeUsername() {
		return employeeUsername;
	}

	public void setEmployee_username(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}		

}
