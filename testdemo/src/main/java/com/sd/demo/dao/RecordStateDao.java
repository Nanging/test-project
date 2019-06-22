package com.sd.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.entity.Place;
import com.sd.demo.entity.RecordState;
@Repository
public interface RecordStateDao extends JpaRepository<RecordState,Long> {

}
