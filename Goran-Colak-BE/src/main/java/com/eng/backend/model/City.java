package com.eng.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "postalcode")
	private Integer postalcode;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(cascade =  CascadeType.ALL, mappedBy = "city")
	private List<Student> studentlist;
	
	@OneToMany(cascade =  CascadeType.ALL, mappedBy = "city")
	private List<Professor> professorlist;
	
	// constructors
	
	public City() {}
	
	public City(Integer id, Integer postalcode, String name) {
		super();
		this.id = id;
		this.postalcode = postalcode;
		this.name = name;
	}

	// getters and setters

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPostalcode() {
		return postalcode;
	}
	
	public void setPostalcode(Integer postalcode) {
		this.postalcode = postalcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
