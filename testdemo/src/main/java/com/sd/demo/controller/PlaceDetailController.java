package com.sd.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sd.demo.service.ApplyService;
import com.sd.demo.service.PlaceService;
import com.sd.demo.service.UserService;
import com.sd.demo.web.ApplyForm;
import com.sd.demo.web.Result;
import com.sd.demo.web.ResultFactory;
@CrossOrigin
@RequestMapping("/api/place/detail")
@Controller
public class PlaceDetailController {
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplyService applyService;
	
	@RequestMapping(value = "{id}", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result detail(@PathVariable("id") Integer id) {
		return ResultFactory.buildSuccessResult(placeService.getPlaceById(id));	
	}
	
	@RequestMapping(value = "/apply/{id}", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result apply(@RequestBody ApplyForm form,HttpServletRequest request, HttpServletResponse response) {

		if (null != applyService.addApply(form.getPlaceid(), form.getStartTime(), form.getTime(), 
				userService.getCurrentUser(request, response))) {
			return ResultFactory.buildSuccessResult("apply successfully");
		}
		return ResultFactory.buildSuccessResult("fail");	
	}
	

}
