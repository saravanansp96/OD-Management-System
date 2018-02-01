package com.zilker.mymodule.bean;

public class ApprovalClass  extends StudentDetails {
	String eventName;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String toString() {
		return (this.registerNumber + "\t" + this.studentName + "\t" + this.eventName + "\t"
				+ String.format("%.2f", (((float) this.attendance / (float) this.totalClass) * 100)) + "\t"
				+ this.noOfStandingArrear);
	}
}
