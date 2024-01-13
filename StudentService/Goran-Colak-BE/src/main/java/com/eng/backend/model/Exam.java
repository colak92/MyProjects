package com.eng.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Column(name = "grade")
	private Integer grade;
	
	@Column(name = "passed")
	private Boolean passed;
	
	@Column(name = "registered")
	private Boolean registered;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "examperiod_id", referencedColumnName = "id")
	private ExamPeriod examperiod;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "subject_id", referencedColumnName = "id")
	private Subject subject;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "professor_id", referencedColumnName = "id")
	private Professor professor;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student student;
	
	@OneToMany(mappedBy = "exam")
    private List<ExamReg> examregs;

	// constructors

	public Exam() {
		// TODO Auto-generated constructor stub
	}

	public Exam(Integer id, String name, LocalDateTime examdate, Integer grade, Boolean passed, Boolean registered) {
		super();
		this.id = id;
		this.name = name;
		this.examdate = examdate;
		this.grade = grade;
		this.passed = passed;
		this.registered = registered;
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
	
	public Boolean getRegistered() {
		return registered;
	}
	
	public void setRegistered(Boolean registered) {
		this.registered = registered;
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
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}

}
