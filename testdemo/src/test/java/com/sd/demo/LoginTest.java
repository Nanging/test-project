package com.sd.demo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sd.demo.service.UserService;

public class LoginTest extends TestdemoApplicationTests{
	@Autowired
	private UserService userService;
	
	@Test
	public void testUserLogin() {
		//UT_LG_001_001_001 
		Assert.assertEquals(false, userService.userLogin(null, "11111111"));
		Assert.assertEquals(false, userService.userLogin("test", null));
		
		//UT_LG_001_001_002
		Assert.assertEquals(false, userService.userLogin("aaa", "99999999999"));
		
		//UT_LG_001_001_003
		Assert.assertEquals(false, userService.userLogin("test", "99999999999"));
	}
	
	@Test
	public void testUserRegister() {
		//UT_LG_001_002_001 
		Assert.assertEquals(400, userService.userRegister(null, "11111111", "12311111111").getCode());
		Assert.assertEquals(400, userService.userRegister("test", null, "12311111111").getCode());
		Assert.assertEquals(400, userService.userRegister("test", "11111111", null).getCode());
		
		//UT_LG_001_002_002
		Assert.assertEquals(400, userService.userRegister("helloworld", "11111111", "12311111111").getCode());
		
		//UT_LG_001_002_003
		Assert.assertEquals(400, userService.userRegister("usertest", "11111111", "123").getCode());
	}
}
