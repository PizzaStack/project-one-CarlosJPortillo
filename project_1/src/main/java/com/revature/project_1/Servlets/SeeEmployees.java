package com.revature.project_1.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.project_1.Model.Employee;
import com.revature.project_1.Model.ReimbursementRequest;
import com.revature.project_1.Services.EmployeeRequestsService;


public class SeeEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		EmployeeRequestsService eRS = new EmployeeRequestsService();
		ArrayList<ReimbursementRequest> requests = eRS.getRequestsFromEmployee(username);
		Gson gson = new Gson();
		String requestsJson = gson.toJson(requests);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(requestsJson);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!= null) {
		EmployeeRequestsService eRS = new EmployeeRequestsService();
		eRS.getEmployees();
		ArrayList<Employee> employees = eRS.getEmployees();
		Gson gson = new Gson();
		String employeesJson = gson.toJson(employees);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeesJson);
        out.flush();  
		}
		else {
			CheckedLoggedIn.SendNotLoggedInResponse(response);
		}
	}

}
