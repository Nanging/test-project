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
	@Before
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		name = "test";
		type = "住房";
		page = 1;
	}
	@Test
	public void testGetPlaceList001_001() {
		page = 0;
		assertTrue("页数不合法情况测试失败", null==placeService.getPlaceList(name, type, page));
	}
	
	@Test
	public void testGetPlaceList002_001() {
		name = null;
		assertTrue("名称为空情况测试失败", null==placeService.getPlaceList(name, type, page));
	}
	
	@Test
	public void testGetPlaceList002_002() {
		type = null;
		assertTrue("类型为空情况测试失败", null==placeService.getPlaceList(name, type, page));
	}
	
	@Test
	public void testGetPlaceList003_001() {
		assertNotNull("参数合法情况测试失败", placeService.getPlaceList(name, type, page));
	}
}
