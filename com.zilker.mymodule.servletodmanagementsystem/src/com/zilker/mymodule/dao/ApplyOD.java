package com.zilker.mymodule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.zilker.mymodule.constants.Constants;
import com.zilker.mymodule.dao.ApplyOD;
import com.zilker.mymodule.dbconnection.ConnectionClass;

public class ApplyOD {

	public Connection connection;
	public ResultSet resultSet, resultSet2;
	public Constants constants;
	public PreparedStatement statement;
	public Logger logger = Logger.getLogger(ApplyOD.class.getName());

	public int applyOD(long registerNumber, String eventName, String eventDate) {
		constants = new Constants();
		int flag = 0;
		ConnectionClass gcc = new ConnectionClass();
		try {
			connection = gcc.getConnection();
			statement = connection.prepareStatement(constants.QUERY_DUPLICATE_ENTRY_CHECK_OD_DATE);
			statement.setLong(1, registerNumber);
			statement.setString(2, eventDate);
			resultSet = statement.executeQuery();
			statement = connection.prepareStatement(constants.QUERY_DUPLICATE_CHECK);
			statement.setLong(1, registerNumber);
			statement.setString(2, eventDate);
			resultSet2 = statement.executeQuery();
			if (resultSet.next() || !resultSet2.next()) {
				statement = connection.prepareStatement(constants.QUERY_ATTENDANCE_DETAILS);
				statement.setLong(1, registerNumber);
				resultSet = statement.executeQuery();
				resultSet.next();
				int daysAttended = resultSet.getInt("Attendance");
				int totalDays = resultSet.getInt("Total_classes");
				int collegeRepresentativeFlag = resultSet.getInt("special");
				float attendancePercentange = (((float) daysAttended / (float) totalDays) * 100);
				Date dateOfEvent = new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);
				Date currentDate = new Date();
				if (attendancePercentange > 75.00 || collegeRepresentativeFlag == 1) {
					if (dateOfEvent.compareTo(currentDate) > 0) {
						statement = connection.prepareStatement(constants.QUERY_SPECIAL);
						statement.setLong(1, registerNumber);
						resultSet = statement.executeQuery();
						resultSet.next();
						int splFlag = resultSet.getInt("special");
						if (splFlag == 0) {
							statement = connection.prepareStatement(constants.QUERY_OD_CHECK);
						} else {
							statement = connection.prepareStatement(constants.QUERY_OD_CHECK1);
						}
						statement.setLong(1, registerNumber);
						statement.setLong(2, registerNumber);
						resultSet = statement.executeQuery();
						resultSet.next();
						int odCount = resultSet.getInt("OD_Taken");
						if ((odCount < 5 && splFlag == 0) || splFlag == 1) {
							statement = connection.prepareStatement(constants.QUERY_OD_INSERT);
							// String newstring = new SimpleDateFormat("yyyy-mm-dd").format(eventDate);
							statement.setLong(1, registerNumber);
							statement.setString(2, eventName);
							statement.setString(3, eventDate);
							int statusOfQuery = statement.executeUpdate();
							if (statusOfQuery != 0) {
								flag = 1;
								logger.info("od applied");
							} else {
								flag = 0;
							}
						} else {
							flag = -2;
						}
					} else {
						flag = -1;
					}
				} else {
					flag = -3;
				}
			} else {
				flag = -4;
			}
			resultSet2.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return flag;
	}

}
