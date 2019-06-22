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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class SysPermission implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Long id;
	
	@Column(length = 32)
	private String code;
	
	@Column(length = 64)
	private String name;
	
	@Column(length = 64)
	private String url;

	@ManyToMany(mappedBy = "permissionList")
    private Set<SysRole> roleList = new HashSet<SysRole>();
    
	public SysPermission(Long id, String code, String name, String url, Set<SysRole> roleList) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.url = url;
		this.roleList = roleList;
	}

	public SysPermission() {
		// TODO Auto-generated constructor stub
	}


	public Set<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<SysRole> roleList) {
		this.roleList = roleList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
