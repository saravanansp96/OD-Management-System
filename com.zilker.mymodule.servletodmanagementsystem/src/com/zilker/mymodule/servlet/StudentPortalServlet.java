package com.zilker.mymodule.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zilker.mymodule.servletdelegate.StudentPortalDelegate;

/**
 * Servlet implementation class StudentPortalServlet
 */
@WebServlet("/StudentPortalServlet")
public class StudentPortalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentPortalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id= (String)request.getParameter("id");
		Logger logger = Logger.getLogger(StudentPortalServlet.class.getName());
		//check the http session
		 HttpSession session=request.getSession(false);
		 if(id.equals("logout")) {
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
		 else
	    {    
			String number = (String)request.getParameter("registerNumber");
			long registerNumber = Long.parseLong(number);
		StudentPortalDelegate studentPortalDelegate = new StudentPortalDelegate();
		if(id.equals("viewDetails")) {
			//view details
			String jsonObjectDetails = studentPortalDelegate.getStudentDetails(registerNumber);
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(jsonObjectDetails);
		}
		else if(id.equals("viewStatus")) {
			//view status
			String jsonArray = studentPortalDelegate.getOdDetails(registerNumber);
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(jsonArray);
		}
		else if(id.equals("applyOd")) {
			//apply for od
			String eventName = (String)request.getParameter("eventName");
			String eventDate = (String)request.getParameter("eventDate");
			String odInsertedOrNot = studentPortalDelegate.applyTheOD(registerNumber, eventName, eventDate);
			response.setContentType("text");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(odInsertedOrNot);
		}
	     }
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
