package com.eng.backend.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ExamDTO {
	
	private Integer id;

	@NotNull(message = "Name is required")
	@Size(min = 3, message = "Name must have min 3 characters")
	private String name;

	@NotNull(message = "Exam date is required")
	private LocalDateTime examdate;
	
	@NotNull(message = "Exam period id is required")
	private Integer examPeriodId;
	
	private String examPeriodName;

	@NotNull(message = "Subject id is required")
	private Integer subjectId;
	
	private String subjectName;
	
	@NotNull(message = "Professor id is required")
	private Integer professorId;
	
	private String professorFirstName;
	
	private String professorLastName;

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

	public LocalDateTime getExamdate() {
		return examdate;
	}

	public void setExamdate(LocalDateTime examdate) {
		this.examdate = examdate;
	}

	public Integer getExamPeriodId() {
		return examPeriodId;
	}

	public void setExamPeriodId(Integer examPeriodId) {
		this.examPeriodId = examPeriodId;
	}

	public String getExamPeriodName() {
		return examPeriodName;
	}

	public void setExamPeriodName(String examPeriodName) {
		this.examPeriodName = examPeriodName;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Integer professorId) {
		this.professorId = professorId;
	}

	public String getProfessorFirstName() {
		return professorFirstName;
	}

	public void setProfessorFirstName(String professorFirstName) {
		this.professorFirstName = professorFirstName;
	}

	public String getProfessorLastName() {
		return professorLastName;
	}

	public void setProfessorLastName(String professorLastName) {
		this.professorLastName = professorLastName;
	}

}
