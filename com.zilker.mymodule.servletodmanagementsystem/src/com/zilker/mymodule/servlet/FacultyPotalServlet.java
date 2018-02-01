package com.zilker.mymodule.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zilker.mymodule.servletdelegate.FacultyPortalDelegate;

/**
 * Servlet implementation class FacultyPotalServlet
 */
@WebServlet("/FacultyPotalServlet")
public class FacultyPotalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacultyPotalServlet() {
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
		FacultyPortalDelegate facultyPortalDelegate = new FacultyPortalDelegate();
		HttpSession session=request.getSession(false);
		 if(id.equals("logout")) {
			 if(session!=null) {
				 if(session.getAttribute("facultyId")!=null) {
					 session.removeAttribute("facultyId");
				 }
				 if(session.getAttribute("password")!=null) {
					 session.removeAttribute("password");
				 }
				 session.invalidate();
			 }
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
			requestDispatcher.forward(request, response);
	     }
		 else {
			 int facultyId = Integer.parseInt(request.getParameter("faculty-id"));
			 if (id.equals("odApprovedStudents")) {
			 String jsonArray = facultyPortalDelegate.getTheOdAprrovedList(facultyId); 
			 response.setContentType("application/json");
			 response.setCharacterEncoding("UTF-8");
			 response.getWriter().write(jsonArray);
			 }
			 else if (id.equals("odPendingStudents")) {
				 String jsonArray = facultyPortalDelegate.getThePendingOdApprovalList(facultyId);
				 response.setContentType("application/json");
				 response.setCharacterEncoding("UTF-8");
				 response.getWriter().write(jsonArray);
			 }
			 else if (id.equals("odChangeStatus")) {
				 long registerNumber = Long.parseLong(request.getParameter("register-number"));
				 String dateOfEvent = request.getParameter("date-of-od");
				 String statusOfOd = request.getParameter("status-of-od");
				 String messageToFrontEnd = facultyPortalDelegate.approveTheOd(registerNumber, dateOfEvent, statusOfOd);
				 response.setContentType("text");
				 response.setCharacterEncoding("UTF-8");
				 response.getWriter().write(messageToFrontEnd);
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
