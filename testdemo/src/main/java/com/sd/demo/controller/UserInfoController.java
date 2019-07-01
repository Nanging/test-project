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

import com.sd.demo.entity.Apply;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.ApplyService;
import com.sd.demo.service.PlaceService;
import com.sd.demo.service.UserService;
import com.sd.demo.support.PasswordEncoder;
import com.sd.demo.web.EditPlaceForm;
import com.sd.demo.web.ModifyForm;
import com.sd.demo.web.Result;
import com.sd.demo.web.ResultFactory;
import com.sd.demo.web.UserItem;

@CrossOrigin
@RequestMapping("/api/user")
@Controller
public class UserInfoController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private PlaceService placeService;
	
	@RequestMapping(value = "/info", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result info(HttpServletRequest request, HttpServletResponse response) {
		SysUser user = userService.getCurrentUser(request, response);
		System.out.println(user);
		if (user == null ) {
			return ResultFactory.buildAuthFailResult("fail");
		}
		UserItem userItem = new UserItem();
		userItem.setId(user.getId().intValue());
		userItem.setPhonenumber(user.getPhonenumber());
		userItem.setUsername(user.getUsername());
		return ResultFactory.buildSuccessResult(userItem);	
	}
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result modify(@RequestBody ModifyForm form,HttpServletRequest request, HttpServletResponse response) {
		SysUser user = userService.getCurrentUser(request, response);
		System.out.println(user);
		if (user == null ) {
			return ResultFactory.buildAuthFailResult("fail");
		}
		if (userService.modify(user.getId().intValue(),PasswordEncoder.encryptBasedDes(form.getOldPassword())
				,PasswordEncoder.encryptBasedDes(form.getPassword()))) {
			return ResultFactory.buildSuccessResult("success");
		}
		return ResultFactory.buildFailResult("fail");	
	}
	@RequestMapping(value = "/apply", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result apply(HttpServletRequest request, HttpServletResponse response) {
		SysUser user = userService.getCurrentUser(request, response);
		if (user == null ) {
			return ResultFactory.buildAuthFailResult("fail");
		}
		return ResultFactory.buildSuccessResult(userService.getUserApply(user));	
	}
	@RequestMapping(value = "/userApply", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result userApply(HttpServletRequest request, HttpServletResponse response) {
		SysUser user = userService.getCurrentUser(request, response);
		if (user == null ) {
			return ResultFactory.buildAuthFailResult("fail");
		}
		return ResultFactory.buildSuccessResult(userService.getOtherUserApply(user));	
	}
	@RequestMapping(value = "/confirm/{id}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result confirm(@PathVariable("id") Integer id) {
		if (applyService.setConfirm(id)) {
			return ResultFactory.buildSuccessResult("success");
		}
		return ResultFactory.buildAuthFailResult("fail");	
	}
	@RequestMapping(value = "/refuse/{id}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result refuse(@PathVariable("id") Integer id) {
		if (applyService.setRefuse(id)) {
			return ResultFactory.buildSuccessResult("success");
		}
		return ResultFactory.buildAuthFailResult("fail");	
	}
	@RequestMapping(value = "/place", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result place(HttpServletRequest request, HttpServletResponse response) {
		SysUser user = userService.getCurrentUser(request, response);
		if (user == null ) {
			return ResultFactory.buildAuthFailResult("fail");
		}
		return ResultFactory.buildSuccessResult(placeService.getAllUserPlace(user));	
	}
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result remove(@PathVariable("id") Integer id) {
		if (placeService.deletePlace(id)) {
			return ResultFactory.buildSuccessResult("success");
		}
		return ResultFactory.buildAuthFailResult("fail");		
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result edit(@RequestBody EditPlaceForm form,HttpServletRequest request, HttpServletResponse response) {
		SysUser user = userService.getCurrentUser(request, response);
		if (user == null ) {
			return ResultFactory.buildAuthFailResult("fail");
		}
		placeService.modifyPlace(	form.getId(), 
									form.getName(), 
									form.getType(), 
									form.getDescription(), 
									form.getSize(), 
									form.getAffordNumber(), 
									form.getLocation(), 
									form.getPrice(), 
									form.getRoomNumber());
		return ResultFactory.buildSuccessResult("success");
	}
	@RequestMapping(value = "/favorite", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result favorite(HttpServletRequest request, HttpServletResponse response) {
		SysUser user = userService.getCurrentUser(request, response);
		if (user == null ) {
			return ResultFactory.buildAuthFailResult("fail");
		}
		return ResultFactory.buildSuccessResult(placeService.getUserFavoritePlace(user.getId().intValue()));	
	}
	
	@RequestMapping(value = "favorite/publish/{id}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result addFavorite(@PathVariable("id") Integer id,HttpServletRequest request, HttpServletResponse response) {
		SysUser user = userService.getCurrentUser(request, response);
		if (user == null ) {
			return ResultFactory.buildAuthFailResult("fail");
		}
		userService.addFavorite(user.getId().intValue(), id);
		return ResultFactory.buildSuccessResult("success");	
	}
	@RequestMapping(value = "favorite/cancel/{id}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result removeFavorite(@PathVariable("id") Integer id,HttpServletRequest request, HttpServletResponse response) {
		SysUser user = userService.getCurrentUser(request, response);
		if (user == null ) {
			return ResultFactory.buildAuthFailResult("fail");
		}
		userService.removeFavorite(user.getId().intValue(), id);
		return ResultFactory.buildSuccessResult("success");	
	}
	
}
