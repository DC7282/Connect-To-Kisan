package com.dhiraj.entity;

import java.text.SimpleDateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orderplaced")
public class OrderPlaced {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="userid")
	private int userid;

	@Column(name="billingid")
	private int billingid;

	@Column(name="productid")
	private int productid;

	@Column(name="title", length=100, nullable=false)
	private String title;

	@Column(name="mrp", length=10, nullable=false)
	private float mrp;

	@Column(name="sellingprice", length=10, nullable=false)
	private float sellingprice;

	@Column(name="quantity", length=10)
	private int quantity;

	@Column(name="deliverystatus")
	private int deliverystatus=1;

	@Column(name="availability", length=20, nullable=false)
	private int availability;

	@Column(name="department", length=20, nullable=false)
	private int department;

	@Column(name="category", length=10, nullable=false)
	private int category;

	@Column(name="productimage",length=300)
	private String productimage;

	@Column(name="timestamp", length=30)
	private String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());

	public OrderPlaced() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderPlaced(int userid, int billingid, int productid, String title, float mrp, float sellingprice,
			int quantity, int deliverystatus, int availability, int department, int category, String productimage, String timeStamp) {
		super();
		this.userid = userid;
		this.billingid = billingid;
		this.productid = productid;
		this.title = title;
		this.mrp = mrp;
		this.sellingprice = sellingprice;
		this.quantity = quantity;
		this.deliverystatus = deliverystatus;
		this.availability = availability;
		this.department = department;
		this.category = category;
		this.productimage = productimage;
		this.timeStamp = timeStamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBillingid() {
		return billingid;
	}

	public void setBillingid(int billingid) {
		this.billingid = billingid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getMrp() {
		return mrp;
	}

	public void setMrp(float mrp) {
		this.mrp = mrp;
	}

	public float getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(float sellingprice) {
		this.sellingprice = sellingprice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDeliverystatus() {
		return deliverystatus;
	}

	public void setDeliverystatus(int deliverystatus) {
		this.deliverystatus = deliverystatus;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getProductimage() {
		return productimage;
	}

	public void setProductimage(String productimage) {
		this.productimage = productimage;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
