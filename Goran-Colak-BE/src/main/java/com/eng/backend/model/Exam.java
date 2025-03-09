package com.eng.backend.model;

import java.time.LocalDateTime;

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
@Table(name = "exams")
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "examdate")
	private LocalDateTime examdate;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "examperiod_id", referencedColumnName = "id")
	private ExamPeriod examperiod;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "subject_id", referencedColumnName = "id")
	private Subject subject;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "professor_id", referencedColumnName = "id")
	private Professor professor;
	
	// constructors

	public Exam() {}

	public Exam(Integer id, String name, LocalDateTime examdate, ExamPeriod examperiod, Subject subject, Professor professor) {
		super();
		this.id = id;
		this.name = name;
		this.examdate = examdate;
		this.examperiod = examperiod;
		this.subject = subject;
		this.professor = professor;
	}

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

	public LocalDateTime getExamdate() {
		return examdate;
	}

	public void setExamdate(LocalDateTime examdate) {
		this.examdate = examdate;
	}
	
	public ExamPeriod getExamperiod() {
		return examperiod;
	}
	
	public void setExamperiod(ExamPeriod examperiod) {
		this.examperiod = examperiod;
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
