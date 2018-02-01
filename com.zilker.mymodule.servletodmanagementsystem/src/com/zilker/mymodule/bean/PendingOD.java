package com.zilker.mymodule.bean;

import java.util.Date;

public class PendingOD extends OnDutyDetails {
	
	String student_name;

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public PendingOD(int odID, long registerNumber, String eventName, String status, Date dateOfEvent,
			String student_name) {
		super(odID, registerNumber, eventName, status, dateOfEvent);
		this.student_name = student_name;
	}

	public PendingOD() {
		super();
	}

	public String toString() {
		return (this.registerNumber + " \t " + this.student_name + " \t " + this.eventName + " \t " + this.dateOfEvent
				+ " \t " + this.status);
	}

}
