package com.sd.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.HttpResource;
import org.thymeleaf.expression.Ids;

import com.sd.demo.entity.PlaceImage;
import com.sd.demo.entity.PlaceType;
import com.sd.demo.service.PlaceImageService;
import com.sd.demo.service.PlaceService;

@Controller
@RequestMapping("/place/upload")
public class PlaceInfoUploadController {

	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private PlaceImageService placeImageService;
	
	@RequestMapping("/new")
	public String uploadNewPlace(Model model) {
		List<PlaceType> types = placeService.getAllPlaceType();
		model.addAttribute("types", types);
		return "place/upload";
	}
	
	@RequestMapping("/image")
	@ResponseBody
	public List<PlaceImage> uploadPlaceImage(@RequestParam(value = "imgInput[]") MultipartFile[] files,HttpServletResponse response) {
		System.out.println("files "+ files.length);
		List<PlaceImage> images = new ArrayList<PlaceImage>();
		PlaceImage image = new PlaceImage();
		image.setId((long)1);
		image.setUrl("123");
		images.add(image);
		images.add(image);
		
		return images;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String createNewPlace(@RequestParam("name") String name,@RequestParam("images") String images,
			@RequestParam("types") String types, @RequestParam("size") String size,
			@RequestParam("detail") String detail) {
		System.out.println("types "+ types);
		System.out.println("images "+ images);
		String[] ids = images.split(",");
		Set<Long> imageids = new HashSet<Long>();
		for (String string : ids) {
			System.out.println("ids: "+Long.parseLong(string));
			imageids.add(Long.parseLong(string));
		}
		Set<PlaceType> placeTypes = placeService.parsePlaceType(types);
//		System.out.println("types "+ types.length);
//		for (String string : types) {
//			System.out.println(string);
//		}
//		System.out.println("size "+ size);
//		System.out.println("detail "+detail);
//		placeService.addPlace(name, placeTypes
//				, detail, size, imageids);
		return "{}";
	}
	
}
