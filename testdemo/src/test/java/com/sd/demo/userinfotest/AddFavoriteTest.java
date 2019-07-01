package com.sd.demo.userinfotest;
import com.sd.demo.TestdemoApplicationTests;
import com.sd.demo.service.UserService;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
public class AddFavoriteTest extends TestdemoApplicationTests {
	@Autowired
	private UserService userService;
	
	@Test
	public void testAddFavorite001() {
		int userid = -1;
		int placeid = 1;
		assertTrue("用户不存在情况测试失败", !userService.addFavorite(userid, placeid));	
	}
	
	@Test
	public void testAddFavorite002() {
		int userid = 1;
		int placeid = -1;
		assertTrue("场地不存在情况测试失败", !userService.addFavorite(userid, placeid));	
	}
	@Test
	public void testAddFavorite003() {
		int userid = 1;
		int placeid = 1;
		assertTrue("参数合法情况测试失败", userService.addFavorite(userid, placeid));	
	}
}
