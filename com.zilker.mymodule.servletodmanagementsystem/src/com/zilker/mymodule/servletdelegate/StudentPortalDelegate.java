package com.zilker.mymodule.servletdelegate;

import java.util.ArrayList;

import org.json.JSONArray;

import com.zilker.mymodule.bean.OnDutyDetails;
import com.zilker.mymodule.bean.StudentDetails;
import com.zilker.mymodule.dao.ApplyOD;
import com.zilker.mymodule.dao.DisplayStudentDetails;
import com.zilker.mymodule.dao.ViewODStatus;

public class StudentPortalDelegate {

	public String getStudentDetails (long registerNumber) {
		DisplayStudentDetails displayStudentDetails = new DisplayStudentDetails();
		StudentDetails studentDetails = displayStudentDetails.displayStudentDetails(registerNumber);
		String jsonObjectDetails = "{ \"RegisterNumber\":" + studentDetails.getRegisterNumber() + ",\"Name\":\""+ studentDetails.getStudentName() + "\",\"Year\":"+ studentDetails.getYear() + ",\"Department\":\"" + studentDetails.getDepartment() + "\",\"CGPA\":"+ studentDetails.getCgpa() + ",\"OdTaken\":"+ studentDetails.getOdTaken() + ",\"Attendance\":"+String.format("%.2f", ((float) studentDetails.getAttendance() / (float) studentDetails.getTotalClass()) * 100)+"}";
		return jsonObjectDetails;
	}
	
	public String getOdDetails (long registerNumber) {
		ArrayList<OnDutyDetails> arrayOdDetails = new ArrayList<OnDutyDetails>();
		ViewODStatus viewODStatus = new ViewODStatus();
		arrayOdDetails = viewODStatus.viewODStatus(registerNumber);
		JSONArray viewODJsonArray = new JSONArray(arrayOdDetails);
		return viewODJsonArray.toString();
	}
	
	public String applyTheOD(long registerNumber,String eventName, String eventDate) {
		ApplyOD applyOD = new ApplyOD();
		int flag = applyOD.applyOD(registerNumber, eventName, eventDate);
		if(flag == 0)
			return ("Unable to apply OD");
		else if (flag == -1)
			return ("You can't apply past dates");
		else if (flag == -2) 
			return ("OD limit is reached");
		else if (flag == -3)
			return ("Due to low attendace,You can't apply for OD");
		else if (flag == -4)
			return ("You have already an OD for the date "+eventDate);
		else 
			return ("OD applied Successfully");
	}
	
}
