package com.revature.project_1.Services;

import com.revature.project_1.Repository.SubmitNewRequestDAO;

public class SubmitNewRequestService {
	private SubmitNewRequestDAO submitNewRequestDAO;
	
	public SubmitNewRequestService() {
		submitNewRequestDAO = new SubmitNewRequestDAO();
		submitNewRequestDAO.openConnection();
	}
	public boolean createRequest(String fileName, String reimbursementType, int employeeID) {
		int newRequestID = submitNewRequestDAO.getMaxID("reimbursementrequests", "request_id");
		boolean success = submitNewRequestDAO.CreateRequest(newRequestID, fileName, reimbursementType, employeeID);
		return success;
	}
}
