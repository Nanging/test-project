package com.sd.demo.userinfotest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sd.demo.TestdemoApplicationTests;
import com.sd.demo.service.UserService;

public class RemoveFavoriteTest extends TestdemoApplicationTests {
	@Autowired
	private UserService userService;
	
	@Test
	public void testRemoveFavorite001_001() {
		int userid = -1;
		int placeid = 1;
		assertTrue("用户不存在情况测试失败", !userService.removeFavorite(userid, placeid));	
	}
	
	@Test
	public void testRemoveFavorite002_001() {
		int userid = 1;
		int placeid = -1;
		assertTrue("场地不存在情况测试失败", !userService.removeFavorite(userid, placeid));	
	}
	@Test
	public void testRemoveFavorite003_001() {
		int userid = 1;
		int placeid = 1;
		assertTrue("参数合法情况测试失败", userService.removeFavorite(userid, placeid));	
	}
}
