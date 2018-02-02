package com.zilker.mymodule.constants;

public class Constants {
	public final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	public final String DB_NAME = "jdbc:mysql://localhost/Attendance_management_system";

	public final String USERNAME = "root";

	public final String PASSWORD = "zilkeradmin";

	public final String QUERY_VALIDATE_STUDENT = "select Register_Number from studentdetails where Register_Number=?;";

	public final String QUERY_GET_STUDENT_DETAILS = "select * from studentdetails where Register_Number=?;";

	public final String QUERY_DELIMITER = ";";

	public final String QUERY_DISPLAY_DETAILS = "select * from studentdetails , AttendanceDetails where AttendanceDetails.Register_Number=? and studentdetails.Register_Number=?;";

	public final String QUERY_OD_CHECK = "select OD_Taken from AttendanceDetails, studentdetails  where AttendanceDetails.Register_number=? and  studentdetails.Register_Number=?  and studentdetails.special=0;";

	public final String QUERY_OD_CHECK1 = "select OD_Taken from AttendanceDetails, studentdetails  where AttendanceDetails.Register_number=? and  studentdetails.Register_Number=?  and studentdetails.special=1;";

	public final String QUERY_OD_INSERT = "insert into odDetails (Register_Number,Event_Name,Date_of_Event) values (?,?,?);";

	public final String QUERY_OD_STATUS = "select * from oddetails where Register_Number=? order by Date_of_Event DESC;";

	public final String QUERY_SPECIAL = "select special from studentdetails where Register_Number=?";

	public final String QUERY_ATTENDANCE_DETAILS = "select AttendanceDetails.Attendance, AttendanceDetails.Total_classes , studentdetails.special from AttendanceDetails, studentdetails  where AttendanceDetails.Register_number=? and  studentdetails.Register_Number=AttendanceDetails.Register_number;";

	public final String QUERY_DUPLICATE_ENTRY_CHECK_OD_DATE = "select * from oddetails where Register_Number=? and Date_of_Event=? and Status_of_OD= 'rejected';";

	public final String QUERY_DUPLICATE_CHECK = "select * from oddetails where Register_Number=? and Date_of_Event=?;";

	// faculty constants

	public final String QUERY_VALIDATE_FACULTY = "select Faculty_ID from facultydetails where Faculty_ID=?";

	public final String QUERY_GET_FACULTY_NAME = "select Faculty_Name from facultydetails where Faculty_ID=?";

	public final String QUERY_PENDING_OD = "select a.Register_Number, a.Student_Name ,c.Event_Name , c.Date_of_Event ,c.Status_of_OD from StudentDetails a,oddetails c where c.Register_Number=a.Register_Number and a.Faculty_ID=? and c.Status_of_OD='pending' order by c.Date_of_Event;";

	public final String QUERY_OD_ON_DATE = "select a.Register_Number, a.Student_Name,c.Event_Name, a.NSA ,d.Attendance , d.Total_classes from StudentDetails a,AttendanceDetails d, odDetails c where a.Faculty_ID=?  and a.Register_Number=c.Register_Number and a.Register_Number=d.Register_Number and c.Status_of_OD='pending' and c.Date_of_Event=?";

	public final String QUERY_UPDATE_STATUS_APPROVE = "update oddetails set Status_of_OD= 'approved' where Register_Number=? and Date_of_Event=? ";

	public final String QUERY_UPDATE_STATUS_REJECT = "update oddetails set Status_of_OD= 'rejected' where Register_Number=? and Date_of_Event=? ";

	public final String QUERY_UPDATE_OD_COUNT = "update AttendanceDetails set OD_Taken=OD_Taken+1 where Register_Number=?";

	public final String QUERY_OD_COUNT_CHECK_ON_GIVEN_DATE = "select count(*) as count from oddetails c, StudentDetails a where a.Faculty_ID =? and c.Status_of_OD='approved' and a.Register_Number=c.Register_Number and a.special=0 and c.Date_of_Event=? ;";

	public final String QUERY_LIST_OF_APPROVED_OD = "select a.Register_Number, a.Student_Name ,c.Event_Name , c.Date_of_Event ,c.Status_of_OD from oddetails c, StudentDetails a where a.Faculty_ID =? and c.Status_of_OD='approved' and a.Register_Number=c.Register_Number order by c.Date_of_Event;";

	public final String QUERY_LIST_OF_PENDING_OD = "select a.Register_Number, a.Student_Name ,c.Event_Name , c.Date_of_Event ,c.Status_of_OD from oddetails c, StudentDetails a where a.Faculty_ID =? and c.Status_of_OD='pending' and a.Register_Number=c.Register_Number order by c.Date_of_Event;";

	public final String QUERY_GET_FACULTY_ID_FROM_REGISTER_NUMBER = "select Faculty_ID from studentdetails where Register_Number = ?";

	public final String QUERY_ALREADY_APPROVED = "select * from oddetails where Register_Number=? and Date_of_Event=? and Status_of_OD= 'approved'; ";
	
	public final String QUERY_GET_OD_LIST = "select a.Register_Number, a.Student_Name ,c.Event_Name , c.Date_of_Event ,c.Status_of_OD from oddetails c, StudentDetails a where a.Faculty_ID =? and a.Register_Number=c.Register_Number order by c.Date_of_Event;";

	// Exception Constants
	
	public final String EXCEPTION_UNABLE_TO_CREATE_CONNECTION = "Unable to create connection";

	public final String EXCEPTION_UNABLE_TO_CLOSE_CONNECTION = "Unable to close connection";
	
	public final String EXCEPTION_UNABLE_TO_APPROVE_OD = "Unable to approve OD";
	
	public final String EXCEPTION_UNABLE_TO_GET_OD_COUNT = "Unable to get the od count";
	
	public final String EXCEPTION_UNABLE_TO_UPDATE_THE_OD_COUNT = "Unable to update the od count";
	
	public final String EXCEPTION_UNABLE_TO_FETCH_OD_APPROVED_STUDENTS = "Unable to get the OD approved Students";
	
	public final String EXCEPTION_UNABLE_TO_CHECK_DATE_REDUNDANCY_IN_OD = "Unable to check the redndancy in OD";
	
	public final String EXCEPTION_UNABLE_TO_GET_FACULTY_ID = "Unable to get the faculty ID";
	
	public final String EXCEPTION_UNABLE_TO_GET_PENDING_OD_ON_PARTICULAR_DATE = "Unable to get the pending OD details on particular date";
	
	public final String EXCEPTION_UNABLE_TO_GET_PENDING_OD_DETAILS = "Unable to get the pending OD details";
	
	public final String EXCEPTION_UNABLE_TO_GET_STUDENT_DETAILS_FROM_TABLE = "Unable to get the student details from table";
	
	
	
	// UserInterface Constants

}
