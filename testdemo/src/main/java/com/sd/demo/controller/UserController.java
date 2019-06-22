package com.sd.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.entity.SysUser;
import com.sd.demo.service.UserService;
import com.sd.demo.support.MyUserDetailsService;
/**
 *  for test
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('UserIndex')")
	@RequestMapping("/index")
	public String index(Model model) {
		String username = MyUserDetailsService.getCurrentUser();
		SysUser sysUser = userService.getUserByName(username);
		model.addAttribute("user", sysUser);
		return "user/index";
	}
	@PreAuthorize("hasAuthority('UserModify')")
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(@RequestParam(value = "password") String password,
			@RequestParam(value = "tel") String tel) {
		String username = MyUserDetailsService.getCurrentUser();
		SysUser sysUser = userService.getUserByName(username);
		sysUser.setPassword(password);
		sysUser.setPhonenumber(tel);
		return "user/index";
	}	
}
