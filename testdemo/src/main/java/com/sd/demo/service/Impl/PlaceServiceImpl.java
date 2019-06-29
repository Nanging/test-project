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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd.demo.dao.PlaceDao;
import com.sd.demo.dao.PlaceImageDao;
import com.sd.demo.dao.SysUserDao;
import com.sd.demo.entity.Place;
import com.sd.demo.entity.PlaceImage;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.PlaceService;
import com.sd.demo.service.UserService;
import com.sd.demo.web.PlaceItem;
@Service
public class PlaceServiceImpl implements PlaceService {
	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	private SysUserDao userDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlaceImageDao placeImageDao;
	
	private static final int Size = 10;
	
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
	public Place addPlace(String name,String type, String description, int size,
			int affordNumber,String location,int price,int roomNumber,
			Set<String> images,HttpServletRequest request, HttpServletResponse response) {
		SysUser user = userService.getCurrentUser(request, response);
		if (user == null) {
			return null;
		}
		return addPlace(name, type, description, size, affordNumber, location, price, roomNumber, images, user);
	}
	
	@Override
	public Place addPlace(String name,String type, String description, int size,
			int affordNumber,String location,int price,int roomNumber,
			Set<String> imageUrls,SysUser owner) {
		Place place = new Place();
		place.setName(name);
		place.setDescription(description);
		place.setSize(size);
		place.setType(type);
		place.setOwner(owner);
		place.setAffordNumber(affordNumber);
		place.setLocation(location);
		place.setPrice(price);
		place.setRoomNumber(roomNumber);
		placeDao.saveAndFlush(place);
		Set<PlaceImage> images = new HashSet<>();
		for (String url : imageUrls) {
			PlaceImage image = new PlaceImage();
			image.setUrl(url);
		}
		place.setImages(images);
		placeDao.saveAndFlush(place);
		return placeDao.getOne(place.getId());
	}
	
	public Place modifyPlace(int id,String name,String type, String description, int size,
			int affordNumber,String location,int price,int roomNumber) {
		Place place = getPlaceDetail(id);
		place.setName(name);
		place.setType(type);
		place.setDescription(description);
		place.setSize(size);
		place.setAffordNumber(affordNumber);
		place.setLocation(location);
		place.setPrice(price);
		place.setRoomNumber(roomNumber);
		modifyPlaceDetail(place);
		return placeDao.findById(place.getId()).get();
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
		SysUser user = userDao.findByUsername("MyUserDetailsService.getCurrentUser()");
		System.out.println("user id "+user.getId());
		return placeDao.findByOwner(user, pageable);
	}
	
	@Override
	public List<PlaceItem> getAllUserPlace(SysUser user){
		List<Place>places = placeDao.findByOwner(user);
		List<PlaceItem > resultList = new ArrayList<>();
		for (Place place : places) {
			PlaceItem item = getPlaceResult(place);
			resultList.add(item);
		}
		return resultList;
	}
	
	@Override
	public List<PlaceItem> getUserFavoritePlace(SysUser user){
		Set<Place>places = user.getPlaceList();
		List<PlaceItem > resultList = new ArrayList<>();
		for (Place place : places) {
			PlaceItem item = getPlaceResult(place);
			resultList.add(item);
		}
		return resultList;
	}
	@Override
	public Page<Place> getAdminList(int page){
		Pageable pageable = PageRequest.of(page,Size);
		return placeDao.findAll(pageable);
	}
	
	@Override
	public List<PlaceItem> getPlaceList(String name,String type, int page){
		Pageable pageable = PageRequest.of(page-1, Size);
		List<Place> places = placeDao.findByNameLikeOrTypeLike("%"+name+"%", "%"+type+"%", pageable).getContent();
		System.out.println(places.size());
		List<PlaceItem > resultList = new ArrayList<>();
		for (Place place : places) {
			PlaceItem item = getPlaceResult(place);
			resultList.add(item);
		}
		return resultList;
	}
	@Override
	public PlaceItem getPlaceById(int id) {
		Place place = placeDao.getOne((long) id);
		return getPlaceResult(place);
	}
	public PlaceItem getPlaceResult(Place place){
		PlaceItem item = new PlaceItem();
		item.setAffordNumber(place.getAffordNumber());
		item.setDescription(place.getDescription());
		item.setId(place.getId());
		Set<String> images = new HashSet<>();
		for (PlaceImage image : place.getImages()) {
			images.add(image.getUrl());
		}
		item.setImages(images);
		item.setLocation(place.getLocation());
		item.setName(place.getName());
		item.setOwner(place.getOwner().getUsername());
		item.setPhonenumber(place.getOwner().getPhonenumber());
		item.setPrice(place.getPrice());
		item.setRoomNumber(place.getRoomNumber());
		item.setSize(place.getSize());
		item.setType(place.getType());	
		return item;
	}
}
