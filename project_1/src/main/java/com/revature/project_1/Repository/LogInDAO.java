package com.revature.project_1.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.revature.project_1.Model.Employee;

public class LogInDAO extends DAO {
	
	//see if user and return an employee object 
	public Employee getEmployeeInformation(String username, String password, String tableName) {
		sql =  "SELECT * from " + tableName + " where username = '" + username + "' and password = '" + password + "';";
		try {
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
				return employee;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
