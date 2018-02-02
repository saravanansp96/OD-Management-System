package com.zilker.mymodule.concreteclass;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.mymodule.interfaces.ViewOdInterface;
import com.zilker.mymodule.servletdelegate.StudentPortalDelegate;

public class StudentViewOd implements ViewOdInterface {
	public void viewOdDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentPortalDelegate studentPortalDelegate = new StudentPortalDelegate();
		studentPortalDelegate.getOdDetails(request,response);
	}
}
