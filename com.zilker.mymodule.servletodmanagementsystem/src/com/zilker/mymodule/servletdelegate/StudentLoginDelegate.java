package com.zilker.mymodule.servletdelegate;

import com.zilker.mymodule.dao.ValidateFaculty;
import com.zilker.mymodule.dao.ValidateStudent;

public class StudentLoginDelegate {
	
	public int studentLoginCheck (long registerNumber , String password) {
		int flag=0;
		ValidateStudent validateStudent = new ValidateStudent();
		if((password.equals("123"))) {
			if( validateStudent.validateStudent(registerNumber)==1) {
				flag = 1;
			}
		}
		return flag;
	}
	
	public String getStudentName (long registerNumber) {
		ValidateStudent validateStudent = new ValidateStudent();
		return(validateStudent.getNameOfStudent(registerNumber));		
	}
	
	public int facultyLoginCheck (int facultyId , String password) {
		int flag=0;
		ValidateFaculty validateFaculty = new ValidateFaculty();
		if((password.equals("123"))) {
			if( validateFaculty.validateFaculty(facultyId)==1) {
				flag = 1;
			}
		}
		return flag;
	}
	
	public String getFacultyName (int facultyId) {
		ValidateFaculty validateFaculty = new ValidateFaculty();
		return (validateFaculty.getFacultyName(facultyId));
	}

}

