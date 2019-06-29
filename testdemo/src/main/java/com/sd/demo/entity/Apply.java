package com.sd.demo.entity;

import java.sql.Timestamp;
import java.util.HashSet;
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
public class Apply {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="placeid")
	private Place place;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="applier")
	private SysUser applier;

	@Column
	private Timestamp startTime;
	
	@Column
	private int time;
	
	@Column
	private String unit;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="stateid")
	private ApplyState state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public SysUser getApplier() {
		return applier;
	}

	public void setApplier(SysUser applier) {
		this.applier = applier;
	}

	public ApplyState getState() {
		return state;
	}

	public void setState(ApplyState state) {
		this.state = state;
	}
	
}
