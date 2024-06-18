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
@Table(name = "titles")
public class Title {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(cascade =  CascadeType.ALL, mappedBy = "title")
	private List<Professor> professorlist;
	
	// constructors
	
	public Title() {
		// TODO Auto-generated constructor stub
	}

	public Title(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	// getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
