package com.eng.backend.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ExamPeriodDTO {
	
	private Integer id;
	
	@NotNull(message = "Name is required")
	@Size(min = 3, message = "Name must have min 3 characters")
	private String name;
	
	@NotNull(message = "Exam start name is required")
	private LocalDateTime examstart;
	
	@NotNull(message = "Exam end is required")
	private LocalDateTime examend;
	
	@NotNull(message = "Status is required")
	private Boolean status;
	
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
