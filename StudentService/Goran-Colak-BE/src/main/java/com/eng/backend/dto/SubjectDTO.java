package com.eng.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubjectDTO {
	
	private Integer id;
	
	@NotNull(message = "Name is required")
	@Size(min = 3, message = "Name must have min 3 characters")
	private String name;
	
	private String description;
	
	@NotNull(message = "Year of study is required")
	private Integer yearofstudy;
	
	@NotNull(message = "Number of ESP is required")
	private Integer noofesp;
	
	@NotNull(message = "Semester is required")
	private String semester;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYearofstudy() {
		return yearofstudy;
	}

	public void setYearofstudy(Integer yearofstudy) {
		this.yearofstudy = yearofstudy;
	}

	public Integer getNoofesp() {
		return noofesp;
	}

	public void setNoofesp(Integer noofesp) {
		this.noofesp = noofesp;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

}
