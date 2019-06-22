package com.sd.demo.controller;

import java.util.HashMap;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sd.demo.entity.Product;
import com.sd.demo.service.ProductService;

@Controller

public class ProductListController {

	@Autowired
	private ProductService productService;
	
	private final int size = 1;
	
	@RequestMapping(value = {"/product/list","/","/index"})
	public String list(@RequestParam(value="page", defaultValue="0") int page,
			  Model model) {
		model.addAllAttributes(productService.getProductList(page, size));
		return "product/list";
	}
	
	
}
