package com.sd.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@PreAuthorize("hasAuthority('AdminIndex')")
	@RequestMapping("/index")
	public String adminIndex() {
		
		return "admin/index";
	}
}
