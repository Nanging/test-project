package com.sd.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.entity.Place;
import com.sd.demo.service.ApplyService;
import com.sd.demo.service.PlaceService;
import com.sd.demo.service.ProductService;

@Controller
@RequestMapping("/apply")
public class ApplyController {


	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private PlaceService placeService;
	
	@RequestMapping("/new")
	public String editApply(Model model) {
		List<Place> places = placeService.getAllUserPlace();
		model.addAttribute("places", places);
		model.addAttribute("types", placeService.getAllPlaceType());
		return "apply/edit";
	}
	
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public String add(@RequestParam(value = "placeid")  int placeid) {
		applyService.addApply((long)placeid);
		return "apply/detail";
	}
	

	
}
