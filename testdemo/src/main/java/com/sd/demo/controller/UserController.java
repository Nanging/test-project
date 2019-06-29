package com.sd.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.service.UserService;
import com.sd.demo.web.LoginForm;
import com.sd.demo.web.RegisterForm;
import com.sd.demo.web.Result;
import com.sd.demo.web.ResultFactory;

@CrossOrigin
@RequestMapping("/api/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result Login(@RequestBody LoginForm loginForm,HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(loginForm);
		if (userService.userLogin(loginForm.getUsername(), loginForm.getPassword())) {
			HttpSession session = request.getSession();
			String sessionId = session.getId();
			userService.addUserSession(loginForm.getUsername() + "_" + loginForm.getPassword(), sessionId);

			Cookie cookie = new Cookie("loginStatus", loginForm.getUsername() + "_" + loginForm.getPassword());
			cookie.setPath("/");
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
			return ResultFactory.buildSuccessResult("success");
		} 
		return ResultFactory.buildFailResult("fail");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result Register(@RequestBody RegisterForm registerForm) {
		
		System.out.println(registerForm);
		Result result = userService.userRegister(registerForm.getUsername(), registerForm.getPassword(), registerForm.getPhonenumber());
		if (result != null) {
			return result;
		}
		return ResultFactory.buildFailResult("fail");
	}
}
