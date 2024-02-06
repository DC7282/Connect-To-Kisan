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
@Table(name="userregisteration")
public class UserRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="uid", length=10)
	private int id;

	@Column(name="firstname", length=20)
	private String firstname;

	@Column(name="lastname", length=20)
	private String lastname;

	@Column(name="email", length=50)
	private String email;

	@Column(name="contact", length=10)
	private String contact;

	@Column(name="password", length=16)
	private String password;

	@Column(name="status")
	private int status=1;

	@OneToMany(cascade=CascadeType.ALL, targetEntity=com.dhiraj.entity.OrderPlaced.class)
	@JoinColumn(name="userid")
	private Set<OrderPlaced> orderplaced;

	@OneToMany(cascade=CascadeType.ALL, targetEntity=com.dhiraj.entity.BillingDetails.class)
	@JoinColumn(name="userid")
	private Set<BillingDetails> billing;

	@Column(name="timestamp", length=30)
	private String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm.ss").format(new java.util.Date());

	public UserRegistration() {
		super();
	}

	public UserRegistration(String firstname, String lastname, String email, String contact, String password,
			int status, Set<OrderPlaced> orderplaced, Set<BillingDetails> billing, String timeStamp) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.contact = contact;
		this.password = password;
		this.status = status;
		this.orderplaced = orderplaced;
		this.billing = billing;
		this.timeStamp = timeStamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<BillingDetails> getBilling() {
		return billing;
	}

	public void setBilling(Set<BillingDetails> billing) {
		this.billing = billing;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		status = status;
	}

}
