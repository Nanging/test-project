package com.sd.demo.entity;

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

@Entity
public class Place {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Long id;
	
	@Column(length = 64)
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ownerid")
	private SysUser owner;
	
	@Column(length = 64)
	private String type;
//	@ManyToMany(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST})
//    @JoinTable(joinColumns = {@JoinColumn(name = "placeid")}, inverseJoinColumns = {@JoinColumn(name = "typeid")})
//	private Set<PlaceType> types = new HashSet<PlaceType>();
	
	@Column(length = 500)
	private String description;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(length = 64)
	private int size;
	
	@Column(length = 64)
	private int affordNumber;

	@Column(length = 64)
	private int roomNumber;
	
	@Column(length = 64)
	private String location;
	
	@Column(length = 64)
	private int price;
	
	@OneToMany(mappedBy="place",cascade = {CascadeType.ALL})
	private Set<PlaceImage> images = new HashSet<PlaceImage>();
	
	@OneToMany(mappedBy="place",cascade = {CascadeType.ALL})
	private Set<Apply> applies = new HashSet<Apply>();
	
	@OneToMany(mappedBy="place",cascade = {CascadeType.ALL})
	private Set<Comment> comments = new HashSet<Comment>();
	
	@ManyToMany(mappedBy = "placeList")
    private Set<SysUser> userList = new HashSet<SysUser>();
	
	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAffordNumber() {
		return affordNumber;
	}

	public void setAffordNumber(int affordNumber) {
		this.affordNumber = affordNumber;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Set<Apply> getApplies() {
		return applies;
	}

	public void setApplies(Set<Apply> applies) {
		this.applies = applies;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<SysUser> getUserList() {
		return userList;
	}

	public void setUserList(Set<SysUser> userList) {
		this.userList = userList;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDetail() {
		return description;
	}

	public void setDetail(String detail) {
		this.description = detail;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public SysUser getOwner() {
		return owner;
	}

	public void setOwner(SysUser owner) {
		this.owner = owner;
	}


	public Set<PlaceImage> getImages() {
		return images;
	}

	public void setImages(Set<PlaceImage> images) {
		this.images = images;
	}

	
}
