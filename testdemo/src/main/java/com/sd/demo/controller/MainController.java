package com.sd.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.dao.SysUserDao;
/**
 *  for test
 * @author Administrator
 *
 */
@Controller
public class MainController {

	@Autowired
	private SysUserDao userDao;
	
//	@RequestMapping("/")
//	@ResponseBody
//	public String index() {
//		System.out.println(userDao.findByUsername("123"));
//		
//		return "123";
//	}
}
