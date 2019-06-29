package com.sd.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.entity.Place;
import com.sd.demo.entity.SysUser;
@Repository
public interface PlaceDao extends JpaRepository<Place,Long>{
	Page<Place> findByOwner(SysUser owner,Pageable pageable);
	List<Place> findByOwner(SysUser owner);
}
