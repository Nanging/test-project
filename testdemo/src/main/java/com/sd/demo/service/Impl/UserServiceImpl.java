package com.sd.demo.service.Impl;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sd.demo.dao.SysRoleDao;
import com.sd.demo.dao.SysUserDao;
import com.sd.demo.entity.SysRole;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.UserService;
import com.sd.demo.web.Result;
import com.sd.demo.web.ResultFactory;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserDao userDao;
	
	@Autowired
	private SysRoleDao roleDao;
	
	@Autowired
	UserRedisService userRedisService;

	@Override
	public void addUserSession(String username, String sessionId) {
		userRedisService.addUserSession(username, sessionId);
	}
	@Override
	public SysUser modifyUser(SysUser user) {
		user.setPassword(user.getPassword());
		userDao.saveAndFlush(user);
		return userDao.getOne(user.getId());
	}
	
	@Override
	public SysUser addNewUser(String username,String password,String tel) {
		SysUser newUser = new SysUser();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setPhonenumber(tel);
		SysRole userRole = roleDao.findByCode("ROLE_USER");
		newUser.addRole(userRole);
		userDao.saveAndFlush(newUser);
		System.out.println(newUser.getId());
		SysRole newRole = new SysRole();
		newRole.setCode("USER_"+newUser.getId());
		newRole.setName("用户"+newUser.getId());
		newUser.addRole(newRole);
		userDao.saveAndFlush(newUser);
		return userDao.findByUsername(username);
	}
    
	@Cacheable(cacheNames = "authority",key = "#username")
	@Override
	public SysUser getUserByName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}
	@Override
	public boolean userLogin(String username,String password) {
		SysUser user = getUserByName(username);
		if (user == null) {
			return false;
		}
		System.out.println(user);
		if (user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	@Override
	public Result userRegister(String username,String password,String phonenumber) {
		SysUser user = getUserByName(username);
		if (user != null) {
			return ResultFactory.buildFailResult("user exists");
		}
		SysUser newUser = addNewUser(username, password, phonenumber);
		if (newUser != null) {
			return ResultFactory.buildSuccessResult("success");
		}
		return ResultFactory.buildFailResult("error");
	}
	@Override
	public SysUser getCurrentUser(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginStatus")) {
					if (null != cookie.getValue() && !"".equals(cookie.getValue())) {
						String[] token = cookie.getValue().split("_");
						String username = token[0];
						SysUser user = this.getUserByName(username);
						String password = token[1];
						if (!password.equals(user.getPassword())) {
							Cookie loginStatus = new Cookie("loginStatus", null);
							loginStatus.setPath("/");
							loginStatus.setMaxAge(0);
							response.addCookie(loginStatus);
							break;
						}
						HttpSession session = request.getSession();
						String sessionId = session.getId();
						String currentSessionID = userRedisService.getUserSession(cookie.getValue());
						System.out.println("[currentSessionID:" + currentSessionID + "]");
						System.out.println("[sessionId:" + sessionId + "]");
						if (sessionId.equals(currentSessionID)) {
							return user;
						} else {
							return null;
						}
					}
				}
			}
		}
		return null;
	}
	
}
