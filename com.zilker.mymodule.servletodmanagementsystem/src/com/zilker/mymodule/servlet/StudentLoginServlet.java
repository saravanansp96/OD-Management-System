package com.zilker.mymodule.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zilker.mymodule.servletdelegate.StudentLoginDelegate;

/**
 * Servlet implementation class StudentLoginServlet
 */
@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String registerNumber, password ,facultyId, typeOfLogin;
		HttpSession session = request.getSession();
		//session.invalidate();
		if (session.getAttribute("id") == null) {
			
			if(request.getParameter("id") == null ) {
				response.sendRedirect("index.html");
				return;
			}
			typeOfLogin = request.getParameter("id");
			session.setAttribute("id", typeOfLogin);
		}else {
			typeOfLogin = (String)session.getAttribute("id");
		}
			
			if (typeOfLogin.equals("StudentLogin")) {
					
				if (session.getAttribute("registerNumber") == null) {
					
					if (request.getParameter("registration-number") == null) {
						
						response.sendRedirect("index.html");
						return;
					}
					registerNumber = request.getParameter("registration-number");
					password = request.getParameter("student-password");
					session.setAttribute("registerNumber", registerNumber);
					session.setAttribute("password", password);
				} else {
					registerNumber = (String) session.getAttribute("registerNumber");
					password = (String) session.getAttribute("password");
				}
				StudentLoginDelegate studentLoginDelegate = new StudentLoginDelegate();
				if (studentLoginDelegate.studentLoginCheck(Long.parseLong(registerNumber), password) == 1) {
					// goto student portal page
					request.setAttribute("register-number", registerNumber);
					request.setAttribute("name", studentLoginDelegate.getStudentName(Long.parseLong(registerNumber)));
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/JSP/student_portal.jsp");
					requestDispatcher.forward(request, response);
				} else {
					// goto student invalid page.
					session.removeAttribute("id");
					session.removeAttribute("registerNumber");
					session.removeAttribute("password");
					session.invalidate();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/JSP/invalid_student.jsp");
					requestDispatcher.forward(request, response);
				}
			}
			else if (typeOfLogin.equals("FacultyLogin")) {
				if (session.getAttribute("facultyId") == null) {
					if (request.getParameter("faculty-id") == null) {
						response.sendRedirect("index.html");
						return;
					}
					facultyId = request.getParameter("faculty-id");
					password = request.getParameter("faculty-password");
					session.setAttribute("facultyId", facultyId);
					session.setAttribute("password", password);
				} else {
					facultyId = (String) session.getAttribute("facultyId");
					password = (String) session.getAttribute("password");
				}
				StudentLoginDelegate studentLoginDelegate = new StudentLoginDelegate();
				if (studentLoginDelegate.facultyLoginCheck(Integer.parseInt(facultyId), password) == 1) {
					// goto student portal page
					request.setAttribute("faculty-id", facultyId);
					request.setAttribute("name", studentLoginDelegate.getFacultyName(Integer.parseInt(facultyId)));
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/JSP/faculty_portal.jsp");
					requestDispatcher.forward(request, response);
				} else {
					// goto faculty invalid page.
					session.removeAttribute("id");
					session.removeAttribute("facultyId");
					session.removeAttribute("password");
					session.invalidate();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/JSP/invalid_student.jsp");
					requestDispatcher.forward(request, response);
				}
			}else {
				response.sendRedirect("index.html");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
