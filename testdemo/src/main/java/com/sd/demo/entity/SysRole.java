package com.sd.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class SysRole implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Long id;

	@Column(length = 64)
	private String code;
	
	@Column(length = 64)
	private String name;
		
	@ManyToMany(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST})
	@JoinTable(joinColumns = {@JoinColumn(name = "roleid")}, inverseJoinColumns = {@JoinColumn(name = "permissionid")})
	private Set<SysPermission> permissionList = new HashSet<SysPermission>();


	@ManyToMany(mappedBy = "roleList")
    private Set<SysUser> userList = new HashSet<SysUser>();
	
	
	public SysRole(Long id, String code, String name, Set<SysPermission> permissionList, Set<SysUser> userList) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.permissionList = permissionList;
		this.userList = userList;
	}
	public SysRole() {
		
	}
	public SysRole(String code, String name) {
		this.code =code;
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Set<SysPermission> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(Set<SysPermission> permissionList) {
		this.permissionList = permissionList;
	}
	public Set<SysUser> getUserList() {
		return userList;
	}
	public void setUserList(Set<SysUser> userList) {
		this.userList = userList;
	}
	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
//	public List<Record> getOrderList() {
//		return orderList;
//	}
//	public void setOrderList(List<Record> orderList) {
//		this.orderList = orderList;
//	}

//	public List<SysPermission> getPermissionList() {
//		return permissionList;
//	}
//
//	public void setPermissionList(List<SysPermission> permissionList) {
//		this.permissionList = permissionList;
//	}

//	public SysUser getUser() {
//		return user;
//	}
//
//	public void setUser(SysUser user) {
//		this.user = user;
//	}


	
	
}
