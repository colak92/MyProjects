package com.eng.backend.dto;

import com.eng.backend.model.Exam;

public class ExamRegDTO {
	
	private Integer id;
	
	private String comment;
	
	private Exam exam;
	
	// getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	

}
