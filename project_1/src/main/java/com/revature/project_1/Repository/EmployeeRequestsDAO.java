package com.revature.project_1.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.project_1.Model.Employee;
import com.revature.project_1.Model.ReimbursementRequest;

public class EmployeeRequestsDAO extends DAO {
	
	public ArrayList<ReimbursementRequest> getRequests(int employee_id, String employeeType){
		if(employeeType.equals("Employee")) {
			sql = "SELECT rr.request_id, rr.request_type, rr.date_submitted, rr.approved, rr.managerapproved_id, emps.username  from reimbursementrequests as rr inner "
					+ "join employees as emps\r\n" + "on rr.employee_id = emps.employee_id where rr.employee_id = " + employee_id +" order by rr.request_id;";
		}
		else {
			sql = "SELECT rr.request_id, rr.request_type, rr.date_submitted, rr.approved, rr.managerapproved_id, emps.username, mg.username from managers as mg right join reimbursementrequests \r\n" + 
					"as rr on mg.manager_id = rr.managerapproved_id full outer \r\n" + 
					"join employees as emps on rr.employee_id = emps.employee_id order by rr.request_id";
		}
		try {
			
			resultSet = statement.executeQuery(sql);
			ArrayList<ReimbursementRequest> requests = new ArrayList<ReimbursementRequest>();
			while(resultSet.next()) {
				System.out.println(resultSet.getDate(3));
				if(employeeType.equals("Employee")) {
					requests.add(new ReimbursementRequest(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getString(6), 
							resultSet.getString(4), resultSet.getInt(5), ""));	
				}
				else {
					requests.add(new ReimbursementRequest(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getString(6), 
							resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6)));	
				}
						
			}
			return requests;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
