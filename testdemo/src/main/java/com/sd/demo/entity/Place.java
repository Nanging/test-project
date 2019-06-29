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
	
	@ManyToMany(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST})
    @JoinTable(joinColumns = {@JoinColumn(name = "placeid")}, inverseJoinColumns = {@JoinColumn(name = "typeid")})
	private Set<PlaceType> types = new HashSet<PlaceType>();
	
	@Column(length = 500)
	private String description;
	
	@Column(length = 64)
	private String size;
	
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public SysUser getOwner() {
		return owner;
	}

	public void setOwner(SysUser owner) {
		this.owner = owner;
	}

	public Set<PlaceType> getTypes() {
		return types;
	}

	public void setTypes(Set<PlaceType> types) {
		this.types = types;
	}

	public Set<PlaceImage> getImages() {
		return images;
	}

	public void setImages(Set<PlaceImage> images) {
		this.images = images;
	}

	
}
