package com.sd.demo.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.entity.Place;
import com.sd.demo.entity.Product;
import com.sd.demo.service.PlaceService;
import com.sd.demo.service.ProductService;
import com.sd.demo.support.MyUserDetailsService;
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PlaceService placeService;
	
	@RequestMapping("/new")
	public String editProduct(Model model) {
		List<Place> places = placeService.getAllUserPlace();
		model.addAttribute("places", places);
		model.addAttribute("types", placeService.getAllPlaceType());
		return "product/edit";
	}
	
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public String add(@RequestParam(value = "price")  int price,@RequestParam(value = "unit",defaultValue = "1")  String unit,
			@RequestParam(value = "placeid")  int placeid) {
		productService.addProduct((long)placeid,(long)price, unit);
		return "product/detail";
	}
}
