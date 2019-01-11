package com.revature.project_1.Model;

public class ReimbursementRequest {
	
	private int requestID;
	private String requestType;
	private float amountRequested;
	private int employeeID;
	private boolean accepted;
	private int acceptedManagerID;
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
	public float getAmountRequested() {
		return amountRequested;
	}
	public void setAmountRequested(float amountRequested) {
		this.amountRequested = amountRequested;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
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
	
	
	

}
