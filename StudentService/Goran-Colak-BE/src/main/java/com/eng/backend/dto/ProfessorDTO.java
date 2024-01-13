package com.eng.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ProfessorDTO {
	
	private Integer id;

	@NotNull(message = "First name is required")
	@Size(min = 3, message = "First name must have min 3 characters")
	private String firstname;

	@NotNull(message = "Last name is required")
	@Size(min = 3, message = "Last name must have min 3 characters")
	private String lastname;

	@Email(message = "Email must be valid")
	private String email;

	@Size(min = 3, message = "Address must have min 3 characters")
	private String address;

	@Size(min = 9, message = "Phone must have min 9 characters")
	private String phone;

	@NotNull(message = "Reelection date is required")
	private LocalDateTime reelectiondate;

	private Integer cityId;
	
	private String cityName;
	
	@NotNull(message = "Title id is required")
	private Integer titleId;
	
	private String titleName;
	
	private List<SubjectDTO> subjectList;
	
	// getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getReelectiondate() {
		return reelectiondate;
	}

	public void setReelectiondate(LocalDateTime reelectiondate) {
		this.reelectiondate = reelectiondate;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	public List<SubjectDTO> getSubjectList() {
		return subjectList;
	}
	
	public void setSubjectList(List<SubjectDTO> subjectList) {
		this.subjectList = subjectList;
	}
	
	
}
