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
import com.revature.project_1.Model.ReimbursementRequest;
import com.revature.project_1.Services.EmployeeRequestsService;

public class EmployeeHomepage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeHomepage() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!= null) {
			int employeeID = (Integer) session.getAttribute("employeeID");
			EmployeeRequestsService eRS = new EmployeeRequestsService();
			ArrayList<ReimbursementRequest> requests = eRS.getRequests(employeeID);
			Gson gson = new Gson();
			String requestsJson = gson.toJson(requests);
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(requestsJson);
	        out.flush();   
		}
		else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("Not Logged In");
		}
				
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
