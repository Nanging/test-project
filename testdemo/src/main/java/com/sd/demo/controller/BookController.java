package com.sd.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *  for test
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/book")
public class BookController {

	@PreAuthorize("hasAuthority('BookList')")
	@RequestMapping("/list")
	public String list() {
		
		return "book/list";
	}
	
	@PreAuthorize("hasAuthority('BookAdd')")
	@RequestMapping("/add")
	public String add() {
		return "book/add";
	}
	
	@PreAuthorize("hasAuthority('BookDetail')")
	@RequestMapping("/detail")
	public String detail() {
		return "book/detail";
	}
}
