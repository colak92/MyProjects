package com.eng.backend.request;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignupRequest {
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
	
	@NotNull
	private String roleName;
	
	private String professorFirstName;

	private String professorLastName;

	private LocalDateTime reelectionDate;
	
	private String titleName;
	
	private String indexNumber;
	
	private Integer indexYear;
	
	private String studentFirstName;
	
	private String studentLastName;
	
	private Integer currentYearoFStudy;
	
	// getters and setters
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	
	public LocalDateTime getReelectionDate() {
		return reelectionDate;
	}
	
	public void setReelectionDate(LocalDateTime reelectionDate) {
		this.reelectionDate = reelectionDate;
	}
	
	public String getTitleName() {
		return titleName;
	}
	
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public Integer getIndexYear() {
		return indexYear;
	}

	public void setIndexYear(Integer indexYear) {
		this.indexYear = indexYear;
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

	public Integer getCurrentYearoFStudy() {
		return currentYearoFStudy;
	}

	public void setCurrentYearoFStudy(Integer currentYearoFStudy) {
		this.currentYearoFStudy = currentYearoFStudy;
	}
	
}
