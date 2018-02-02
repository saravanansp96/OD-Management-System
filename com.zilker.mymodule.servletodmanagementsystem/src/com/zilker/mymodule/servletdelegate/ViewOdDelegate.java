package com.zilker.mymodule.servletdelegate;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.mymodule.factory.ViewOdDetailsFactory;
import com.zilker.mymodule.interfaces.ViewOdInterface;

public class ViewOdDelegate {
	
	public Logger logger = Logger.getLogger(ViewOdDelegate.class.getName());
	
	public void viewOd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeOfUser = request.getParameter("type");
		ViewOdDetailsFactory odDetailsFactory = new ViewOdDetailsFactory();
		ViewOdInterface concreteObject = odDetailsFactory.selectTheView(typeOfUser);
		concreteObject.viewOdDetails(request,response);
		}
}
