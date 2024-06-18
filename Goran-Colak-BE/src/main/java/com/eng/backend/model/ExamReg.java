package com.eng.backend.model;

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
	
	@Column(name = "comment")
	private String comment;
	
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
	private Exam exam;
	
	// constructors
	
	public ExamReg() {
		// TODO Auto-generated constructor stub
	}
	
	public ExamReg(Integer id, String comment) {
		super();
		this.id = id;
		this.comment = comment;
	}
	
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
