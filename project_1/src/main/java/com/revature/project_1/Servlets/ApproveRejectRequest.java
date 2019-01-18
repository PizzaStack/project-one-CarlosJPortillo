package com.revature.project_1.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.project_1.Services.ApproveRejectService;

@WebServlet
@MultipartConfig
public class ApproveRejectRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		int requestID =  Integer.parseInt(request.getParameter("requestID"));
		HttpSession session = request.getSession(false);
		int managerID = (Integer) session.getAttribute("employeeID");
		ApproveRejectService arS = new ApproveRejectService();
		arS.ApproveReject(action, requestID, managerID);
	}

}
