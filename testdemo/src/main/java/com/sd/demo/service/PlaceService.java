package com.sd.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.sd.demo.entity.Place;
import com.sd.demo.entity.SysUser;
import com.sd.demo.web.PlaceItem;


public interface PlaceService {

	Place getPlaceDetail(int id);

	Place modifyPlaceDetail(Place place);

	Boolean deletePlace(int id);

	Page<Place> getUserList(int page, int size);


	List<PlaceItem> getPlaceList(String name, String type, int page);

	Page<Place> getAdminList(int page);


	boolean addPlace(String name, String type, String description, int size, int affordNumber, String location, int price,
			int roomNumber, Set<String> images, HttpServletRequest request, HttpServletResponse response);

	PlaceItem getPlaceById(int id);

	List<PlaceItem> getAllUserPlace(SysUser user);

	Place modifyPlace(int id, String name, String type, String description, int size, int affordNumber, String location,
			int price, int roomNumber);

	boolean addPlace(String name, String type, String description, int size, int affordNumber, String location, int price,
			int roomNumber, Set<String> imageUrls, int ownerid);

	List<PlaceItem> getUserFavoritePlace(int userid);


}
