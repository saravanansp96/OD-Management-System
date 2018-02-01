<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%  String registerNumber = (String)request.getAttribute("register-number"); 
	String name = (String)request.getAttribute("name");
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	if(session.getAttribute("id")==null){
		 RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
    	 requestDispatcher.forward(request, response);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel = "stylesheet" type = "text/css" href = "./CSS/student_portal_css.css" >
<title>Student Portal</title>
</head>
<body>

<div class = "nav-bar" id = "nav-bar">
<ul class = "nav-bar-list" id = "nav-bar-list">
<li class = "nav-bar-element mobile-nav-icon" onclick="openNavBar()">&#9776;<div class = "mobile-username">Hi <%= name %></div></li>
<li id = "view-Details-Nav" class = "nav-bar-element"><div onclick = "getStudentDetails('<%= registerNumber%>')">View Details</div></li>
<li id = "apply-Od-Nav" class = "nav-bar-element"><div>Apply OD</div></li>
<li id = "view-Od-Details-Nav" class = "nav-bar-element"><div onclick = "getOdDetails('<%= registerNumber %>')" >Status of OD</div></li>
<li class = "nav-bar-element dropdown"><div>Hi <%= name %></div>
<form method = "post" action = "StudentPortalServlet?id=logout" class = "logoutbutton"><input class = "blue-button-logout" type = "submit" value ="Log out" ></form>
</li>
<li class = "nav-bar-element mob-display"><form method = "post" action = "StudentPortalServlet?id=logout"><input class = "blue-button-logout" type = "submit" value ="Log out" ></form></li>
</ul>
</div>
<div class = "container">
<div id = "welcome-container" class="animation fadeIn">
<h3 class = "name-h3 content-center">Hi  <%=name %>!<br><br></h3><h3 class = "content-center">  welcome to Student portal,</h3>
<p class = "content-center" >Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam quis dolor quis sapien rutrum pulvinar aliquet at tellus. Cras facilisis ligula augue, et laoreet ipsum ultricies a. Sed egestas consectetur ipsum. Proin leo mauris, ultrices non tincidunt ac, iaculis vel leo. Aliquam erat volutpat. Integer vulputate malesuada arcu, eget porta velit sagittis in. Curabitur nec nulla vehicula, iaculis ex a, euismod sapien. Etiam ligula purus, condimentum quis ullamcorper consectetur, auctor at tellus. Duis sapien erat, ornare in arcu id, laoreet luctus metus. Duis sit amet sodales risus. Aliquam lectus ligula, molestie rutrum eleifend sit amet, aliquam at est. Curabitur rhoncus congue augue consectetur commodo. Phasellus gravida libero eget enim laoreet, sit amet feugiat ligula malesuada.</p> 
</div>
<div id = "view-details-container" class = "container-view-details display-none animation fadeIn">
<h3 class = "name-h3 content-center">Student Details</h3>
<div id = "details-of-student">
</div>
</div>
<div class = "container-apply-od display-none animation fadeIn" id = "apply-od">
<h3 class = "name-h3 content-center">OD Application Form</h3>
<div class="form-apply-od">
<label>Event Name: </label><br>
<input type = "text" name = "event-name" id = "input-event-name" required><br>
<label>Date Of Event</label><br>
<input type = "date" name = "event-date" id = "inpt-event-date" required><br>
<input class = "blue-button" type = "submit" value = "APPLY OD"  onclick = "applyTheOd('<%=registerNumber%>')">
</div>
</div>
 
<div class = "container-view-status display-none animation fadeIn" id = "view-status">
<h3 class = "name-h3 content-center">Status of all OD's</h3>
<div id = "od-details-of-student">
</div>
</div>
</div>
<div id="myModal" class="modal animation fadeIn display-none">
		<div class="modal-content">
			<span id="close-modal">&times;</span>
			<div id="modal-contents"></div>
		</div>
	</div>
	
<script src="./JAVASCRIPT/student_portal_js.js"></script>
</body>
</html>