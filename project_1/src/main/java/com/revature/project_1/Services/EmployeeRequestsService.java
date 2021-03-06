package com.revature.project_1.Services;

import java.util.ArrayList;

import com.revature.project_1.Model.Employee;
import com.revature.project_1.Model.ReimbursementRequest;
import com.revature.project_1.Repository.EmployeeRequestsDAO;

public class EmployeeRequestsService {
	private EmployeeRequestsDAO employeeRequestsDAO;
	private ArrayList<ReimbursementRequest> requests;
	
	public EmployeeRequestsService() {
		employeeRequestsDAO = new EmployeeRequestsDAO();
		employeeRequestsDAO.openConnection();
	}
	public ArrayList<ReimbursementRequest> getRequests(int employee_ID, String employeeType) {
		requests = employeeRequestsDAO.getRequests(employee_ID, employeeType);
		employeeRequestsDAO.closeConnection();	
		return requests;
	}
	public void setRequests(ArrayList<ReimbursementRequest> requests) {
		this.requests = requests;
	}
	public ArrayList<Employee> getEmployees(){
		ArrayList<Employee> employees = employeeRequestsDAO.GetEmployees();
		return employees;
	}
	public ArrayList<ReimbursementRequest>getRequestsFromEmployee(String username){
		ArrayList<ReimbursementRequest> requests = employeeRequestsDAO.getRequestsFromEmployee(username);
		return requests;
	}

}