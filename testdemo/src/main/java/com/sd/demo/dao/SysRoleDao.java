package com.sd.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sd.demo.entity.SysRole;
import com.sd.demo.entity.SysUser;

public interface SysRoleDao extends JpaRepository<SysRole, Long> {
	
	public SysRole findByCode(String code);
}
