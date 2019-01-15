package com.revature.project_1.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public abstract class DAO {
	private Properties properties = new Properties();
	private static Logger logger = Logger.getLogger(LogInDAO.class);
	
	protected Connection connection;
	protected Statement statement;
	protected ResultSet resultSet;
	private String url = "jdbc:postgresql://cjportillo89.cwrr2qklkyum.us-east-2.rds.amazonaws.com:5432/ReinbursementDatabase";
	private String userName = "cjportillo89";
	private String passWord = "augmaticdisport22-";
	protected String sql;
	
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

	public void closeConnection() {
		try {
			connection.close();
			logger.debug("connection closed successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("failed to close connection");
		}
	}
	public int getMaxID(String table, String columnValue) {
		int maxId = 0;
	    sql =  "SELECT MAX( " + columnValue + ") as MaxId FROM " + table;
		try {
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				maxId = resultSet.getInt("MaxId");
			}
			
		} catch (SQLException e) {
			System.out.println("An error has occured with trying to execute query");
			
		}
		maxId++;
		return maxId;
	}

}
