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
@Table(name = "examregs")
public class ExamReg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "grade")
	private Integer grade;
	
	@Column(name = "passed")
	private Boolean passed;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "examregdate")
	private LocalDateTime examregdate; 
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student student;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "exam_id", referencedColumnName = "id")
	private Exam exam;
	
	// constructors
	
	public ExamReg() {}
	
	public ExamReg(Integer id, Integer grade, Boolean passed, String comment, LocalDateTime examregdate, Student student, Exam exam) {
		super();
		this.id = id;
		this.grade = grade;
		this.passed = passed;
		this.comment = comment;
		this.examregdate = examregdate;
		this.student = student;
		this.exam = exam;
	}

	// getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public LocalDateTime getExamregdate() {
		return examregdate;
	}
	
	public void setExamregdate(LocalDateTime examregdate) {
		this.examregdate = examregdate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
}
