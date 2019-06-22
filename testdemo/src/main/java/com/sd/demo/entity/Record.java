package com.sd.demo.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Record {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userid",referencedColumnName="id")
	private SysUser borrower;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="productid",referencedColumnName="id")
	private Product product;
	
	private Timestamp starttime;
	
	private Timestamp endtime;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="recordstateid")
	private RecordState state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public SysUser getBorrower() {
		return borrower;
	}

	public void setBorrower(SysUser borrower) {
		this.borrower = borrower;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public RecordState getState() {
		return state;
	}

	public void setState(RecordState state) {
		this.state = state;
	}
	
}
