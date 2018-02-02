package com.zilker.mymodule.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.mymodule.servletdelegate.StudentPortalDelegate;


/**
 * Servlet implementation class StudentLogoutServlet
 */
@WebServlet("/StudentLogoutServlet")
public class StudentLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		StudentPortalDelegate studentPortalDelegate = new StudentPortalDelegate();
		studentPortalDelegate.logOutStudent(request, response);
	     
		 /*else
	    {    
			String number = (String)request.getParameter("registerNumber");
			long registerNumber = Long.parseLong(number);
		StudentPortalDelegate studentPortalDelegate = new StudentPortalDelegate();
		if(id.equals("viewDetails")) {
			//view details
			studentPortalDelegate.getStudentDetails(request,response);
			/*response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(jsonObjectDetails);
		}
		else if(id.equals("viewStatus")) {//use factory class
			//view status
			studentPortalDelegate.getOdDetails(request,response);
			/*response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(jsonArray);
		}
		else if(id.equals("applyOd")) {
			//apply for od
			String eventName = (String)request.getParameter("eventName");
			String eventDate = (String)request.getParameter("eventDate");
			studentPortalDelegate.applyTheOD(request,response);
			/*response.setContentType("text");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(odInsertedOrNot);
		}
	     }*/
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
