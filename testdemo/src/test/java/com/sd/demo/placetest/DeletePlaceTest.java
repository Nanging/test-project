package com.sd.demo.placetest;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.demo.TestdemoApplicationTests;
import com.sd.demo.service.PlaceService;
import static org.junit.Assert.*;

import org.junit.Test;
public class DeletePlaceTest extends TestdemoApplicationTests{

	@Autowired
	private PlaceService placeService;
	@Test
	public void testDeletePlace001_001() {
		int placeid = -1;
		assertTrue("场地不存在情况测试失败", !placeService.deletePlace(placeid));
	}
	@Test
	public void testDeletePlace002_001() {
		int placeid = 1;
		assertTrue("场地存在情况测试失败", placeService.deletePlace(placeid));
	}
}
