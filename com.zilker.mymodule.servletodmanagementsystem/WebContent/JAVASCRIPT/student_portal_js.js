/**
 * 
 */
var navBar = document.getElementById('nav-bar');
var welcomeTab = document.getElementById('welcome-container');
var viewDetails = document.getElementById('view-details-container');
var applyOd = document.getElementById('apply-od');
var viewStatus = document.getElementById('view-status');
var navViewDetails = document.getElementById('view-Details-Nav');
var navApplyOd = document.getElementById('apply-Od-Nav');
var navViewStatus = document.getElementById('view-Od-Details-Nav');
var modal = document.getElementById('myModal');
var cancelModal = document.getElementById('close-modal');
var flagDateOfEvent = 0;
var flagStatusOfOd = 0;
var studentDetails;
var odDetails;
var tableContent;

window.onclick = function(event) {
    if (event.target == modal) {
    	//modal.style.display = "none";
    	modal.classList.remove('display-block');
    	modal.classList.add('display-none');
    }
}

function closeModal() {
	modal.classList.remove('display-block');
	modal.classList.add('display-none');
	//modal.style.display = "none";
}

function onNavBarClick (event) {
	var target = event.target;
	//console.log(target.innerHTML);
	if(target.innerHTML == "View Details"){
		//console.log("1");
		if(!welcomeTab.classList.contains('display-none')){
			welcomeTab.classList.add('display-none');
		}
		if(viewDetails.classList.contains('display-none')){
			viewDetails.classList.remove('display-none');
		}
		if(!applyOd.classList.contains('display-none')){
			applyOd.classList.add('display-none');
		}
		if(!viewStatus.classList.contains('display-none')){
			viewStatus.classList.add('display-none');
		}
		navViewDetails.classList.add('menu-select');
		navApplyOd.classList.remove('menu-select');
		navViewStatus.classList.remove('menu-select');
	}
	else if(target.innerHTML == "Apply OD") {
		//console.log("2");
		if(!welcomeTab.classList.contains('display-none')){
			welcomeTab.classList.add('display-none');
		}
		if(applyOd.classList.contains('display-none')) {
			applyOd.classList.remove('display-none');
		}
		if(!viewDetails.classList.contains('display-none')){
			viewDetails.classList.add('display-none');
		}
		if(!viewStatus.classList.contains('display-none')){
			viewStatus.classList.add('display-none');
		}
		navApplyOd.classList.add('menu-select');
		navViewDetails.classList.remove('menu-select');
		navViewStatus.classList.remove('menu-select');
	}
	else if(target.innerHTML == "Status of OD") {
		//console.log("3");
		if(!welcomeTab.classList.contains('display-none')){
			welcomeTab.classList.add('display-none');
		}
		if(viewStatus.classList.contains('display-none')) {
			viewStatus.classList.remove('display-none');
		}
		if(!viewDetails.classList.contains('display-none')){
			viewDetails.classList.add('display-none');
		}
		if(!applyOd.classList.contains('display-none')){
			applyOd.classList.add('display-none');
		}
		navViewStatus.classList.add('menu-select');
		navApplyOd.classList.remove('menu-select');
		navViewDetails.classList.remove('menu-select');
	}	
}

function addStudentDetailsToHtml (studentDetails) {
	 tableContent = "<table class=\"student-details-table\"><tr><th>REGISTRATION NUMBER: </th><td>"+studentDetails.RegisterNumber+"</td></tr><tr><th>NAME: </th><td>"+studentDetails.Name+"</td></tr><tr><th>YEAR: </th><td>"+studentDetails.Year+"</td></tr><tr><th>DEPARTMENT: </th><td>"+studentDetails.Department+"</td></tr><tr><th>CGPA: </th><td>"+studentDetails.CGPA+"</td></tr><tr><th>ODS TAKEN: </th><td>"+studentDetails.OdTaken+"</td></tr><tr><th>ATTENDANCE: </th><td>"+studentDetails.Attendance+"</td></tr></table>";
	 document.getElementById('details-of-student').innerHTML = tableContent;
}

function addOdDetailsToHtml (odDetails) {
	if(odDetails != 0) {
	tableContent = "<table class=\"od-details-table\"><tr><th>EVENT NAME</th><th class = \"hover-pointer\" onclick = \"sortByDate()\">DATE OF EVENT  <i class=\"fa fa-sort\" aria-hidden=\"true\"></i></th><th class = \"hover-pointer\" onclick = \"sortByStatus()\">STATUS OF OD  <i class=\"fa fa-sort\" aria-hidden=\"true\"></i></th></tr>";
	for(var i=0; i<odDetails.length ; i++) {
		var jsonObject = odDetails[i];
		if(jsonObject.status == "pending"){
			className = "pending-color";
		}
		else if (jsonObject.status == "approved") {
			className = "approved-color";
		}
		else {
			className = "rejected-color";
		}
		tableContent = tableContent + "<tr><td>"+jsonObject.eventName+"</td><td>"+jsonObject.dateOfEvent+"</td><td class=\""+className+"\">"+jsonObject.status+"</td></tr>";
	}
	tableContent = tableContent + "</table>";
	}
	else {
		tableContent = "You haven't applied any OD yet!";
	}
	document.getElementById('od-details-of-student').innerHTML = tableContent;
}

function getOdDetails(registerNumber) {
	var xhttp =new XMLHttpRequest();
	var url = "ViewOdFactory?type=StudentViewOd&registerNumber="+registerNumber;
	console.log(url);
    xhttp .onreadystatechange=function() {
        if(this.readyState == 4 && this.status == 200) {
        	console.log(this.responseText);
        	odDetails = JSON.parse(this.responseText);
        	addOdDetailsToHtml(odDetails);
        }
    };
    xhttp.open("POST", url , true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send();
}

function ifDateInRange(eventDate) {
	var today = new Date();
	var todayInMilliSeconds = today.getTime();
	eventDate = new Date(eventDate);
	var eventDateInMilliSeconds = eventDate.getTime();
	var differenceInDays = (eventDateInMilliSeconds - todayInMilliSeconds)/(1000*60*60*24);
	console.log(differenceInDays);
	if(differenceInDays > 31) {
		return 0;
	}
	return 1;
}

function isSundayCheck(eventDate) {
	eventDate = new Date(eventDate);
	var dayOfWeek =  eventDate.getDay();
	if(dayOfWeek == 0) {
		return 1;
	}
	return 0;
}

function applyTheOd (registerNumber) {
	var eventName = document.getElementById('input-event-name').value;
	var eventDate = document.getElementById('inpt-event-date').value;
	if (ifDateInRange(eventDate) == 1 && isSundayCheck(eventDate) == 0){
	var xhttp =new XMLHttpRequest();
	var url = "StudentPortalServlet?id=applyOd&registerNumber="+registerNumber+"&eventName="+eventName+"&eventDate="+eventDate;
    xhttp .onreadystatechange=function() {
        if(this.readyState == 4 && this.status == 200) {
        	//alert(this.responseText);
        	//modal.style.display = "block";
        	document.getElementById('modal-contents').innerHTML = this.responseText;
        	modal.classList.remove('display-none');
        	modal.classList.add('display-block');
        	document.getElementById('input-event-name').value = "";
        	document.getElementById('inpt-event-date').value = "";
        }
    };
        xhttp.open("POST", url , true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send();
	} else {
		//alert("You can't apply OD for that date "+eventDate);
		document.getElementById('modal-contents').innerHTML = "You can't apply OD for that date "+eventDate; 
    	modal.classList.remove('display-none');
    	modal.classList.add('display-block');
	}
}

function getStudentDetails(registerNumber) {
	var xhttp =new XMLHttpRequest();
	var url = "StudentPortalServlet?id=viewDetails&registerNumber="+registerNumber;
	console.log(url);
    xhttp .onreadystatechange=function() {
        if(this.readyState == 4 && this.status == 200) {
        	console.log(this.responseText);
        	studentDetails = JSON.parse(this.responseText);
        	console.log(studentDetails.RegisterNumber);
        	addStudentDetailsToHtml(studentDetails);
        }
    };
        xhttp.open("POST", url , true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send();
}

function openNavBar() {
    document.getElementById("nav-bar-list").classList.toggle("responsive");
}

function sortByStatus() {
	console.log("inside the sortByStatus "+flagStatusOfOd);
	odDetails.sort(function(a, b){
		if(a.status > b.status) {
			return 1;
		}
		else if (a.statusOfOd < b.statusOfOd) {
			return -1;
		}
		else {
			return 0;
		} 
	});
	if(flagStatusOfOd == 1){
		addOdDetailsToHtml(odDetails);
		flagStatusOfOd = 0;
	}else {
		addOdDetailsToHtml(odDetails.reverse());
		flagStatusOfOd = 1;
	}
}

function sortByDate() {
	console.log("inside the sortByDate "+flagDateOfEvent);
	odDetails.sort(function(a, b){
		if(a.dateOfEvent > b.dateOfEvent) {
			return 1;
		}
		else if (a.dateOfEvent < b.dateOfEvent) {
			return -1;
		}
		else {
			return 0;
		} 
	});
	if(flagDateOfEvent == 1){
		addOdDetailsToHtml(odDetails.reverse());
		flagDateOfEvent = 0;
	}else {
		addOdDetailsToHtml(odDetails);
		flagDateOfEvent = 1;
	}
}

navBar.addEventListener('click', onNavBarClick , false);
cancelModal.addEventListener('click',closeModal);