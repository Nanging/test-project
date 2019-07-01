package com.sd.demo.userinfotest;

import com.sd.demo.TestdemoApplicationTests;
import com.sd.demo.service.UserService;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
public class ModifyTest extends TestdemoApplicationTests{

	@Autowired
	private UserService userService;
	
	int userid = 1;
	String oldPassword = "123";
	String password = "456";
	@Before
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		userid = 1;
		oldPassword = "123";
		password = "456";
	}
	@Test
	public void testModify001_001() {
		oldPassword = "";
		assertTrue("密码为空情况测试失败", !userService.modify(userid, oldPassword, oldPassword));	
	}
	@Test
	public void testModify001_002() {
		password = "";
		assertTrue("新密码为空情况测试失败", !userService.modify(userid, oldPassword, oldPassword));	
	}
	@Test
	public void testModify002_001() {
		userid = 0;
		assertTrue("用户不存在情况测试失败", !userService.modify(userid, oldPassword, oldPassword));	
	}
	@Test
	public void testModify003_001() {
		oldPassword ="999";
		assertTrue("用户密码错误情况测试失败", !userService.modify(userid, oldPassword, oldPassword));	
	}
	@Test
	public void testModify004_001() {
		assertTrue("参数合法情况测试失败", userService.modify(userid, oldPassword, oldPassword));	
	}
}
