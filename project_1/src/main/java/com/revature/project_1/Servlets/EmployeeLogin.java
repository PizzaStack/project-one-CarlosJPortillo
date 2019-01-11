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

/**
 * Servlet implementation class EmployeeLogin
 */
public class EmployeeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LogInService lIS = new LogInService(); 
		Employee employee = lIS.getEmployeeByCredentials(request.getParameter("username"), request.getParameter("password"));
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
			out.write("success");
		}	
	}


}
