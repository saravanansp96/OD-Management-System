package com.zilker.mymodule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.zilker.mymodule.bean.PendingOD;
import com.zilker.mymodule.constants.Constants;
import com.zilker.mymodule.dbconnection.ConnectionClass;

public class OdAppliedStudents {
	
	public Connection connection;
	public ResultSet resultSet;
	public Constants mysqlConstants;
	public PreparedStatement statement;

	public Logger logger = Logger.getLogger(OdAppliedStudents.class.getName());
	
	public ArrayList<PendingOD> viewODStatus(int facultyId) {
		mysqlConstants = new Constants();
		ArrayList<PendingOD> arrayPendingOdDetails = new ArrayList<PendingOD>();
		ConnectionClass gcc = new ConnectionClass();
		try {
			connection = gcc.getConnection();
		    statement=connection.prepareStatement(mysqlConstants.QUERY_PENDING_OD);
			statement.setInt(1, facultyId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String eventName = resultSet.getString("Event_Name");
				Date date = resultSet.getDate("Date_of_Event");
				String name = resultSet.getString("Student_Name");
				long registerNumber = resultSet.getLong("Register_Number");
				String status = resultSet.getString("Status_of_OD");
				PendingOD Details = new PendingOD();
				Details.setDateOfEvent(date);
				Details.setEventName(eventName);
				Details.setRegisterNumber(registerNumber);
				Details.setStudent_name(name);
				Details.setStatus(status);
				arrayPendingOdDetails.add(Details);
			}
		} catch (Exception e) {
			logger.info("unable to apply the OD");
		}
		finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return arrayPendingOdDetails;
	}

}
