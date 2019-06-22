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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class SysUser  implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Long id;
	
	@Column(length = 64)
	private String username;
	
	@Column(length = 64)
	private String password;
	
	@Column(length = 64)
	private String phonenumber;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST})
    @JoinTable(joinColumns = {@JoinColumn(name = "userid")}, inverseJoinColumns = {@JoinColumn(name = "roleid")})
	private Set<SysRole> roleList = new HashSet<SysRole>();

	@OneToMany(mappedBy="borrower")
	private Set<Record> records = new HashSet<>(); 
	
	@OneToMany(mappedBy="owner")
	private Set<Place> places = new HashSet<>(); 
	
	@OneToMany(mappedBy="applier")
	private Set<Apply> applies = new HashSet<>(); 
	
	public SysUser(String username, String password) {
		this.username = username;
		this.password = password;
		// TODO Auto-generated constructor stub
	}

	public SysUser() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public void addRole(SysRole role) {
		this.roleList.add(role);
	}
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", username=" + username + ", password=" + password + ", roleList=" + roleList
				+ "]";
	}

	public Set<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<SysRole> roleList) {
		this.roleList = roleList;
	}

	public Set<Record> getRecords() {
		return records;
	}

	public void setRecords(Set<Record> records) {
		this.records = records;
	}

	public Set<Place> getPlaces() {
		return places;
	}

	public void setPlaces(Set<Place> places) {
		this.places = places;
	}

}
