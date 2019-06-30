package com.sd.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.entity.Apply;
import com.sd.demo.entity.Comment;
import com.sd.demo.entity.Place;
@Repository
public interface ApplyDao  extends JpaRepository<Apply,Long>{
	List<Apply> findByPlace(Place place);
}
