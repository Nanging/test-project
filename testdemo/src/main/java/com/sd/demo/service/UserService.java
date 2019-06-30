package com.sd.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sd.demo.entity.SysUser;
import com.sd.demo.web.ApplyItem;
import com.sd.demo.web.Result;

public interface UserService {
	
	public SysUser  getUserByName(String username);

	SysUser addNewUser(String username, String password, String tel);

	boolean userLogin(String username, String password);

	Result userRegister(String username, String password, String phonenumber);

	public void addUserSession(String string, String sessionId);

	SysUser getCurrentUser(HttpServletRequest request, HttpServletResponse response);

	SysUser modifyUser(SysUser user);

	SysUser modify(String password, HttpServletRequest request, HttpServletResponse response);

	List<ApplyItem> getUserApply(SysUser user);

	List<ApplyItem> getOtherUserApply(SysUser user);

	SysUser removeFavorite(SysUser user, int placeid);

	SysUser addFavorite(SysUser user, int placeid);
}
