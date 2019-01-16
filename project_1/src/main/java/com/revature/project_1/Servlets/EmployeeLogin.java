package com.revature.project_1.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.project_1.Model.Employee;
import com.revature.project_1.Services.LogInService;

public class EmployeeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String employeeType = request.getParameter("selection");
		LogInService lIS = new LogInService(); 
		Employee employee = lIS.getEmployeeByCredentials(username, password, employeeType);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if(employee == null) {
			out.write("failure");
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("employeeID", employee.getEmployeeID());
			session.setAttribute("employeeUserName", employee.getUsername());
			session.setAttribute("employeePassword", employee.getPassword());
			session.setAttribute("employeeType", employeeType);
			out.write("success");
		}	
	}


}
