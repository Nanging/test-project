package com.sd.demo;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sd.demo.entity.Place;
import com.sd.demo.service.ApplyService;
import com.sd.demo.service.PlaceService;
import com.sd.demo.web.PlaceItem;

public class ApplyTest extends TestdemoApplicationTests {
	@Autowired ApplyService applyService;
	@Autowired PlaceService PlaceService;
	
	//APPLY_001_FUN_001
	@Test
	public void testSetConfirm() {
		//UT_AP_001_001_001
		Assert.assertEquals(false, applyService.setConfirm(0));
		
		//UT_AP_001_001_002
		Assert.assertEquals(false, applyService.setConfirm(-1));
		
		//UT_AP_001_001_003
		Assert.assertEquals(false, applyService.setConfirm(100));
	}
	
	//APPLY_001_FUN_002
	@Test
	public void testSetRefuse() {
		//UT_AP_001_002_001
		Assert.assertEquals(false, applyService.setRefuse(0));
		
		//UT_AP_001_002_002
		Assert.assertEquals(false, applyService.setRefuse(-1));
		
		//UT_AP_001_002_003
		Assert.assertEquals(false, applyService.setRefuse(100));
	}
	
	//APPLY_001_FUN_003
	@Test
	public void testGetApplyByPlace() {
		//UT_AP_001_003_001
		Assert.assertEquals(0, applyService.getApplyByPlace(0).size());
		
		//UT_AP_001_003_002
		Assert.assertEquals(0, applyService.getApplyByPlace(-1).size());
		
		//UT_AP_001_003_003
		Assert.assertEquals(0, applyService.getApplyByPlace(100).size());
	}
	
	//APPLY_001_FUN_004
	@Test
	public void testGetApplyDetail() {
		//UT_AP_001_004_001
		Assert.assertEquals(null, applyService.getApplyDetail(0));
			
		//UT_AP_001_004_002
		Assert.assertEquals(null, applyService.getApplyDetail(-1));
		
		//UT_AP_001_004_003
		Assert.assertEquals(null, applyService.getApplyDetail(100));
	}
	
	//APPLY_001_FUN_005
	@Test
	public void testAddApply() {
		//UT_AP_001_005_001
		Assert.assertEquals(null, applyService.addApply(0,new Date(),2,1);
		Assert.assertEquals(null, applyService.addApply(1,new Date(),0,1);
		Assert.assertEquals(null, applyService.addApply(1,new Date(),2,0);
			
		//UT_AP_001_005_002
		Assert.assertEquals(null, applyService.addApply(-1,new Date(),2,1);
		Assert.assertEquals(null, applyService.addApply(1,new Date(),-1,1);
		Assert.assertEquals(null, applyService.addApply(1,new Date(),2,-1);
		
		//UT_AP_001_005_003
		Assert.assertEquals(null, applyService.addApply(100,new Date(),2,1);
	}
	
	//APPLY_001_FUN_006
	@Test
	public void testDeleteApply() {
		//UT_AP_001_006_001
		Assert.assertEquals(false, applyService.deleteApply(0));
		
		//UT_AP_001_006_002
		Assert.assertEquals(false, applyService.deleteApply(-1));
		
		//UT_AP_001_006_003
		Assert.assertEquals(false, applyService.deleteApply(100));
	}
	
}
