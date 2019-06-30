package com.sd.demo.web;

public class ModifyForm {
	public String oldPassword;
	public String password;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ModifyForm [oldPassword=" + oldPassword + ", password=" + password + "]";
	}


}
