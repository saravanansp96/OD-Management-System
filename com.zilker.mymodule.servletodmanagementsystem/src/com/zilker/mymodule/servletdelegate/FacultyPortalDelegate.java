package com.zilker.mymodule.servletdelegate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import org.json.JSONArray;

import com.zilker.mymodule.bean.PendingOD;
import com.zilker.mymodule.dao.ApproveOD;

public class FacultyPortalDelegate {
	
	public Logger logger = Logger.getLogger(FacultyPortalDelegate.class.getName());
	
	public String getTheOdAprrovedList (int facultyId) {
		ArrayList<PendingOD> odApprovedStudents = new ArrayList<PendingOD>();
		ApproveOD approveOD = new ApproveOD();
		odApprovedStudents = approveOD.displayOdList(facultyId);
		JSONArray arrayOdApprovedList = new JSONArray(odApprovedStudents);	
		return arrayOdApprovedList.toString();
	}
	
	public String getThePendingOdApprovalList(int facultyId) {
		ArrayList<PendingOD> odPendingStudents = new ArrayList<PendingOD>();
		ApproveOD approveOD = new ApproveOD();
		odPendingStudents = approveOD.displayPendingOd(facultyId);
		JSONArray arrayOdPendingList = new JSONArray(odPendingStudents);	
		return arrayOdPendingList.toString();
	}
	
	public String approveTheOd (long registerNumber , String eventDate , String statusOfOd) {
		Date  dateOfEvent = new Date();
		try {
		dateOfEvent = new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);
		}catch (Exception e) {
			logger.info("unable to parse the date");
		}
		ApproveOD approveOD = new ApproveOD();
		String result = "";
		int facultyId = approveOD.getFacultyId(registerNumber);
		
		//check for providing 3 OD per day
		logger.info("inside function");
		if(approveOD.checkForRedundantOd(registerNumber, eventDate)==0) {
			result = "OD rejected!! You have already approved OD for the same student on same day";
			approveOD.change(dateOfEvent, registerNumber, "rejected");
		}
		else if (approveOD.getOdCount(dateOfEvent,facultyId)<3) {
			approveOD.change(dateOfEvent, registerNumber, statusOfOd);
			result = "OD has been "+statusOfOd;
		}
		else  {
			result = "You have already approved 3 OD's for the date "+eventDate;
		}
		return result;
	}
}