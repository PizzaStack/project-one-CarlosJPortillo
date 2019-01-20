package com.revature.project_1.Repository;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class ApproveRejectDAO extends DAO {
	public void Approve_Reject(String action, int requestID, int managerID){
		CallableStatement stmt;
		try {
			if(action.equals("approve")) {
				stmt = connection.prepareCall("{call approve(?, ?)}");
				stmt.setInt(1, requestID);
				stmt.setInt(2, managerID);
				stmt.executeUpdate();
			}
			else {
				stmt = connection.prepareCall("{call reject(?, ?)}");
				stmt.setInt(1, requestID);
				stmt.setInt(2, managerID);
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
