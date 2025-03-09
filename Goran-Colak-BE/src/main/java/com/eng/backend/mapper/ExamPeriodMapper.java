package com.eng.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.eng.backend.dto.ExamPeriodDTO;
import com.eng.backend.model.ExamPeriod;

@Mapper(componentModel = "spring")
public interface ExamPeriodMapper {
	
	public ExamPeriodDTO toExamPeriodDTO(ExamPeriod examPeriod);
	
	public ExamPeriod toExamPeriodEntity(ExamPeriodDTO examPeriodDTO);
	
	public List<ExamPeriodDTO> toExamPeriodDTOs(List<ExamPeriod> examperiods);

}
