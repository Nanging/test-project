package com.sd.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.dao.RecordDao;
import com.sd.demo.entity.Record;
import com.sd.demo.service.RecordService;

@Controller
@RequestMapping("/record/list")
public class RecordListController {

	@Autowired
	private RecordService recordService;
	
	private final int elementNumber = 10;
	
	@PreAuthorize("hasAuthority('RecordUser')")
	@RequestMapping("/user/{page}")
	public String getUserRecordList(@PathVariable("page") Integer page,Model model) {
		Page<Record> records = recordService.getUserList(page, elementNumber);
		model.addAttribute("records", records.getContent());
		model.addAttribute("total", records.getNumber());
		model.addAttribute("page", page);
		return "record/list";
	}

	@PreAuthorize("hasAuthority('RecordAdmin')")
	@RequestMapping("/admin/{page}")
	public String getAdminRecordList(@PathVariable("page") Integer page,Model model) {
		Page<Record> records = recordService.getAdminList(page, elementNumber);
		model.addAttribute("records", records.getContent());
		model.addAttribute("total", records.getNumber());
		model.addAttribute("page", page);
		return "record/list";
	}
}
