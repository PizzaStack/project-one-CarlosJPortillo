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

public class LogInDAO {
	
	private Properties properties = new Properties();
	private static Logger logger = Logger.getLogger(LogInDAO.class);
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private String url = "jdbc:postgresql://cjportillo89.cwrr2qklkyum.us-east-2.rds.amazonaws.com:5432/ReinbursementDatabase";
	private String userName = "cjportillo89";
	private String passWord = "augmaticdisport22-";
	private String sql;
	
	/*public LogInDAO() {
		try {
			properties.load(new FileInputStream("C:\\Users\\Carlos\\Documents\\GitHub\\project-one-CarlosJPortillo\\project_1"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		url = properties.getProperty("url");
		userName = properties.getProperty("username");
		passWord = properties.getProperty("password");
		
		PropertyConfigurator.configure(System.getProperty("user.dir") + File.separator +
				"log4j.properties");
	}*/
	public void openConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, userName, passWord);
			statement = connection.createStatement();
			logger.debug("Connection made to database");
			System.out.println("Test");
		} catch(ClassNotFoundException ex){
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Failed to connect to database");
		}
	}
	public Connection getConnection() {
		return connection;
	}
	//see if user and return an employee object 
	public Employee getEmployeeInformation(String username, String password) {
		sql =  "SELECT * from employees where username = '" + username + "' and password = '" + password + "';";
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
