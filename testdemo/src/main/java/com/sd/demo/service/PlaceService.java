package com.sd.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.sd.demo.entity.Place;
import com.sd.demo.entity.PlaceType;

public interface PlaceService {

	Place getPlaceDetail(int id);

	Place addPlace(String name, Set<Long> typeids, String detail, String size, Set<Long> imageids);

	Place modifyPlaceDetail(Place place);

	Boolean deletePlace(int id);

	Page<Place> getUserList(int page, int size);

	Page<Place> getAdminList(int page, int size);

	Map<String, Object> getPlaceList(int page, int size);

	List<Place> getAllUserPlace();

	List<PlaceType> getAllPlaceType();

	Set<PlaceType> parsePlaceType(String types);

	Place addPlace(String name, String typeidString, String detail, String size, String imageidString);

}
