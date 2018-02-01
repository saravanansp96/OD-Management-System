package com.zilker.mymodule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.zilker.mymodule.constants.Constants;
import com.zilker.mymodule.dbconnection.ConnectionClass;

public class ValidateStudent {
	public Connection connection;
	public ResultSet resultSet;
	public Constants mysqlConstants;
	public PreparedStatement statement;

	public Logger logger = Logger.getLogger(ValidateStudent.class.getName());
	
	public int validateStudent(long registerNumber) {
		int flag=0;
		mysqlConstants=new Constants();
		ConnectionClass gcc = new ConnectionClass();
		try {
			
			connection = gcc.getConnection();
		    statement=connection.prepareStatement(mysqlConstants.QUERY_VALIDATE_STUDENT);
			statement.setLong(1, registerNumber);
			resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				flag=0;
			} else {
				flag=1;
			}
		} catch (Exception e) {
			logger.info("unable to validate the student details");
		}
		finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
			return flag;
	}
	
	
	public String getNameOfStudent(long registerNumber) {
		mysqlConstants=new Constants();
		ConnectionClass gcc = new ConnectionClass();
		String name=null;
		try {
			
			connection = gcc.getConnection();
		    statement=connection.prepareStatement(mysqlConstants.QUERY_GET_STUDENT_DETAILS);
			statement.setLong(1, registerNumber);
			resultSet = statement.executeQuery();
			resultSet.next();
			name = resultSet.getString("Student_Name");
	}catch (Exception e) {
		logger.info("unable to get the name of student");
	}finally {
		gcc.closeConnection(connection, statement, resultSet);
	}
		return name;
}
}