package com.sd.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.demo.entity.Place;
import com.sd.demo.service.PlaceService;
import com.sd.demo.support.MyUserDetailsService;

@Controller
@RequestMapping("/place/detail")
public class PlaceDetailController {
	@Autowired
	private PlaceService placeService;
	
	
	@RequestMapping(value = "/{id}")
	public String detail(@PathVariable("id") Integer id,Model model) {
		System.out.println(MyUserDetailsService.getCurrentUser());
		Place place = placeService.getPlaceDetail(id);
		model.addAttribute("place", place);
		return "place/detail";
	}
	
	@PreAuthorize("hasAuthority('PlaceDelete')")
	@RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteProduct(@PathVariable("id") Integer id,
			  Model model) {
		return "place/list";
	}
	
	@PreAuthorize("hasAuthority('PlaceModify')")
	@RequestMapping(value = "/{id}/put",method = RequestMethod.PUT)
	@ResponseBody
	public String modifyProduct(@PathVariable("id")  Integer id,
			  Model model) {
		return "place/detail";
	}
}
