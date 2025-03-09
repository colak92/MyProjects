package com.eng.backend.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class ExamRegDTO {
	
	private Integer id;
	
	private Integer grade;
	
	private Boolean passed;
	
	private String comment;
	
	@NotNull(message = "Exam registration date is required")
	private LocalDateTime examregdate; 
	
	@NotNull(message = "Student id is required")
	private Integer studentId;
	
	private String studentFirstName;
	
	private String studentLastName;
	
	@NotNull(message = "Exam id is required")
	private Integer examId;
	
	private String examName;
		
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
	
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	
	public String getExamName() {
		return examName;
	}
	
	public void setExamName(String examName) {
		this.examName = examName;
	}
	
}
