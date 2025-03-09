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
@Table(name = "subjects")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "yearofstudy")
	private Integer yearofstudy;

	@Column(name = "numberofects")
	private Integer numberofects;

	@Column(name = "semester")
	private String semester;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
	private List<Exam> examlist;

	// constructors

	public Subject() {}

	public Subject(Integer id, String name, String description, Integer yearofstudy, Integer numberofects, String semester) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.yearofstudy = yearofstudy;
		this.numberofects = numberofects;
		this.semester = semester;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYearofstudy() {
		return yearofstudy;
	}

	public void setYearofstudy(Integer yearofstudy) {
		this.yearofstudy = yearofstudy;
	}

	public Integer getNumberofects() {
		return numberofects;
	}
	
	public void setNumberofects(Integer numberofects) {
		this.numberofects = numberofects;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

}
