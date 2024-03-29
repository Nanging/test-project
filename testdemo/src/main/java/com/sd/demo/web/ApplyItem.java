package com.sd.demo.web;

import java.sql.Timestamp;

public class ApplyItem {
	public int id;
	public String placename;
	public int placeid;
	public String applier;
	public String state;
	public Timestamp startTime;
	public int time;
	
	public int getPlaceid() {
		return placeid;
	}
	public void setPlaceid(int placeid) {
		this.placeid = placeid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
	}
	public String getApplier() {
		return applier;
	}
	public void setApplier(String applier) {
		this.applier = applier;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "ApplyItem [placename=" + placename + ", applier=" + applier + ", state=" + state + ", startTime="
				+ startTime + ", time=" + time + "]";
	}
	
}
