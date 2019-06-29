package com.sd.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.service.CommentService;
import com.sd.demo.web.PlaceListRequest;
import com.sd.demo.web.Result;
import com.sd.demo.web.ResultFactory;

@CrossOrigin
@RequestMapping("/api/comment")
@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	@RequestMapping(value = "/{id}", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result detail(@PathVariable("id") Integer id) {
		return ResultFactory.buildSuccessResult(commentService.getCommentsByPlace(id));	
	}
}
