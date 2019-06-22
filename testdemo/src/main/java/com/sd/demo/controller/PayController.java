package com.sd.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay")
public class PayController {

	
	@RequestMapping("/index")
	public String payIndex() {
		return "pay/index";
	}
}
