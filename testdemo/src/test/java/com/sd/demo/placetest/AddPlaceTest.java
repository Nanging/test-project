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
	

	public void initPara() {
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
	public void testAddPlaceTest001() {
		initPara();
		name = "";
		assertTrue("场地名称不能为空情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
		initPara();
		type = "";
		assertTrue("场地类型不能为空情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
		initPara();
		description = "";
		assertTrue("场地描述不能为空情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
		initPara();
		name = "";
		assertTrue("场地名称不能为空情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
		initPara();
		name = "";
		assertTrue("场地名称不能为空情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	@Test
	public void testAddPlaceTest002() {
		initPara();
		ownerid = -1;
		assertTrue("用户不存在情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	
	@Test
	public void testAddPlaceTest003() {
		initPara();
		size = 0;
		assertTrue("场地大小不合法情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
		initPara();
		affordNumber = 0;
		assertTrue("场地容纳人数不合法情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
		initPara();
		roomNumber = 0;
		assertTrue("场地房间数不合法情况测试失败", !placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
	@Test
	public void testAddPlaceTest004() {
		assertTrue("参数合法情况测试失败", placeService.addPlace(name, type, description, size, affordNumber, location, price, roomNumber, imageUrls, ownerid));
	}
}
