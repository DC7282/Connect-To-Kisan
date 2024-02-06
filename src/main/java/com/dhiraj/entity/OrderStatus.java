package com.dhiraj.entity;

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
@Table(name="orderstatus")
public class OrderStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="status", length=30)
	private String status;

	@OneToMany(cascade=CascadeType.ALL, targetEntity=com.dhiraj.entity.BillingDetails.class)
	@JoinColumn(name="deliverystatus")
	private Set<BillingDetails> deliverystatus;

	public OrderStatus() {
		super();
	}

	public OrderStatus(String status, Set<BillingDetails> deliverystatus) {
		super();
		this.status = status;
		this.deliverystatus = deliverystatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<BillingDetails> getBillingstatus() {
		return deliverystatus;
	}

	public void setBillingstatus(Set<BillingDetails> deliverystatus) {
		this.deliverystatus = deliverystatus;
	}
}
