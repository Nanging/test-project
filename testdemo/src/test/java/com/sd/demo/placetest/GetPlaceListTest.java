package com.sd.demo.placetest;
import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sd.demo.TestdemoApplicationTests;
import com.sd.demo.service.PlaceService;
public class GetPlaceListTest extends TestdemoApplicationTests{
	
	@Autowired
	private PlaceService placeService;
	
	String name = "test";
	String type = "住房";
	int page = 1;

	public void initPara() {
		name = "test";
		type = "住房";
		page = 1;
	}
	@Test
	public void testGetPlaceList001() {
		initPara();
		page = 0;
		assertTrue("页数不合法情况测试失败", null==placeService.getPlaceList(name, type, page));
	}
	
	@Test
	public void testGetPlaceList002() {
		initPara();
		name = null;
		assertTrue("名称为空情况测试失败", null==placeService.getPlaceList(name, type, page));
		initPara();
		type = null;
		assertTrue("类型为空情况测试失败", null==placeService.getPlaceList(name, type, page));
	}
	
	@Test
	public void testGetPlaceList003() {
		assertNotNull("参数合法情况测试失败", placeService.getPlaceList(name, type, page));
	}
}
