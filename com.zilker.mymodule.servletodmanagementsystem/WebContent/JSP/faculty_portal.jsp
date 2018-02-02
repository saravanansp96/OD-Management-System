<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String facultyId = (String) request.getAttribute("faculty-id");
%>
<%
	String name = (String) request.getAttribute("name");
%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	if (session.getAttribute("id") == null) {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
		requestDispatcher.forward(request, response);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="./CSS/faculty_portal.css">
<title>Faculty portal</title>
</head>
<body>
	<ul id="nav-bar" class="nav-bar-list">
		<li class="nav-bar-element mobile-nav-icon" onclick="openNavBar()">&#9776;
			<div class="mobile-username">
				Hi
				<%=name%></div>
		</li>
		<li class="nav-bar-element animation fadeInDown"><div
				onclick="totalOdList(<%=facultyId%>)">OD LIST</div>
		</li>
		<li class="nav-bar-element animation fadeInDown"><div
				onclick="odPendingStudents(<%=facultyId%>)">Approve OD</div></li>
		<li class="nav-bar-element  animation fadeInDown dropdown"><div>
				Hi
				<%=name%>!
			</div>
			<form method="post" action="FacultyPotalServlet?id=logout"
				class="logoutbutton">
				<input class="blue-button-logout" type="submit" value="Log out">
			</form></li>
		<li class="nav-bar-element mob-display animation fadeInDown">
			<form method="post" action="FacultyPotalServlet?id=logout">
				<input class="blue-button-logout" type="submit" value="Log out">
			</form>
		</li>
	</ul>
	<div class="container">
		<div id="welcome-container" class="animation fadeIn">
			<h3 class="name-h3 content-center">
				Hi
				<%=name%>!<br>
				<br>
			</h3>
			<h3 class="content-center">welcome to Faculty portal,</h3>
			<p class="content-center">Lorem ipsum dolor sit amet,
				consectetur adipiscing elit. Nam quis dolor quis sapien rutrum
				pulvinar aliquet at tellus. Cras facilisis ligula augue, et laoreet
				ipsum ultricies a. Sed egestas consectetur ipsum. Proin leo mauris,
				ultrices non tincidunt ac, iaculis vel leo. Aliquam erat volutpat.
				Integer vulputate malesuada arcu, eget porta velit sagittis in.
				Curabitur nec nulla vehicula, iaculis ex a, euismod sapien. Etiam
				ligula purus, condimentum quis ullamcorper consectetur, auctor at
				tellus. Duis sapien erat, ornare in arcu id, laoreet luctus metus.
				Duis sit amet sodales risus. Aliquam lectus ligula, molestie rutrum
				eleifend sit amet, aliquam at est. Curabitur rhoncus congue augue
				consectetur commodo. Phasellus gravida libero eget enim laoreet, sit
				amet feugiat ligula malesuada.</p>
		</div>
		<div id="container-approve-od" class="display-none animation fadeIn">
			<h3 class="content-center">Approve Od</h3>
			<div id="approval-od-list"></div>
			<p class="content-center">*click on student register number (or)
				name to see the student details</p>
		</div>
		<div id="container-od-approved" class="display-none animation fadeIn">
			<h3 class="content-center">OD LIST</h3>
			<div class = "content-center" id = "filter-od-details"><span><input name = "filter" class = "filter-check-box" type = "checkbox" value = "pending" checked>Pending</span>
	<span><input name = "filter" class = "filter-check-box" type = "checkbox" value = "rejected" checked>Rejected</span>
	<span><input name = "filter" class = "filter-check-box" type = "checkbox" value = "approved" checked>Approved</span></div> 
			<div id="od-approved-list"></div>
		</div>
	</div>
	<div id="myModal" class="modal animation fadeIn display-none">
		<div class="modal-content">
			<span id="close-modal">&times;</span>
			<div id="modal-contents"></div>
		</div>
	</div>
	<script src="./JAVASCRIPT/faculty_portal.js"></script>
</body>
</html>