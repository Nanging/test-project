package com.sd.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.entity.Record;
import com.sd.demo.entity.SysUser;
@Repository
public interface RecordDao  extends JpaRepository<Record,Long>{

	Page<Record> findByBorrower(SysUser borrower, Pageable pageable);
}
