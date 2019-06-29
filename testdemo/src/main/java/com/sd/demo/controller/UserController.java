package com.sd.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sd.demo.support.MyUserDetailsService;
import com.sd.demo.web.Result;

@Controller
public class UserController {

	@RequestMapping("/login")
	public String Login() {
		
		System.out.println("user:" + MyUserDetailsService.getCurrentUser());
		
		return "/login";
	}
	
	@RequestMapping("/register")
	public Result Register() {
		
		
		return null;
	}
}
