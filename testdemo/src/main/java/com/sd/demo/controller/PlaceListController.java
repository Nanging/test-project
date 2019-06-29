package com.sd.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.service.PlaceService;
import com.sd.demo.web.AddPlaceForm;
import com.sd.demo.web.PlaceListRequest;
import com.sd.demo.web.Result;
import com.sd.demo.web.ResultFactory;
import com.sun.org.apache.bcel.internal.generic.NEW;

@CrossOrigin
@RequestMapping("/api/place")
@Controller
public class PlaceListController {

	@Autowired
	private PlaceService placeService;
	
	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result list(@RequestBody PlaceListRequest para) {
		
		System.out.println(para);
		return ResultFactory.buildSuccessResult(placeService.getPlaceList(para.name,para.type,para.page));
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result add(@RequestBody AddPlaceForm form ,HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(form);
		placeService.addPlace(	form.getName(), 
								form.getType(), 
								form.getDescription(), 
								form.getSize(), 
								form.getAffordNumber(), 
								form.getLocation(), 
								form.getPrice(), 
								form.getRoomNumber(), 
								form.getImages(), 
								request, response);
		return null;
	}
	@RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result detail(@RequestBody PlaceListRequest para) {
		
		System.out.println(para);
		return ResultFactory.buildSuccessResult(placeService.getPlaceList(para.name,para.type,para.page));
		
	}
}
