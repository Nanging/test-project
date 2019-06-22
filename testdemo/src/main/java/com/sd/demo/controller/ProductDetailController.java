package com.sd.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sd.demo.entity.Product;
import com.sd.demo.service.ProductService;
import com.sd.demo.support.MyUserDetailsService;

@Controller
@PreAuthorize("hasAuthority('ProductDetail')")
@RequestMapping("/product/detail")
public class ProductDetailController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/{id}")
	public String detail(@PathVariable("id") Integer id,Model model) {
		System.out.println(MyUserDetailsService.getCurrentUser());
		Product product = productService.getProductDetail(id);
		model.addAttribute("product", product);
		return "product/detail";
	}
	
	@RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteProduct(@PathVariable("id") Integer id,
			  Model model) {
		productService.deleteProduct(id);
		return "product/list";
	}
	
	@RequestMapping(value = "/{id}/put",method = RequestMethod.PUT)
	@ResponseBody
	public String modifyProduct(@PathVariable("id")  Integer id,
			  Model model) {
		
//		productService.modifyProductDetail(product);
		return "product/detail";
	}
}
