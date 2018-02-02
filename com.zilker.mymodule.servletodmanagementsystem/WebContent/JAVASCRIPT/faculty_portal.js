/**
 * 
 */
var facultyCopyId;
var navBar = document.getElementById('nav-bar');
var fragment = document.createDocumentFragment();
var approvalSection = document.getElementById('container-approve-od');
var approvedStudentSection = document.getElementById('container-od-approved');
var odApprovalContentsSection = document.getElementById('approval-od-list');
var welcomeTab = document.getElementById('welcome-container');
var modal = document.getElementById('myModal');
var cancelModal = document.getElementById('close-modal');
var filterOdList = document.getElementById('filter-od-details');
var displayFilter= new Array();
var flagDateOfEvent = 0;
var studentDetails;


function totalOdList (facultyId) {
	facultyCopyId = facultyId;
	var xhttp =new XMLHttpRequest();
	var url = "ViewOdFactory?type=FacultyViewOd&faculty-id="+facultyId;
	console.log(url);
    xhttp .onreadystatechange=function() {
        if(this.readyState == 4 && this.status == 200) {
        	//console.log(this.responseText);
        	studentDetails = JSON.parse(this.responseText);
        	addStudentDetailsToHtml();
        }
    };
        xhttp.open("POST", url , true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send();
}

function odPendingStudents (facultyId) {
	facultyCopyId = facultyId;
	var xhttp =new XMLHttpRequest();
	facultyCopyId = facultyId;
	var url = "FacultyPotalServlet?id=odPendingStudents&faculty-id="+facultyId;
	console.log(url);
    xhttp .onreadystatechange=function() {
        if(this.readyState == 4 && this.status == 200) {
        	console.log(this.responseText);
        	studentDetails = JSON.parse(this.responseText);
        	console.log(studentDetails.RegisterNumber);
        	addDetailsToHtml(studentDetails);
        }
    };
        xhttp.open("POST", url , true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send();
}

function closeModal() {
	modal.classList.remove('display-block');
	modal.classList.add('display-none');
	//modal.style.display = "none";
}

function importStudentDetailsToHtml (studentDetailsObject) {
	 console.log("inside add students to html");
	 var tableContent = "<h3 class = \"center-align\" >Student Details</h3><table class=\"student-details-table\"><tr class = \"student-details-tr\"><th>REGISTRATION NUMBER: </th><td>"+studentDetailsObject.RegisterNumber+"</td></tr><tr class = \"student-details-tr\"><th>NAME: </th><td>"+studentDetailsObject.Name+"</td></tr><tr class = \"student-details-tr\"><th>YEAR: </th><td>"+studentDetailsObject.Year+"</td></tr><tr class = \"student-details-tr\"><th>DEPARTMENT: </th><td>"+studentDetailsObject.Department+"</td></tr><tr class = \"student-details-tr\"><th>CGPA: </th><td>"+studentDetailsObject.CGPA+"</td></tr><tr class = \"student-details-tr\"><th>ODS TAKEN: </th><td>"+studentDetailsObject.OdTaken+"</td></tr><tr class = \"student-details-tr\"><th>ATTENDANCE: </th><td>"+studentDetailsObject.Attendance+"</td></tr></table>";
	 console.log("table = "+tableContent);
	 document.getElementById('modal-contents').innerHTML = tableContent;
}

function getStudentDetails(registerNumber) {
	var xhttp =new XMLHttpRequest();
	var url = "StudentPortalServlet?id=viewDetails&registerNumber="+registerNumber;
	console.log(url);
    xhttp .onreadystatechange=function() {
        if(this.readyState == 4 && this.status == 200) {
        	console.log(this.responseText);
        	studentDetailsObject = JSON.parse(this.responseText);
        	console.log(studentDetails.RegisterNumber);
        	importStudentDetailsToHtml(studentDetailsObject);
        }
    };
        xhttp.open("POST", url , true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send();
}

function navFunction(event) {
	var target = event.target;
	if(target.innerHTML == 'OD LIST')
		{
			console.log("inside OD approved");
			if (approvedStudentSection.classList.contains('display-none')){
				approvedStudentSection.classList.remove('display-none');
			}
			if(!approvalSection.classList.contains('display-none')) {
				approvalSection.classList.add('display-none');
			}
			if(!welcomeTab.classList.contains('display-none')){
				welcomeTab.classList.add('display-none');
			}
		}
	if(target.innerHTML == 'Approve OD')
		{
		console.log("inside Approve OD");
		if (approvalSection.classList.contains('display-none')){
			approvalSection.classList.remove('display-none');
		}
		if(!approvedStudentSection.classList.contains('display-none')) {
			approvedStudentSection.classList.add('display-none');
		}
		if(!welcomeTab.classList.contains('display-none')){
			welcomeTab.classList.add('display-none');
		}
	}
}

function updateStatusInDB (registerNumber , dateOfOd , status) {
	var xhttp =new XMLHttpRequest();
	var url = "FacultyPotalServlet?id=odChangeStatus&register-number="+registerNumber+"&date-of-od="+dateOfOd+"&status-of-od="+status+"&faculty-id="+facultyCopyId;
	console.log(url);
    xhttp .onreadystatechange=function() {
        if(this.readyState == 4 && this.status == 200) {
        	console.log(this.responseText);
        	//modal.style.display = "block";
        	modal.classList.remove('display-none');
        	modal.classList.add('display-block');
        	document.getElementById('modal-contents').innerHTML = this.responseText;
        }
    };
        xhttp.open("POST", url , true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send();
}


function odApproveOrReject (event) {
	var target = event.target;
	var id = target.dataset.id;
	//var registerNumber = studentDetails[(id-1)].RegisterNumber;
	console.log(id);
	var tableRow = document.querySelector('tr[data-id="'+id+'"]');
	if(tableRow){
		//alert (id);
		var registerNumber = studentDetails[(id-1)].registerNumber;
		console.log(studentDetails[(id-1)].registerNumber);
		getStudentDetails(registerNumber);
		console.log("inside approve od or reject od "+registerNumber);
		if(target.innerHTML == "APPROVE"){
			var choice = window.confirm('Do you want to approve the od for '+studentDetails[(id-1)].student_name +' ?');
			if(choice == true) {
				tableRow.remove();
				updateStatusInDB(studentDetails[(id-1)].RegisterNumber , studentDetails[(id-1)].dateOfEvent, 'approved');
				return;
        	}
		}
		else if(target.innerHTML == "REJECT"){
			var choice = window.confirm('Do you want to reject the od for '+studentDetails[(id-1)].student_name +' ?');
			if(choice == true) {
				tableRow.remove();
				updateStatusInDB(studentDetails[(id-1)].RegisterNumber , studentDetails[(id-1)].dateOfEvent, 'rejected');
				return;
        	}
		}
		else {
			//modal.style.display = "block";
			modal.classList.remove('display-none');
			modal.classList.add('display-block');
		}
	}
}

window.onclick = function(event) {
    if (event.target == modal) {
    	//modal.style.display = "none";
    	modal.classList.remove('display-block');
    	modal.classList.add('display-none');
    }
}

function isChecked(type){
	var inputElements = document.getElementsByClassName('filter-check-box');
	for(var i=0; inputElements[i]; ++i){ 
		if(inputElements[i].checked && inputElements[i].value == type){
			return 1;
		}
	}
	return 0;
}

function changeOdList() {
	totalOdList(facultyCopyId);
	console.log("inside Event Listener");
	var inputElements = document.getElementsByClassName('filter-check-box');
	for(var i=0; studentDetails.length; ++i){
		if(studentDetails[i] == null){
			console.log("hello");
		}
	      if(isChecked(studentDetails[i].status)==1){
	    	 displayFilter[i]='display-table-row';
	      }
	      else {
	    	 displayFilter[i]='display-none';
	      }
	}
	addStudentDetailsToHtml();
}

//view od list
function addStudentDetailsToHtml() {
	var tableContents = "<table class=\"student-details-table\"><tr class = \"student-details-tr display-table-row\"><th>Register Number</th><th>Student Name</th><th>Event Description</th><th>Event Date</th><th>Status</th></tr>";
	for(var i =0 ; i < studentDetails.length ; i++ ) {
		if(studentDetails[i].status == "pending"){
			className = "pending-color";
		}
		else if (studentDetails[i].status == "approved") {
			className = "approved-color";
		}
		else {
			className = "rejected-color";
		}
		tableContents = tableContents + "<tr class = \"student-details-tr "+displayFilter[i]+"\"><td>"+studentDetails[i].registerNumber+"</td><td>"+studentDetails[i].student_name+"</td><td>"+studentDetails[i].eventName+"</td><td>"+studentDetails[i].dateOfEvent+"</td><td class = "+className+">"+studentDetails[i].status+"</td></tr>";
	}
	tableContents = tableContents + "</table>";
	document.getElementById('od-approved-list').innerHTML = tableContents;
}

function addDetailsToHtml (studentDetails) {
	
	var divContainer = createElement('div', '', '', '');
	var tableOdApproval = createElement('table', '', 'pending-list-table', '');
	var tableRow = createElement('tr', '', 'pending-list-table-tr', '');
	
	var tableheading = createElement('th', 'Register Number', 'pending-list-table-th', '');
	tableRow.appendChild ( tableheading );
	tableheading = createElement('th', 'Student Name', 'pending-list-table-th', '');
	tableRow.appendChild ( tableheading );
	tableheading = createElement('th', 'Event Name', 'pending-list-table-th', '');
	tableRow.appendChild ( tableheading );
	tableheading = createElement('th', 'Event Date', 'pending-list-table-th', '');
	tableRow.appendChild ( tableheading );
	tableheading = createElement('th', '', 'pending-list-table-th', '');
	tableRow.appendChild ( tableheading );
	tableheading = createElement('th', '', 'pending-list-table-th', '');
	tableRow.appendChild ( tableheading );
	
	tableOdApproval.appendChild ( tableRow );
	
	for(var i =0 ; i < studentDetails.length ; i++ ) {
		
		tableRow = createElement('tr', '', 'pending-list-table-tr', i+1);
		
		tableheading = createElement('td', studentDetails[i].registerNumber, 'pending-list-table-th', i+1);
		tableRow.appendChild ( tableheading );
		tableheading = createElement('td', studentDetails[i].student_name , 'pending-list-table-th', i+1);
		tableRow.appendChild ( tableheading );
		tableheading = createElement('td', studentDetails[i].eventName , 'pending-list-table-th', '');
		tableRow.appendChild ( tableheading );
		tableheading = createElement('td', studentDetails[i].dateOfEvent , 'pending-list-table-th', '');
		tableRow.appendChild ( tableheading );
		tableheading = createElement('th', '', 'pending-list-table-th', '');
		button = createElement('button', 'APPROVE', 'pending-list-table-th-approve', i+1);
		tableheading.appendChild ( button );
		tableRow.appendChild ( tableheading );
		tableheading = createElement('th', '', 'pending-list-table-th', '');
		button = createElement('button', 'REJECT', 'pending-list-table-th-reject', i+1);
		tableheading.appendChild ( button );
		tableRow.appendChild ( tableheading );
		
		tableOdApproval.appendChild ( tableRow );
	}
	divContainer.appendChild(tableOdApproval);
	document.getElementById('approval-od-list').innerHTML = divContainer.innerHTML;
}

function createElement(typeOfElement, innerHtml , classList , id) {
    var element = document.createElement(typeOfElement);
    if(innerHtml != '') {
    element.innerHTML = innerHtml;
    }
    if(classList != '') {
    element.classList.add(classList);
    }
    if(id != ''){
    element.dataset.id = id;
    }
    return element;
}

function sortByDate() {
	console.log("inside the sort function");
	studentDetails.sort(function(a, b){
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
	console.log(flagDateOfEvent);
	if(flagDateOfEvent == 1){
		addStudentDetailsToHtml(studentDetails);
		flagDateOfEvent = 0;
	}
	else {
		addStudentDetailsToHtml(studentDetails.reverse());
		flagDateOfEvent = 1;
	}
}


function openNavBar() {
    document.getElementById("nav-bar").classList.toggle("responsive");
}

navBar.addEventListener('click',navFunction);
odApprovalContentsSection.addEventListener('click',odApproveOrReject);
cancelModal.addEventListener('click',closeModal);
filterOdList.addEventListener('click',changeOdList);