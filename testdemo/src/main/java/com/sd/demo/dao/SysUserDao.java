package com.sd.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.entity.SysRole;
import com.sd.demo.entity.SysUser;
@Repository
public interface SysUserDao extends JpaRepository<SysUser,Long>{
	public SysUser findByUsername(String username);
	
	public SysUser save(SysUser user);

}
