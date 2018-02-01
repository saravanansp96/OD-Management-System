package com.zilker.mymodule.bean;

public class StudentDetails {
	long registerNumber;
	String studentName, department;
	float cgpa;
	int noOfStandingArrear, year, facultyId, special;
	int attendance, odTaken, totalClass;

	public StudentDetails() {
		super();
	}

	public StudentDetails(long registerNumber, String studentName, String department, float cgpa,
			int noOfStandingArrear, int year, int facultyId, int special, int attendance, int odTaken, int totalClass) {
		super();
		this.registerNumber = registerNumber;
		this.studentName = studentName;
		this.department = department;
		this.cgpa = cgpa;
		this.noOfStandingArrear = noOfStandingArrear;
		this.year = year;
		this.facultyId = facultyId;
		this.special = special;
		this.attendance = attendance;
		this.odTaken = odTaken;
		this.totalClass = totalClass;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public int getOdTaken() {
		return odTaken;
	}

	public void setOdTaken(int odTaken) {
		this.odTaken = odTaken;
	}

	public int getTotalClass() {
		return totalClass;
	}

	public void setTotalClass(int totalClass) {
		this.totalClass = totalClass;
	}

	public long getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(long registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public float getCgpa() {
		return cgpa;
	}

	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}

	public int getNoOfStandingArrear() {
		return noOfStandingArrear;
	}

	public void setNoOfStandingArrear(int noOfStandingArrear) {
		this.noOfStandingArrear = noOfStandingArrear;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public int getSpecial() {
		return special;
	}

	public void setSpecial(int special) {
		this.special = special;
	}

}
