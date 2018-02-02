package com.zilker.mymodule.servletdelegate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.zilker.mymodule.bean.OnDutyDetails;
import com.zilker.mymodule.bean.StudentDetails;
import com.zilker.mymodule.dao.ApplyOD;
import com.zilker.mymodule.dao.DisplayStudentDetails;
import com.zilker.mymodule.dao.ViewODStatus;
import com.zilker.mymodule.servlet.StudentLogoutServlet;

public class StudentPortalDelegate {

	public String getStudentDetails (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String number = (String)request.getParameter("registerNumber");
		long registerNumber = Long.parseLong(number);
		DisplayStudentDetails displayStudentDetails = new DisplayStudentDetails();
		StudentDetails studentDetails = displayStudentDetails.displayStudentDetails(registerNumber);
		String jsonObjectDetails = "{ \"RegisterNumber\":" + studentDetails.getRegisterNumber() + ",\"Name\":\""+ studentDetails.getStudentName() + "\",\"Year\":"+ studentDetails.getYear() + ",\"Department\":\"" + studentDetails.getDepartment() + "\",\"CGPA\":"+ studentDetails.getCgpa() + ",\"OdTaken\":"+ studentDetails.getOdTaken() + ",\"Attendance\":"+String.format("%.2f", ((float) studentDetails.getAttendance() / (float) studentDetails.getTotalClass()) * 100)+"}";
	    return jsonObjectDetails;
//	    response.getWriter().write(jsonObjectDetails);
	}
	
	public void getOdDetails (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String number = (String)request.getParameter("registerNumber");
		long registerNumber = Long.parseLong(number);
		ArrayList<OnDutyDetails> arrayOdDetails = new ArrayList<OnDutyDetails>();
		ViewODStatus viewODStatus = new ViewODStatus();
		arrayOdDetails = viewODStatus.viewODStatus(registerNumber);
		JSONArray viewODJsonArray = new JSONArray(arrayOdDetails);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(viewODJsonArray.toString());
	}
	
	public String applyTheOD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String number = (String)request.getParameter("registerNumber");
		long registerNumber = Long.parseLong(number);
		String eventName = (String)request.getParameter("eventName");
		String eventDate = (String)request.getParameter("eventDate");
		ApplyOD applyOD = new ApplyOD();
		String odInsertedOrNot;
		int flag = applyOD.applyOD(registerNumber, eventName, eventDate);
		if(flag == 0)
			odInsertedOrNot = "Unable to apply OD";
		else if (flag == -1)
			odInsertedOrNot = "You can't apply past dates";
		else if (flag == -2) 
			odInsertedOrNot =  "OD limit is reached";
		else if (flag == -3)
			odInsertedOrNot =  "Due to low attendace,You can't apply for OD";
		else if (flag == -4)
			odInsertedOrNot = "You have already an OD for the date "+eventDate;
		else 
			odInsertedOrNot = "OD applied Successfully";
		return odInsertedOrNot;
	}
	
	public void logOutStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Logger logger = Logger.getLogger(StudentLogoutServlet.class.getName());
	//check the http session
	 HttpSession session=request.getSession(false);
		 if(session!=null) {
			 if(session.getAttribute("registerNumber")!=null) {
				 session.removeAttribute("registerNumber");
			 }
			 if(session.getAttribute("password")!=null) {
				 session.removeAttribute("password");
			 }
			 session.invalidate();
		 }	 
         logger.info("logging out");
    	 RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
    	 requestDispatcher.forward(request, response);
	}
	
}
