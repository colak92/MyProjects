package com.eng.backend.model;

import java.time.LocalDateTime;
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
@Table(name = "examperiods")
public class ExamPeriod {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "examstart")
	private LocalDateTime examstart;
	
	@Column(name = "examend")
	private LocalDateTime examend;
	
	@Column(name = "status")
	private Boolean status;
	
	@OneToMany(cascade =  CascadeType.ALL, mappedBy = "examperiod")
	private List<Exam> examlist;
	
	public ExamPeriod() {
		// TODO Auto-generated constructor stub
	}

	public ExamPeriod(Integer id, String name, LocalDateTime examstart, LocalDateTime examend, Boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.examstart = examstart;
		this.examend = examend;
		this.status = status;
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

	public LocalDateTime getExamstart() {
		return examstart;
	}

	public void setExamstart(LocalDateTime examstart) {
		this.examstart = examstart;
	}

	public LocalDateTime getExamend() {
		return examend;
	}

	public void setExamend(LocalDateTime examend) {
		this.examend = examend;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
