package com.sd.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.service.UserService;
/**
 *  for test
 * @author Administrator
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String addUser(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "tel") String tel) {
		System.out.println(username+password+tel);
		userService.addNewUser(username, password, tel);
		return "login";
	}
	
	@RequestMapping("/fail")
	public String loginFail() {
		return "fail";
	}
}
