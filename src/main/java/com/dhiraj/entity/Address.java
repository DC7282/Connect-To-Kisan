package com.dhiraj.entity;

import java.text.SimpleDateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="location", length=100)
	private String location;

	@Column(name="landmark", length=100)
	private String landmark;

	@Column(name="city", length=30)
	private String city;

	@Column(name="state", length=30)
	private String state;
	
	@Column(name="postcode")
	private int postcode;

	@Column(name="timestamp", length=30)
	private String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());

	@Column(name="userid")
	private int userid;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String location, String landmark, String city, String state, int postcode, String timeStamp,
			int userid) {
		super();
		this.location = location;
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
		this.timeStamp = timeStamp;
		this.userid = userid;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	
}
