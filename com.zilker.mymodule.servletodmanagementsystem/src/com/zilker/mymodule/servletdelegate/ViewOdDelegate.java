package com.zilker.mymodule.servletdelegate;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.mymodule.interfaces.ViewOdInterface;

public class ViewOdDelegate {
	
	public Logger logger = Logger.getLogger(ViewOdDelegate.class.getName());
	
	public ViewOdInterface selectTheView (String type) {
		try {
			logger.info("com.zilker.mymodule.concreteclass."+type);
			return (ViewOdInterface)Class.forName("com.zilker.mymodule.concreteclass."+type).getDeclaredConstructor().newInstance();
		} catch(Exception e) {
			//logger.info("unable to get concrete class");
			e.printStackTrace();
		}
		return null;
	}
	
	public void viewOd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeOfUser = request.getParameter("type");
		ViewOdInterface concreteObject = selectTheView(typeOfUser);
		concreteObject.viewOdDetails(request,response);
		}
}
