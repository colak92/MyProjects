package com.eng.backend.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentDTO {
	
	private Integer id;
	
	@NotNull(message = "Index number is required")
	@Size(min = 4, max = 4, message = "Index number must have exact 4 character")
	private String indexnumber;

	@NotNull(message = "Index year is required")
	@Min(value = 2000)
	@Max(value = 2100)
	private Integer indexyear;

	@NotNull(message = "First name is required")
	@Size(min = 3, message = "First name must have min 3 characters")
	private String firstname;
	
	@NotNull(message = "Last name is required")
	@Size(min = 3, message = "Last name must have min 3 characters")
	private String lastname;
	
	@NotNull(message = "JMBG is required")
	private Long jmbg;
	
	@Email(message = "Email must be valid")
	private String email;
	
	private String address;
	
	@NotNull(message = "Current year of study is required")
	private Integer currentyearofstudy;
	
	private Integer cityId;
	
	private String cityName;
	
	// getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndexnumber() {
		return indexnumber;
	}

	public void setIndexnumber(String indexnumber) {
		this.indexnumber = indexnumber;
	}

	public Integer getIndexyear() {
		return indexyear;
	}

	public void setIndexyear(Integer indexyear) {
		this.indexyear = indexyear;
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

	public Long getJmbg() {
		return jmbg;
	}

	public void setJmbg(Long jmbg) {
		this.jmbg = jmbg;
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

	public Integer getCurrentyearofstudy() {
		return currentyearofstudy;
	}

	public void setCurrentyearofstudy(Integer currentyearofstudy) {
		this.currentyearofstudy = currentyearofstudy;
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
	

}
