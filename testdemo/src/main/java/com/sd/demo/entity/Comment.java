package com.sd.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="editor")
	private SysUser editor;
	
	@ManyToOne
	@JoinColumn(name="place")
	private Place place;
	
	@Column(length = 500)
	private String content;
	
	@Column
	private Timestamp time;
	
}
