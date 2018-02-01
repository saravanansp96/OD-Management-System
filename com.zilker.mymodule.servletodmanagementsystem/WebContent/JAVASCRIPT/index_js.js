/**
 * 
 */
var mainContainer = document.getElementById('main-container');
var containerLogin = document.getElementById('container-login');
var containerStudentLogin = document.getElementById('container-student-login');
var containerFacultyLogin = document.getElementById('container-faculty-login');
var studentBackBtn = document.getElementById('student-back-btn');
var facultyBackBtn = document.getElementById('faculty-back-btn');

function gotoStudentLogin() {
	/*if(	!containerStudentLogin.classList.contains('animation')){
		containerStudentLogin.classList.add('animation');
		containerStudentLogin.classList.add('fadeInRight');
	}*/
	containerLogin.classList.add('display-none');
	containerStudentLogin.classList.remove('display-none');
}

function gotoFacultyLogin() {
	/*if(!containerFacultyLogin.classList.contains('animation')){
		containerFacultyLogin.classList.add('animation');
		containerFacultyLogin.classList.add('fadeInLeft');
	}*/
	containerLogin.classList.add('display-none');
	containerFacultyLogin.classList.remove('display-none');
}

function gotoMainContainer() {
	/*if(!containerLogin.classList.contains('animation')){
		containerLogin.classList.add('animation');
		containerLogin.classList.add('fade');
	}*/
	containerLogin.classList.remove('display-none');
}

function onLoginClick (event) {
	var target=event.target;
	if(target.id == 'student-login-button')
		gotoStudentLogin();
	if(target.id == 'faculty-login-button')
		gotoFacultyLogin();
}

mainContainer.addEventListener('click', onLoginClick);
studentBackBtn.addEventListener('click',function() {
	containerStudentLogin.classList.add('display-none');
	gotoMainContainer();
});

facultyBackBtn.addEventListener('click',function() {
	containerFacultyLogin.classList.add('display-none');
	gotoMainContainer();
});

