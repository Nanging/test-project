package com.sd.demo.web;

import java.sql.Timestamp;

public class CommentItem {
	public int id;
	public String content;
	public Timestamp time;
	public String username;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "CommentItem [id=" + id + ", content=" + content + ", time=" + time + ", username=" + username + "]";
	}
	
}
