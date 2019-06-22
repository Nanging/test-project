package com.sd.demo.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.sd.demo.entity.Product;

public interface ProductService {

	Map<String, Object> getProductList(int page, int size);

	Page<Product> getList(int page, int size);

	Product getProductDetail(int id);

	Product modifyProductDetail(Product product);

	Boolean deleteProduct(int id);


	Product addProduct(Long placeid, Long price, String unit);



}
