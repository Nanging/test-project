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

	public void initPara() {
		// TODO Auto-generated method stub
		userid = 1;
		oldPassword = "123";
		password = "456";
	}
	@Test
	public void testModify001() {
		initPara();
		oldPassword = "";
		assertTrue("密码为空情况测试失败", !userService.modify(userid, oldPassword, oldPassword));	
		initPara();
		password = "";
		assertTrue("新密码为空情况测试失败", !userService.modify(userid, oldPassword, oldPassword));	
	}
	@Test
	public void testModify002() {
		initPara();
		userid = 0;
		assertTrue("用户不存在情况测试失败", !userService.modify(userid, oldPassword, oldPassword));	
		initPara();
		oldPassword ="999";
		assertTrue("用户密码错误情况测试失败", !userService.modify(userid, oldPassword, oldPassword));	
	}
	@Test
	public void testModify004() {
		assertTrue("参数合法情况测试失败", userService.modify(userid, oldPassword, oldPassword));	
	}
}
