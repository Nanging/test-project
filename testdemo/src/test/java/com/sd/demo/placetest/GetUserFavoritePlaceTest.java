package com.sd.demo.placetest;

import com.sd.demo.TestdemoApplicationTests;
import com.sd.demo.service.PlaceService;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
public class GetUserFavoritePlaceTest extends TestdemoApplicationTests{

	@Autowired
	private PlaceService placeService;
	@Test
	public void testGetUserFavoritePlace001() {
		int userid = -1;
		assertTrue("用户不存在情况测试失败", null == placeService.getUserFavoritePlace(userid));
	}
	@Test
	public void testGetUserFavoritePlaces002() {
		int userid = 1;
		assertNotNull("用户存在情况测试失败", placeService.getUserFavoritePlace(userid));
	}
	
}
