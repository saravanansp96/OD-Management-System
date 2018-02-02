package com.zilker.mymodule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.zilker.mymodule.bean.ApprovalClass;
import com.zilker.mymodule.bean.PendingOD;
import com.zilker.mymodule.constants.Constants;
import com.zilker.mymodule.dbconnection.ConnectionClass;

public class ApproveOD {
	
	public Connection connection;
	public ResultSet resultSet;
	public Constants constants;
	public PreparedStatement statement;
	public Logger logger = Logger.getLogger(ApproveOD.class.getName());
	public ApproveOD() {
		super();
	}

	public ArrayList<ApprovalClass> displayPendingOdDetais(Date date, int facultyId) {
		constants = new Constants();
		ArrayList<ApprovalClass> approvalOD = new ArrayList<ApprovalClass>();
		ConnectionClass gcc = new ConnectionClass();
		try {
			connection = gcc.getConnection();
			statement = connection.prepareStatement(constants.QUERY_OD_ON_DATE);
			statement.setInt(1,facultyId);
			String newstring = new SimpleDateFormat("yyyy-mm-dd").format(date);
			statement.setString(2, newstring);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String eventName = resultSet.getString("Event_Name");
				String name = resultSet.getString("Student_Name");
				long registerNumber = resultSet.getLong("Register_Number");
				int arrear = resultSet.getInt("NSA");
				int attendance = resultSet.getInt("Attendance");
				int total = resultSet.getInt("Total_classes");

				ApprovalClass Details = new ApprovalClass();
				Details.setAttendance(attendance);
				Details.setTotalClass(total);
				Details.setNoOfStandingArrear(arrear);
				Details.setRegisterNumber(registerNumber);
				Details.setStudentName(name);
				Details.setEventName(eventName);

				approvalOD.add(Details);
			}
		} catch (Exception e) {
			logger.info(constants.EXCEPTION_UNABLE_TO_APPROVE_OD);
		}finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return approvalOD;
	}

	public int getOdCount(Date date , int facultyId)
	{
		int count=0;
		ConnectionClass gcc = new ConnectionClass();
		try {
			connection = gcc.getConnection();
			PreparedStatement statement = connection.prepareStatement(constants.QUERY_OD_COUNT_CHECK_ON_GIVEN_DATE);
			statement.setInt(1, facultyId);
			String newstring = new SimpleDateFormat("yyyy-mm-dd").format(date);
			statement.setString(2, newstring);
			resultSet=statement.executeQuery();
			resultSet.next();
			count=resultSet.getInt("count");
		}
		catch(Exception e)
		{
			logger.info(constants.EXCEPTION_UNABLE_TO_GET_OD_COUNT);
		}finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return count;
	}
	
	public void change(Date date, long registerNumber, String status) {
		ConnectionClass gcc = new ConnectionClass();
		try {
			connection = gcc.getConnection();
			if(status.equals("approved")) { 
				statement = connection.prepareStatement(constants.QUERY_UPDATE_STATUS_APPROVE);
			}
			else {
				statement = connection.prepareStatement(constants.QUERY_UPDATE_STATUS_REJECT);
			}
			statement.setLong(1, registerNumber);
			String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);
			statement.setString(2, newstring);
			logger.info(statement.toString());
			statement.executeUpdate();
			statement.close();
			if(status.equals("approved")) {
				statement = connection.prepareStatement(constants.QUERY_UPDATE_OD_COUNT);
				statement.setLong(1, registerNumber);
				statement.executeUpdate();
			}
		} catch (Exception e) {
			logger.info(constants.EXCEPTION_UNABLE_TO_UPDATE_THE_OD_COUNT);
		}finally {
			gcc.closeConnection(connection, statement, resultSet);
		}

	}
	
	public ArrayList<PendingOD> displayOdList (int facultyId)
	{
		constants = new Constants();
		ArrayList<PendingOD> approvedOD = new ArrayList<PendingOD>();
		ConnectionClass gcc = new ConnectionClass();
		try {
			connection = gcc.getConnection();
			statement=connection.prepareStatement(constants.QUERY_GET_OD_LIST);
			statement.setInt(1, facultyId);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
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
				approvedOD.add(Details);
				
			}
		} catch (Exception e) {
			logger.info(constants.EXCEPTION_UNABLE_TO_FETCH_OD_APPROVED_STUDENTS);
		}finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return approvedOD;
	
	}
	
	public ArrayList<PendingOD> displayPendingOd (int facultyId) {
		constants = new Constants();
		ArrayList<PendingOD> approvedOD = new ArrayList<PendingOD>();
		ConnectionClass gcc = new ConnectionClass();
		try {
			connection = gcc.getConnection();
			statement=connection.prepareStatement(constants.QUERY_LIST_OF_PENDING_OD);
			statement.setInt(1, facultyId);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
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
				approvedOD.add(Details);
			}
		} catch (Exception e) {
			logger.info(constants.EXCEPTION_UNABLE_TO_GET_PENDING_OD_DETAILS);
		}finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return approvedOD;
	}
	
	public ArrayList<ApprovalClass> displayPendingOdOnAParticularDate(Date date, int facultyId) {
		constants = new Constants();
		ArrayList<ApprovalClass> approvalOD = new ArrayList<ApprovalClass>();
		ConnectionClass gcc = new ConnectionClass();
		try {
			connection = gcc.getConnection();
			statement = connection.prepareStatement(constants.QUERY_OD_ON_DATE);
			statement.setInt(1,facultyId);
			String newstring = new SimpleDateFormat("yyyy-mm-dd").format(date);
			statement.setString(2, newstring);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String eventName = resultSet.getString("Event_Name");
				String name = resultSet.getString("Student_Name");
				long registerNumber = resultSet.getLong("Register_Number");
				int arrear = resultSet.getInt("NSA");
				int attendance = resultSet.getInt("Attendance");
				int total = resultSet.getInt("Total_classes");

				ApprovalClass Details = new ApprovalClass();
				Details.setAttendance(attendance);
				Details.setTotalClass(total);
				Details.setNoOfStandingArrear(arrear);
				Details.setRegisterNumber(registerNumber);
				Details.setStudentName(name);
				Details.setEventName(eventName);

				approvalOD.add(Details);
			}
		} catch (Exception e) {
			logger.info(constants.EXCEPTION_UNABLE_TO_GET_PENDING_OD_ON_PARTICULAR_DATE);
		}finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return approvalOD;
	}

	
	public int getFacultyId (long registerNumber) {
		constants = new Constants();
		ConnectionClass gcc = new ConnectionClass();
		int facultyId=0;
		try {
			connection = gcc.getConnection();
			statement = connection.prepareStatement(constants.QUERY_GET_FACULTY_ID_FROM_REGISTER_NUMBER);
			statement.setLong(1, registerNumber);
			resultSet=statement.executeQuery();
			resultSet.next();
			facultyId = resultSet.getInt("Faculty_ID");
		}catch (Exception e) {
			logger.info(constants.EXCEPTION_UNABLE_TO_GET_FACULTY_ID);
		}finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return facultyId;
	}
	
	public int checkForRedundantOd (long registerNumber, String dateOfEvent) {
		constants = new Constants();
		ConnectionClass gcc = new ConnectionClass();
		int redundantFlag = 0;
		try {
			connection = gcc.getConnection();
			statement = connection.prepareStatement(constants.QUERY_ALREADY_APPROVED);
			statement.setLong(1, registerNumber);
			statement.setString(2, dateOfEvent);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				redundantFlag = 1;
			}
		}catch(Exception e) {
			logger.info(constants.EXCEPTION_UNABLE_TO_CHECK_DATE_REDUNDANCY_IN_OD);
		}finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return redundantFlag;
	}
}
