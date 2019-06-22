package com.sd.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sd.demo.service.PlaceService;
import com.sd.demo.service.ProductService;

@Controller
@RequestMapping("/place")
public class PlaceListController {
	@Autowired
	private PlaceService placeService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="page", defaultValue="0") int page,
			  @RequestParam(value="size", defaultValue="10") int size ,
			  Model model) {
		model.addAllAttributes(placeService.getPlaceList(page, size));
		return "place/list";
	}
	
}
