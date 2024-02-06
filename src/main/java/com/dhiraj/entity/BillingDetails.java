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
@Table(name="billingdetails")
public class BillingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="userid")
	private int userid;

	@Column(name="firstname", length=30)
	private String firstname;

	@Column(name="lastname", length=30)
	private String lastname;

	@Column(name="address", length=100)
	private String address;

	@Column(name="landmark", length=100)
	private String landmark;

	@Column(name="city", length=30)
	private String city;

	@Column(name="state", length=30)
	private String state;

	@Column(name="pastcode")
	private int pastcode;

	@Column(name="phone", length=13)
	private String phone;

	@Column(name="email", length=50)
	private String email;

	@Column(name="totalbillingamount")
	private float totalbillingamount;

	@Column(name="timestamp", length=30)
	private String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());

	@Column(name="deliverystatus")
	private int deliverystatus=1;

	@OneToMany(cascade=CascadeType.ALL, targetEntity=com.dhiraj.entity.OrderPlaced.class)
	@JoinColumn(name="billingid")
	private Set<OrderPlaced> billing;

	public BillingDetails() {
		super();
	}

	public BillingDetails(int userid, String firstname, String lastname, String address, String landmark,
			String city, String state, int pastcode, String phone, String email, float totalbillingamount,
			String timeStamp, int deliverystatus, Set<OrderPlaced> billing) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.pastcode = pastcode;
		this.phone = phone;
		this.email = email;
		this.totalbillingamount = totalbillingamount;
		this.timeStamp = timeStamp;
		this.deliverystatus = deliverystatus;
		this.billing = billing;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getPastcode() {
		return pastcode;
	}

	public void setPastcode(int pastcode) {
		this.pastcode = pastcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getTotalbillingamount() {
		return totalbillingamount;
	}

	public void setTotalbillingamount(float totalbillingamount) {
		this.totalbillingamount = totalbillingamount;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Set<OrderPlaced> getBilling() {
		return billing;
	}

	public void setBilling(Set<OrderPlaced> billing) {
		this.billing = billing;
	}

	public int getDeliverystatus() {
		return deliverystatus;
	}

	public void setDeliverystatus(int deliverystatus) {
		this.deliverystatus = deliverystatus;
	}

}
