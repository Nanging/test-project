package com.sd.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.entity.Place;
import com.sd.demo.entity.PlaceType;
@Repository
public interface PlaceTypeDao  extends JpaRepository<PlaceType,Long>{

}
