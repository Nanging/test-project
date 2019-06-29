package com.sd.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.entity.Place;
import com.sd.demo.entity.PlaceImage;
import com.sd.demo.entity.SysUser;
@Repository
public interface PlaceImageDao extends JpaRepository<PlaceImage,Long> {
	List<PlaceImage> findByPlace(int place);
}
