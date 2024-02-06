package com.dhiraj.entity;

import java.text.SimpleDateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="setting")
public class Setting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="email", length=100)
	private String email;
	
	@Column(name="contact", length=10)
	private String contact;
	
	@Column(name="address", length=255)
	private String address;
	
	@Column(name="facebooklink", length=200)
	private String facebooklink;

	@Column(name="instagramlink", length=200)
	private String instagramlink;
	
	@Column(name="xlink", length=200)
	private String xlink;
	
	@Column(name="linkedinlink", length=200)
	private String linkedinlink;
	
	@Column(name="pinterestlink", length=200)
	private String pinterestlink;
	
	@Column(name="pagesize")
	private int pagesize;
	
	@Column(name="minshopingamount")
	private float minshopingamount;
	
	@Column(name="charges")
	private float charges;

	@Column(name="timestamp", length=30)
	private String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());

	public Setting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Setting(String email, String contact, String address, String facebooklink, String instagramlink,
			String xlink, String linkedinlink, String pinterestlink, int pagesize, float minshopingamount,
			float charges, String timeStamp) {
		super();
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.facebooklink = facebooklink;
		this.instagramlink = instagramlink;
		this.xlink = xlink;
		this.linkedinlink = linkedinlink;
		this.pinterestlink = pinterestlink;
		this.pagesize = pagesize;
		this.minshopingamount = minshopingamount;
		this.charges = charges;
		this.timeStamp = timeStamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFacebooklink() {
		return facebooklink;
	}

	public void setFacebooklink(String facebooklink) {
		this.facebooklink = facebooklink;
	}

	public String getInstagramlink() {
		return instagramlink;
	}

	public void setInstagramlink(String instagramlink) {
		this.instagramlink = instagramlink;
	}

	public String getXlink() {
		return xlink;
	}

	public void setXlink(String xlink) {
		this.xlink = xlink;
	}

	public String getPinterestlink() {
		return pinterestlink;
	}

	public void setPinterestlink(String pinterestlink) {
		this.pinterestlink = pinterestlink;
	}

	public float getMinshopingamount() {
		return minshopingamount;
	}

	public void setMinshopingamount(float minshopingamount) {
		this.minshopingamount = minshopingamount;
	}

	public float getCharges() {
		return charges;
	}

	public void setCharges(float charges) {
		this.charges = charges;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getLinkedinlink() {
		return linkedinlink;
	}

	public void setLinkedinlink(String linkedinlink) {
		this.linkedinlink = linkedinlink;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

}
