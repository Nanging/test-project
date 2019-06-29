package com.sd.demo.web;

public class RegisterForm {

	public String username;
	public String password;
	public String phonenumber;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	@Override
	public String toString() {
		return "ResisterForm [username=" + username + ", password=" + password + ", phonenumber=" + phonenumber + "]";
	}
	
}
