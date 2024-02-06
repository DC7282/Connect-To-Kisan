package com.dhiraj.entity;

import java.text.SimpleDateFormat;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id", length=10)
	private int id;

	@Column(name="departmentid", length=10)
	private int departmentid;

	@Column(name="categoryname", length=20)
	private String categoryname;

	@Column(name="timestamp", length=30)
	private String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());

	@OneToMany(cascade=CascadeType.ALL, targetEntity=com.dhiraj.entity.Product.class)
	@JoinColumn(name="category")
	private Set<Product> Product;


	public Category() {
	}



	public Category(int departmentid, String categoryname, String timeStamp, Set<Product> product) {
		super();
		this.departmentid = departmentid;
		this.categoryname = categoryname;
		this.timeStamp = timeStamp;
		Product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Set<Product> getProduct() {
		return Product;
	}

	public void setProduct(Set<Product> product) {
		Product = product;
	}
}
