package com.eng.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.eng.backend.dto.ExamRegDTO;
import com.eng.backend.model.ExamReg;

@Mapper(componentModel = "spring", uses = { StudentMapper.class })
public interface ExamRegMapper {
	
	@Mapping(target="studentId", source = "student.id")
	@Mapping(target="studentFirstName", source = "student.firstname")
	@Mapping(target="studentLastName", source = "student.lastname")
	
	@Mapping(target="examId", source = "exam.id")
	@Mapping(target="examName", source = "exam.name")
	public ExamRegDTO toExamRegDTO(ExamReg examReg);

	@Mapping(target="student.id", source="examRegDTO.studentId")
	@Mapping(target="student.firstname", source="examRegDTO.studentFirstName")
	@Mapping(target="student.lastname", source="examRegDTO.studentLastName")
	
	@Mapping(target="exam.id", source="examRegDTO.examId")
	@Mapping(target="exam.name", source="examRegDTO.examName")
	public ExamReg toExamRegEntity(ExamRegDTO examRegDTO);
	
	public List<ExamRegDTO> toExamRegDTOs(List<ExamReg> examregs);

}
