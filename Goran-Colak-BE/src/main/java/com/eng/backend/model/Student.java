package com.eng.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "indexnumber")
	private String indexnumber;

	@Column(name = "indexyear")
	private Integer indexyear;

	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "currentyearofstudy")
	private Integer currentyearofstudy;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	private City city;
	
	// constructors
	
	public Student() {}

	public Student(Integer id, String indexnumber, Integer indexyear, String firstname, String lastname, String email,
			String address, Integer postalcode, Integer currentyearofstudy) {
		super();
		this.id = id;
		this.indexnumber = indexnumber;
		this.indexyear = indexyear;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.currentyearofstudy = currentyearofstudy;
	}
	
	// getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndexnumber() {
		return indexnumber;
	}

	public void setIndexnumber(String indexnumber) {
		this.indexnumber = indexnumber;
	}

	public Integer getIndexyear() {
		return indexyear;
	}

	public void setIndexyear(Integer indexyear) {
		this.indexyear = indexyear;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getCurrentyearofstudy() {
		return currentyearofstudy;
	}

	public void setCurrentyearofstudy(Integer currentyearofstudy) {
		this.currentyearofstudy = currentyearofstudy;
	}
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
}
