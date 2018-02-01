package com.zilker.mymodule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.zilker.mymodule.constants.Constants;
import com.zilker.mymodule.dbconnection.ConnectionClass;


public class ValidateFaculty {
	public Connection connection;
	public ResultSet resultSet;
	public Constants mysqlConstants;
	public PreparedStatement statement;
	
	public Logger logger = Logger.getLogger(ValidateFaculty.class.getName());

	public int validateFaculty(int facultyID) {
		mysqlConstants = new Constants();
		int flag=0;
		ConnectionClass gcc = new ConnectionClass();
		try {
			connection = gcc.getConnection();
		    statement=connection.prepareStatement(mysqlConstants.QUERY_VALIDATE_FACULTY);
			statement.setInt(1, facultyID);	
			resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				flag=0;
			} else {
				flag=1;
			}
		} catch (Exception e) {
			logger.info("unable to validate the faculty details");
		}
		finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return flag;
	}

	public String getFacultyName (int facultyId) {
		mysqlConstants=new Constants();
		ConnectionClass gcc = new ConnectionClass();
		String name=null;
		try {
			connection = gcc.getConnection();
		    statement=connection.prepareStatement(mysqlConstants.QUERY_GET_FACULTY_NAME);
			statement.setInt(1, facultyId);
			resultSet = statement.executeQuery();
			resultSet.next();
			name = resultSet.getString("Faculty_Name");
	}catch (Exception e) {
		logger.info("unable to get the name of faculty");
	}finally {
		gcc.closeConnection(connection, statement, resultSet);
	}
		return name;
	}
}
