package com.zilker.mymodule.bean;

public class AttendanceDetails {
	int registerNumber, attendance, odTaken, totalClass;

	public int getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
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

	public AttendanceDetails() {
		super();
	}

	public AttendanceDetails(int registerNumber, int attendance, int odTaken, int totalClass) {
		super();
		this.registerNumber = registerNumber;
		this.attendance = attendance;
		this.odTaken = odTaken;
		this.totalClass = totalClass;
	}

}
