package com.sd.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.entity.SysUser;
import com.sd.demo.service.CommentService;
import com.sd.demo.service.UserService;
import com.sd.demo.web.AddCommentForm;
import com.sd.demo.web.PlaceListRequest;
import com.sd.demo.web.Result;
import com.sd.demo.web.ResultFactory;

@CrossOrigin
@RequestMapping("/api/comment")
@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/{id}", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result detail(@PathVariable("id") Integer id) {
		return ResultFactory.buildSuccessResult(commentService.getCommentsByPlace(id));	
	}
	
	@RequestMapping(value = "/publish", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result publish(@RequestBody AddCommentForm form,HttpServletRequest request, HttpServletResponse response) {
		SysUser user = userService.getCurrentUser(request, response);
		if (user == null ) {
			return ResultFactory.buildAuthFailResult("fail");
		}
		commentService.addNewComment(user, form.getPlaceid(), form.getComment());
		return ResultFactory.buildSuccessResult("success");	
	}
}
