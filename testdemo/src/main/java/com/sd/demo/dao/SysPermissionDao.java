package com.sd.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sd.demo.entity.SysPermission;

public interface SysPermissionDao extends JpaRepository<SysPermission, Long> {

}
