package com.sd.demo.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.entity.Product;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.ProductService;
import com.sd.demo.service.RecordService;
import com.sd.demo.service.UserService;
import com.sd.demo.support.MyUserDetailsService;

@Controller
@RequestMapping("/record")
public class RecordController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('RecordNew')")
	@RequestMapping("/new/{id}")
	public String editRecord(@PathVariable("id") Integer id,Model model) {
		System.out.println(MyUserDetailsService.getCurrentUser());
		Product product = productService.getProductDetail(id);
		model.addAttribute("product", product);
		return "record/edit";
	}
	
	@PreAuthorize("hasAuthority('RecordAdd')")
	@RequestMapping(value = "/add")
	@ResponseBody
	public String add(@RequestParam(value = "starttime")  String starttime,@RequestParam(value = "endtime")  String endtime,
			@RequestParam(value = "productid")  int productid) {
		Timestamp ts1 = Timestamp.valueOf(starttime.replace('T', ' '));
		System.out.println(ts1);
		Timestamp ts2 = Timestamp.valueOf(endtime.replace('T', ' '));
		System.out.println(ts2);
		System.out.println(productid);
		SysUser currentUser = userService.getUserByName(MyUserDetailsService.getCurrentUser());
		recordService.addRecord(currentUser.getId(), (long)productid, ts1, ts2);
		return "product/detail";
	}
}
