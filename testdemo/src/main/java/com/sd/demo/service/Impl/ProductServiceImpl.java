package com.sd.demo.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd.demo.dao.PlaceDao;
import com.sd.demo.dao.ProductDao;
import com.sd.demo.dao.ProductStateDao;
import com.sd.demo.entity.Place;
import com.sd.demo.entity.Product;
import com.sd.demo.entity.ProductState;
import com.sd.demo.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductStateDao productStateDao;
	
	@Autowired
	private PlaceDao placeDao;
	
	@Override
	public Product getProductDetail(int id) {
		Integer ID = id;
		Product product = null;
		Optional<Product> productOptional =  productDao.findById(ID.longValue());	
		if (productOptional.isPresent()) {
			product = productOptional.get();
		}
		return product;
	}
	@Override
	public Product addProduct(Long placeid,Long price, String unit) {
		Product product = new Product();
		Place place = placeDao.getOne(placeid);
		product.setPlace(place);
		product.setPrice(price);
		product.setUnit(unit);
		ProductState state = productStateDao.getOne((long)1);
		product.setState(state);
		productDao.saveAndFlush(product);
		return productDao.getOne(product.getId());
	}
	
	@Override
	public Product modifyProductDetail(Product product) {
		productDao.saveAndFlush(product);
		return productDao.getOne(product.getId());
	}
	@Override
	public Boolean deleteProduct(int id) {
		Integer ID = id;
		Product product = productDao.findById(ID.longValue()).get();
		productDao.delete(product);
		Optional<Product> productOptional =  productDao.findById(ID.longValue());	
		if (productOptional.isPresent()) {
			return false;
		}
		return true;
	}
	
	@Override
	public Page<Product> getList(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return productDao.findAll(pageable);
	}
	
	@Override
	public Map<String, Object> getProductList(int page, int size) {
		Map<String, Object>attr = new HashMap<String, Object>();
		Page<Product> products = getList(page, size);
		attr.put("products", products.getContent());
		attr.put("total", products.getNumber());
		attr.put("page", page);
		return attr;
	}
}
