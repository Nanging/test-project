package com.sd.demo.placetest;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.demo.TestdemoApplicationTests;
import com.sd.demo.entity.Place;
import com.sd.demo.service.PlaceService;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class AddPlaceTest extends TestdemoApplicationTests{

	@Autowired
	private PlaceService placeService;
	
	String name = "场地名";
	String type = "";
	String description = "测试用场地";
	int	size = 50;
	int	affordNumber = 5;
	String location = "嘉定";
	int price = 100;
	int roomNumber = 1;
	Set<String> imageUrls = new HashSet<String>(){{
		add("http://localhost:8080/image/1.png");
	}};
	int ownerid = 1;
	
	@Before
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		name = "场地名";
		type = "";
		description = "测试用场地";
		size = 50;
		affordNumber = 5;
		location = "嘉定";
		price = 100;
		roomNumber = 1;
		imageUrls = new HashSet<String>(){{
			add("http://localhost:8080/image/1.png");
		}};
		ownerid = 1;
	}
	
	@Test
	public void testAddPlaceTest001_001() {
		name = "";
		assertTrue("场地名称不能为空情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	@Test
	public void testAddPlaceTest001_002() {
		type = "";
		assertTrue("场地类型不能为空情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	@Test
	public void testAddPlaceTest001_003() {
		description = "";
		assertTrue("场地描述不能为空情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	@Test
	public void testAddPlaceTest001_004() {
		name = "";
		assertTrue("场地名称不能为空情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	@Test
	public void testAddPlaceTest001_005() {
		name = "";
		assertTrue("场地名称不能为空情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	@Test
	public void testAddPlaceTest002_001() {
		ownerid = -1;
		assertTrue("用户不存在情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	
	@Test
	public void testAddPlaceTest003_001() {
		size = 0;
		assertTrue("场地大小不合法情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	@Test
	public void testAddPlaceTest003_002() {
		affordNumber = 0;
		assertTrue("场地容纳人数不合法情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	@Test
	public void testAddPlaceTest003_003() {
		roomNumber = 0;
		assertTrue("场地房间数不合法情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	@Test
	public void testAddPlaceTest004_001() {
		assertTrue("参数合法情况测试失败", placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
}
