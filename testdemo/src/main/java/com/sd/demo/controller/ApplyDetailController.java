package com.sd.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.entity.Apply;
import com.sd.demo.entity.Product;
import com.sd.demo.service.ApplyService;
import com.sd.demo.service.ProductService;
import com.sd.demo.support.MyUserDetailsService;

@Controller
@RequestMapping("/apply/detail")
public class ApplyDetailController {
	@Autowired
	private ApplyService applyService;
	
	
	@RequestMapping(value = "/{id}")
	public String detail(@PathVariable("id") Integer id,Model model) {
		System.out.println(MyUserDetailsService.getCurrentUser());
		Apply apply = applyService.getApplyDetail(id);
		model.addAttribute("apply", apply);
		return "apply/detail";
	}
	
	@PreAuthorize("hasAuthority('ApplyDelete')")
	@RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteApply(@PathVariable("id") Integer id,
			  Model model) {
		return "apply/list";
	}
	
	@PreAuthorize("hasAuthority('ApplyModify')")
	@RequestMapping(value = "/{id}/put",method = RequestMethod.PUT)
	@ResponseBody
	public String modifyApply(@PathVariable("id")  Integer id,
			  Model model) {
		return "product/detail";
	}
	
	@RequestMapping(value = "/{id}/confirm")
	@ResponseBody
	public String confirm(@PathVariable("id")  Integer id) {
		return "apply/detail";
	}
	@RequestMapping(value = "/{id}/refuse")
	@ResponseBody
	public String refuse(@PathVariable("id")  Integer id) {
		return "apply/detail";
	}
}
