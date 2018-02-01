package com.zilker.mymodule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.zilker.mymodule.bean.StudentDetails;
import com.zilker.mymodule.constants.Constants;
import com.zilker.mymodule.dao.DisplayStudentDetails;
import com.zilker.mymodule.dbconnection.ConnectionClass;

public class DisplayStudentDetails {
	public Connection connection;
	public ResultSet resultSet;
	public Constants constants;
	public PreparedStatement statement;
	
	public Logger logger = Logger.getLogger(DisplayStudentDetails.class.getName());
	
	public StudentDetails displayStudentDetails(long registernumber) {

		String studentName, department;
		float cgpa;
		int noOfStandingArrear, year, facultyId, special;
		int attendance, odTaken, totalClass;
		StudentDetails student = new StudentDetails();
		constants = new Constants();
		ConnectionClass gcc = new ConnectionClass();
		try {
			
			connection = gcc.getConnection();
			statement = connection.prepareStatement(constants.QUERY_DISPLAY_DETAILS);
			statement.setLong(1, registernumber);
			statement.setLong(2, registernumber);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				studentName = resultSet.getString("Student_Name");
				department = resultSet.getString("Dept");
				cgpa = resultSet.getFloat("CGPA");
				noOfStandingArrear = resultSet.getInt("NSA");
				year = resultSet.getInt("Year_");
				facultyId = resultSet.getInt("Faculty_ID");
				special = resultSet.getInt("special");
				attendance = resultSet.getInt("Attendance");
				odTaken = resultSet.getInt("OD_Taken");
				totalClass = resultSet.getInt("Total_classes");

				student.setAttendance(attendance);
				student.setDepartment(department);
				student.setCgpa(cgpa);
				student.setFacultyId(facultyId);
				student.setNoOfStandingArrear(noOfStandingArrear);
				student.setRegisterNumber(registernumber);
				student.setYear(year);
				student.setTotalClass(totalClass);
				student.setOdTaken(odTaken);
				student.setStudentName(studentName);
				student.setSpecial(special);

			}
		} catch (Exception e) {
			logger.info(constants.EXCEPTION_UNABLE_TO_GET_STUDENT_DETAILS_FROM_TABLE);
		}
		finally {
			gcc.closeConnection(connection, statement, resultSet);
		}
		return student;

	}

}
