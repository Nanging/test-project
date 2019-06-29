package com.sd.demo.web;

import java.sql.Timestamp;

public class ApplyForm {
	public Timestamp startTime;
	public int time;
	public long  placeid;
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
	public  long getPlaceid() {
		return placeid;
	}
	public void setPlaceid(int placeid) {
		this.placeid = placeid;
	}
	@Override
	public String toString() {
		return "ApplyForm [startTime=" + startTime + ", time=" + time + ", placeid=" + placeid + "]";
	}
	
}
