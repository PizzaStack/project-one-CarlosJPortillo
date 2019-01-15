package com.revature.project_1.Repository;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SubmitNewRequestDAO extends DAO {
	
	public boolean CreateRequest(int newRequestID, String fileName, String reimbursementType, int employeeID) {
		try {
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date;
			try {
				date = format.parse(timeStamp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CallableStatement stmt = connection.prepareCall("{call MyInsert(?"
					+ ", ?, ?, ?, ?)}");
			stmt.setInt(1, newRequestID);
			stmt.setString(2, fileName);
			stmt.setString(3, reimbursementType);
			stmt.setDate(4, java.sql.Date.valueOf(timeStamp));
			stmt.setInt(5, employeeID);
			stmt.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
}
