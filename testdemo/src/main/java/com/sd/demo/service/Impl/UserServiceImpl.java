package com.sd.demo.service.Impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sd.demo.dao.SysRoleDao;
import com.sd.demo.dao.SysUserDao;
import com.sd.demo.entity.SysPermission;
import com.sd.demo.entity.SysRole;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SysRoleDao roleDao;
	
	public SysUser modifyUser(SysUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.saveAndFlush(user);
		return userDao.getOne(user.getId());
	}
	
	@Override
	public SysUser addNewUser(String username,String password,String tel) {
		SysUser newUser = new SysUser();
		newUser.setUsername(username);
		newUser.setPassword(passwordEncoder.encode(password));
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

}
