package com.revature.project_1.Services;

import com.revature.project_1.Repository.ApproveRejectDAO;

public class ApproveRejectService {
private ApproveRejectDAO approveRejectDAO;
	
	public ApproveRejectService() {
		approveRejectDAO = new ApproveRejectDAO();
		approveRejectDAO.openConnection();
	}
	public void ApproveReject(String action, int requestID, int managerID) {
		approveRejectDAO.Approve_Reject(action, requestID, managerID);
	}
}
