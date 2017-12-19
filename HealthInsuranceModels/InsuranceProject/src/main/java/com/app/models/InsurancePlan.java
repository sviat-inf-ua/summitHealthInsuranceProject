package com.app.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="InsurancePlan")
public class InsurancePlan implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="insurancePlan_id")
	private int id;
	@Column
	private String name;
	@Column
	private double price;
	@Column
	private double amountCovered;
	@Column
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Plan_Benefits", joinColumns = { @JoinColumn (name = "InsurancePlan_ID") }, inverseJoinColumns = { @JoinColumn (name = "planBenefit_ID") })
	private List<PlanBenefits> planBenefits;
	
	public List<PlanBenefits> getPlanBenefits() {
		return planBenefits;
	}
	public void setPlanBenefits(List<PlanBenefits> planBenefits) {
		this.planBenefits = planBenefits;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmountCovered() {
		return amountCovered;
	}
	public void setAmountCovered(double amountCovered) {
		this.amountCovered = amountCovered;
	}
	public List<Customer> getCustomer() {
		return customer;
	}
	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
	@Column
	@OneToMany(mappedBy = "plan")
	private List<Customer> customer;
	public InsurancePlan(int id, String name, double price, double amountCovered, List<Customer> customer) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.amountCovered = amountCovered;
		this.customer = customer;
	}
	public InsurancePlan() {
		super();
	}
	
	
}
