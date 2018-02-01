package com.zilker.mymodule.bean;

public class FacultyDetails {

	int facultyId;
	String facultyName, Department;

	public FacultyDetails() {
		super();
	}

	public FacultyDetails(int facultyId, String facultyName, String department) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		Department = department;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}
}
