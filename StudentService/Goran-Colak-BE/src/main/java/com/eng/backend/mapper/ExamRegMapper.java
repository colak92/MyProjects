package com.eng.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.eng.backend.dto.ExamRegDTO;
import com.eng.backend.model.ExamReg;

@Mapper(componentModel = "spring")
public interface ExamRegMapper {
	
	public ExamRegDTO toExamRegDTO(ExamReg examReg);

	public ExamReg toExamRegEntity(ExamRegDTO examRegDTO);
	
	public List<ExamRegDTO> toExamRegDTOs(List<ExamReg> examregs);

}
