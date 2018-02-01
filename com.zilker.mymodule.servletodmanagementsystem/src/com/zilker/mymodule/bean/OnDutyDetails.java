package com.zilker.mymodule.bean;

import java.util.Date;

public class OnDutyDetails {
	
	int odID;
	long registerNumber;
	String eventName, status;
	Date dateOfEvent;

	public OnDutyDetails(int odID, long registerNumber, String eventName, String status, Date dateOfEvent) {
		super();
		this.odID = odID;
		this.registerNumber = registerNumber;
		this.eventName = eventName;
		this.status = status;
		this.dateOfEvent = dateOfEvent;
	}

	public OnDutyDetails() {
		super();
	}

	public int getOdID() {
		return odID;
	}

	public void setOdID(int odID) {
		this.odID = odID;
	}

	public long getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(long registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public String toString() {
		return (this.eventName + " \t " + this.dateOfEvent + " \t " + this.status);
	}


}
