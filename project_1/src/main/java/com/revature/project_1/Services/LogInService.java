package com.revature.project_1.Services;

import com.revature.project_1.Model.Employee;
import com.revature.project_1.Repository.LogInDAO;

public class LogInService {
	
	private LogInDAO logInDAO;
	public LogInService() {
		logInDAO = new LogInDAO();
		logInDAO.openConnection();
	}
	
	public Employee getEmployeeByCredentials(String username, String password, String employeeType) {
		String tableName = null;
		if(employeeType.equals("Employee")) {
			tableName = "employees";
		}
		else if(employeeType.equals("Manager")) {
			tableName = "managers";
		}
		Employee employee = logInDAO.getEmployeeInformation(username, password, tableName );
		logInDAO.closeConnection();
		return employee;
	}

}
