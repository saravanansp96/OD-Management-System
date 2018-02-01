package com.zilker.mymodule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.zilker.mymodule.bean.OnDutyDetails;
import com.zilker.mymodule.constants.Constants;
import com.zilker.mymodule.dao.ViewODStatus;
import com.zilker.mymodule.dbconnection.ConnectionClass;

public class ViewODStatus {
	public Connection connection;
	public ResultSet resultSet;
	public 	Constants mysqlConstants;
	public PreparedStatement statement;
	
	public Logger logger = Logger.getLogger(ViewODStatus.class.getName());
	
	public ArrayList<OnDutyDetails> viewODStatus(long registerNumber) {
		mysqlConstants = new Constants(); 
		ArrayList<OnDutyDetails> arrayOdDetails = new ArrayList<OnDutyDetails>();
		ConnectionClass gcc = new ConnectionClass();
		try {
			connection = gcc.getConnection();
		    statement=connection.prepareStatement(mysqlConstants.QUERY_OD_STATUS);
			statement.setLong(1,registerNumber);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String eventName = resultSet.getString("Event_Name");
				Date date = resultSet.getDate("Date_of_Event");
				String status = resultSet.getString("Status_of_OD");
				OnDutyDetails Details = new OnDutyDetails();
				Details.setDateOfEvent(date);
				Details.setEventName(eventName);
				Details.setStatus(status);
				arrayOdDetails.add(Details);
			}
		} catch (Exception e) {
			logger.info("unable to view OD status");
		}finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return arrayOdDetails;
	}
}
