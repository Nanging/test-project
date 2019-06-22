package com.sd.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.entity.Product;
import com.sd.demo.entity.Record;
import com.sd.demo.service.ProductService;
import com.sd.demo.service.RecordService;
import com.sd.demo.support.MyUserDetailsService;

@Controller
@RequestMapping("/record/detail")
public class RecordDetailController {
	
	@Autowired
	private RecordService recordService;
	
	@PreAuthorize("hasAuthority('RecordDetail')")
	@RequestMapping(value = "/{id}")
	public String detail(@PathVariable("id") Integer id,Model model) {
		System.out.println(MyUserDetailsService.getCurrentUser());
		Record record = recordService.getRecordDetail(id);
		model.addAttribute("record", record);
		return "record/detail";
	}
	
	@PreAuthorize("hasAuthority('RecordDelete')")
	@RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE)
	public String deleteRecord(@PathVariable("id") Integer id,
			  Model model) {
		return "record/list";
	}
	
	@PreAuthorize("hasAuthority('RecordModify')")
	@RequestMapping(value = "/{id}/put",method = RequestMethod.PUT)
	@ResponseBody
	public String modifyRecord(@PathVariable("id")  Integer id,
			  Model model) {
		return "record/detail";
	}
}
