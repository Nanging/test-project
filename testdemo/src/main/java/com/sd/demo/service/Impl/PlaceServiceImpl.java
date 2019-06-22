package com.sd.demo.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd.demo.dao.PlaceDao;
import com.sd.demo.dao.PlaceImageDao;
import com.sd.demo.dao.PlaceTypeDao;
import com.sd.demo.dao.ProductDao;
import com.sd.demo.dao.RecordDao;
import com.sd.demo.dao.RecordStateDao;
import com.sd.demo.dao.SysUserDao;
import com.sd.demo.entity.Place;
import com.sd.demo.entity.PlaceImage;
import com.sd.demo.entity.PlaceType;
import com.sd.demo.entity.Product;
import com.sd.demo.entity.Record;
import com.sd.demo.entity.RecordState;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.PlaceService;
import com.sd.demo.support.MyUserDetailsService;
@Service
public class PlaceServiceImpl implements PlaceService {
	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	private SysUserDao userDao;
	
	@Autowired
	private PlaceTypeDao placeTypeDao;
	
	@Autowired
	private PlaceImageDao placeImageDao;
	
	@Override
	public List<PlaceType> getAllPlaceType(){
		return placeTypeDao.findAll();
	}
	
	@Override 
	public Set<PlaceType> parsePlaceType(String types){
		String[] typeStrings = types.split("&");
		Set<PlaceType> placeTypes = new HashSet<PlaceType>();
		for (String type : typeStrings) {
			placeTypes.add( placeTypeDao.getOne(Long.parseLong(type.split("=")[1])));
		}
		return placeTypes;
	}
	
	@Override
	public Place getPlaceDetail(int id) {
		Integer ID = id;
		Place place = null;
		Optional<Place> placeOptional =  placeDao.findById(ID.longValue());	
		if (placeOptional.isPresent()) {
			place = placeOptional.get();
		}
		return place;
	}
	
	@Override
	public Place addPlace(String name,Set<Long> typeids, String detail, String size, Set<Long> imageids) {
		Place place = new Place();
		place.setName(name);
		place.setDetail(detail);
		place.setSize(size);
		List<PlaceType> types = placeTypeDao.findAllById(typeids);
		List<PlaceImage> images = placeImageDao.findAllById(imageids);
		place.setTypes(new HashSet<PlaceType>(types));
		place.setImages(new HashSet<PlaceImage>(images));
		placeDao.saveAndFlush(place);
		return placeDao.getOne(place.getId());
	}
	@Override
	public Place addPlace(String name,String typeidString, String detail, String size, String imageidString) {
		Place place = new Place();
		place.setName(name);
		place.setDetail(detail);
		place.setSize(size);
		Set<PlaceType> placeTypes = parsePlaceType(typeidString);
		List<PlaceType> types = new ArrayList<PlaceType>(placeTypes);
		String[] imageidStrings = imageidString.split(",");
		Set<Long> imageids = new HashSet<Long>();
		for (String string : imageidStrings) {
			imageids.add(Long.parseLong(string));
		}
		List<PlaceImage> images = placeImageDao.findAllById(imageids);
		place.setTypes(new HashSet<PlaceType>(types));
		place.setImages(new HashSet<PlaceImage>(images));
		placeDao.saveAndFlush(place);
		return placeDao.getOne(place.getId());
	}
	
	@Override
	public Place modifyPlaceDetail(Place place) {
		placeDao.saveAndFlush(place);
		return placeDao.findById(place.getId()).get();
	}
	@Override
	public Boolean deletePlace(int id) {
		Integer ID = id;
		Place record = placeDao.findById(ID.longValue()).get();
		placeDao.delete(record);
		Optional<Place> placeOptional =  placeDao.findById(ID.longValue());	
		if (placeOptional.isPresent()) {
			return false;
		}
		return true;
	}
	
	@Override
	public Page<Place> getUserList(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		SysUser user = userDao.findByUsername(MyUserDetailsService.getCurrentUser());
		System.out.println("user id "+user.getId());
		return placeDao.findByOwner(user, pageable);
	}
	
	@Override
	public List<Place> getAllUserPlace(){
		SysUser user = userDao.findByUsername(MyUserDetailsService.getCurrentUser());
		System.out.println("user id "+user.getId());
		return placeDao.findByOwner(user);
	}
	
	@Override
	public Page<Place> getAdminList(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return placeDao.findAll(pageable);
	}
	
	@Override
	public Map<String, Object> getPlaceList(int page, int size) {
		Map<String, Object>attr = new HashMap<String, Object>();
		Page<Place> places = getUserList(page, size);
		attr.put("records", places.getContent());
		attr.put("total", places.getNumber());
		attr.put("page", page);
		return attr;
	}
}
