package com.sd.demo.service.Impl;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

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
import com.sd.demo.web.ResultFactory;

import net.bytebuddy.asm.Advice.Return;
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
		if(!placeDao.existsById((long)id))return null;
		Integer ID = id;
		Place place = null;
		Optional<Place> placeOptional =  placeDao.findById(ID.longValue());	
		if (placeOptional.isPresent()) {
			place = placeOptional.get();
		}
		return place;
	}
	
	@Override
	public boolean addPlace(String name,String type, String description, int size,
			int affordNumber,String location,int price,int roomNumber,
			Set<String> images,HttpServletRequest request, HttpServletResponse response) {
		if(name == null || type == null || description == null || location == null || images == null)
			return false;
		if(name.equals("") || type.equals("") || description.equals("") || location.equals("")|| images.size() == 0)
			return false;
		if(size < 0 || price < 0 || roomNumber < 0)return false;
		SysUser user = userService.getCurrentUser(request, response);
		if (user == null) {
			return false;
		}
		return addPlace(name, type, description, size, affordNumber, location, price, roomNumber, images, user.getId().intValue());
	}
	
	@Override
	public boolean addPlace(String name,String type, String description, int size,
			int affordNumber,String location,int price,int roomNumber,
			Set<String> imageUrls,int ownerid) {
		if(name == null || type == null || description == null || location == null || imageUrls == null)
			return false;
		if(name.equals("") || type.equals("") || description.equals("") || location.equals("")|| imageUrls.size() == 0)
			return false;
		if(size < 0 || price < 0 || roomNumber < 0)return false;
		if(!userDao.existsById((long)ownerid))return false;
		SysUser owner = userDao.getOne((long)ownerid);
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
			image.setPlace(place);
			placeImageDao.saveAndFlush(image);
		}
		place.setImages(images);
		placeDao.saveAndFlush(place);
		if (null != placeDao.getOne(place.getId())) {
			return true; 
		};
		return false;
	}
	@Override
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
		if(!placeDao.existsById((long)id))return false;
		Integer ID = id;
		System.out.println(id);
		if(!placeDao.existsById(ID.longValue()))return false;
		Place place = placeDao.getOne(ID.longValue());
		SysUser user = place.getOwner();
		user.getPlaces().remove(place);
		userDao.saveAndFlush(user);
		
		placeDao.deleteById((long)id);
		placeDao.flush();
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
	public List<PlaceItem> getUserFavoritePlace(int userid){
		if(!userDao.existsById((long)userid))return null;
		System.out.println("id:"+userid);
		SysUser user = userDao.getOne((long)userid);
		if (user == null) {
			return null;
		}
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
		if(name == null || type == null || page <= 0)return null;
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
		if(!placeDao.existsById((long)id))return null;
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
