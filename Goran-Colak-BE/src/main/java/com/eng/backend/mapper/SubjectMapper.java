package com.eng.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.eng.backend.dto.SubjectDTO;
import com.eng.backend.model.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
	
	public SubjectDTO toSubjectDTO(Subject subject);
	
	public Subject toSubjectEntity(SubjectDTO subjectDTO);
	
	public List<SubjectDTO> toSubjectDTOs(List<Subject> subjects);
	
}
