package com.dhiraj.entity;

import java.text.SimpleDateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="banner")
public class Banner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="title", length=50)
	private String title;

	@Column(name="heading", length=100)
	private String heading;

	@Column(name="info", length=100)
	private String info;

	@Column(name="department")
	private int department;

	@Column(name="type")
	private int type;

	@Column(name="bannerimage", length=100)
	private String bannerimage;

	@Column(name="timestamp", length=30)
	private String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());

	public Banner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Banner(String title, String heading, String info, int department, int type, String bannerimage,
			String timeStamp) {
		super();
		this.title = title;
		this.heading = heading;
		this.info = info;
		this.department = department;
		this.type = type;
		this.bannerimage = bannerimage;
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

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getBannerimage() {
		return bannerimage;
	}

	public void setBannerimage(String bannerimage) {
		this.bannerimage = bannerimage;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
