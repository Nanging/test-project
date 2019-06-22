package com.sd.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.entity.ApplyState;
import com.sd.demo.entity.Place;
@Repository
public interface ApplyStateDao  extends JpaRepository<ApplyState,Long>{

}
