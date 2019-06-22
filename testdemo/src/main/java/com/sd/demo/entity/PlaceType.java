package com.sd.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
public class PlaceType implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Long id;
	
	@Column(length = 32)
	private String type;
	
	@ManyToMany(mappedBy = "types")
    private Set<Place> placeList = new HashSet<Place>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Place> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(Set<Place> placeList) {
		this.placeList = placeList;
	}


}
