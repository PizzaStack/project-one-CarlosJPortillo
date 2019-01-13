package com.revature.project_1.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.project_1.Model.Employee;
import com.revature.project_1.Model.ReimbursementRequest;

public class EmployeeRequestsDAO extends DAO {
	
	public ArrayList<ReimbursementRequest> getRequests(int employee_id){
		sql = "SELECT rr.request_id, rr.request_type, rr.date_submitted, rr.approved, rr.managerapproved_id, emps.username  from reimbursementrequests as rr inner "
				+ "join employees as emps\r\n" + "on rr.employee_id = emps.employee_id where rr.employee_id = " + employee_id +";";
		try {
			
			resultSet = statement.executeQuery(sql);
			ArrayList<ReimbursementRequest> requests = new ArrayList<ReimbursementRequest>();
			while(resultSet.next()) {
				System.out.println(resultSet.getDate(3));
				requests.add(new ReimbursementRequest(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getString(6), 
						resultSet.getBoolean(4), resultSet.getInt(5)));			
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
