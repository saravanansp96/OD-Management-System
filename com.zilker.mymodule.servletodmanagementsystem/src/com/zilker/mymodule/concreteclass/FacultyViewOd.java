package com.zilker.mymodule.concreteclass;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.mymodule.interfaces.ViewOdInterface;
import com.zilker.mymodule.servletdelegate.FacultyPortalDelegate;

public class FacultyViewOd implements ViewOdInterface  {
	
	public void viewOdDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FacultyPortalDelegate facultyPortalDelegate = new FacultyPortalDelegate();
		int facultyId = Integer.parseInt(request.getParameter("faculty-id"));
		String jsonArray = facultyPortalDelegate.getTheOdAprrovedList(facultyId);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonArray);
	}
}
