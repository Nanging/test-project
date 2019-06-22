package com.sd.demo.service;

import com.sd.demo.entity.SysUser;

public interface UserService {
	
	public SysUser  getUserByName(String username);

	SysUser addNewUser(String username, String password, String tel);
}
