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
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", length=10, nullable=false)
	private int id;

	@Column(name="title", length=100, nullable=false)
	private String title;

	@Column(name="discription", length=1000, nullable=false)
	private String discription;

	@Column(name="mrp", length=10, nullable=false)
	private float mrp;

	@Column(name="sellingprice", length=10, nullable=false)
	private float sellingprice;

	@Column(name="availability", length=20, nullable=false)
	private int availability;

	@Column(name="department", length=20, nullable=false)
	private int department;

	@Column(name="category", length=10, nullable=false)
	private int category;

	@Column(name="productimage",length=300)
	private String productimage;

	@Column(name="status")
	private int status;

	@OneToMany(cascade=CascadeType.ALL, targetEntity=com.dhiraj.entity.ProductAndMainPageSectionJoin.class)
	@JoinColumn(name="product_id")
	private Set<ProductAndMainPageSectionJoin> Product;

	@OneToMany(cascade=CascadeType.ALL, targetEntity=com.dhiraj.entity.OrderPlaced.class)
	@JoinColumn(name="productid")
	private Set<OrderPlaced> orderplaced;

	@Column(name="timestamp", length=30)
	private String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());

	public Product() {
	}

	public Product(String title, String discription, float mrp, float sellingprice, int availability, int department,
			int category, String productimage, int status, Set<ProductAndMainPageSectionJoin> product,
			Set<OrderPlaced> orderplaced, String timeStamp) {
		super();
		this.title = title;
		this.discription = discription;
		this.mrp = mrp;
		this.sellingprice = sellingprice;
		this.availability = availability;
		this.department = department;
		this.category = category;
		this.productimage = productimage;
		this.status = status;
		Product = product;
		this.orderplaced = orderplaced;
		this.timeStamp = timeStamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
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

	public Set<ProductAndMainPageSectionJoin> getProduct() {
		return Product;
	}

	public void setProduct(Set<ProductAndMainPageSectionJoin> product) {
		Product = product;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Set<OrderPlaced> getOrderplaced() {
		return orderplaced;
	}

	public void setOrderplaced(Set<OrderPlaced> orderplaced) {
		this.orderplaced = orderplaced;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
