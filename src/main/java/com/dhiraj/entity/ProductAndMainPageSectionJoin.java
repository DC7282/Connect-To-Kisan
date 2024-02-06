package com.dhiraj.entity;

import java.text.SimpleDateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="productandmainpagesectionjoin")
public class ProductAndMainPageSectionJoin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", length=10)
	private int id;

	@Column(name="product_id", length=50)
	private int product;

	@Column(name="mainpagesections_id", length=50)
	private int mainpagesections;

	@Column(name="timestamp", length=30)
	private String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());

	public ProductAndMainPageSectionJoin() {
	}

	public ProductAndMainPageSectionJoin(int product, int mainpagesections, String timeStamp) {
		super();
		this.product = product;
		this.mainpagesections = mainpagesections;
		this.timeStamp = timeStamp;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduct() {
		return product;
	}

	public void setProduct(int product) {
		this.product = product;
	}

	public int getMainpagesections() {
		return mainpagesections;
	}

	public void setMainpagesections(int mainpagesections) {
		this.mainpagesections = mainpagesections;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
