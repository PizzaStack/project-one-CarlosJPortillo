package com.revature.project_1.Services;

import com.revature.project_1.Model.Employee;
import com.revature.project_1.Repository.LogInDAO;

public class LogInService {
	
	private LogInDAO logInDAO;
	public LogInService() {
		logInDAO = new LogInDAO();
		logInDAO.openConnection();
	}
	
	public Employee getEmployeeByCredentials(String username, String password) {
		Employee employee = logInDAO.getEmployeeInformation(username, password);
		return employee;
	}

}
