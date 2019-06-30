package com.sd.demo.web;

public class AddCommentForm {
	public int placeid;
	public String comment;
	public int getPlaceid() {
		return placeid;
	}
	public void setPlaceid(int placeid) {
		this.placeid = placeid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "AddCommentForm [placeid=" + placeid + ", comment=" + comment + "]";
	}
	
}
