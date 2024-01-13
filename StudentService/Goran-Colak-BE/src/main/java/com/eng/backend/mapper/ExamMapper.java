package com.eng.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.eng.backend.dto.ExamDTO;
import com.eng.backend.model.Exam;

@Mapper(componentModel = "spring")
public interface ExamMapper {
	
	@Mapping(target="examPeriodId", source = "examperiod.id")
	@Mapping(target="examPeriodName", source = "examperiod.name")
	
	@Mapping(target="subjectId", source = "subject.id")
	@Mapping(target="subjectName", source = "subject.name")
	
	@Mapping(target="professorId", source = "professor.id")
	@Mapping(target="professorFirstName", source = "professor.firstname")
	@Mapping(target="professorLastName", source = "professor.lastname")
	
	@Mapping(target="studentId", source = "student.id")
	@Mapping(target="studentFirstName", source = "student.firstname")
	@Mapping(target="studentLastName", source = "student.lastname")
	public ExamDTO toExamDTO(Exam exam);
	
	@Mapping(target="examperiod.id", source="examDTO.examPeriodId")
	@Mapping(target="examperiod.name", source="examDTO.examPeriodName")
	
	@Mapping(target="subject.id", source="examDTO.subjectId")
	@Mapping(target="subject.name", source="examDTO.subjectName")
	
	@Mapping(target="professor.id", source="examDTO.professorId")
	@Mapping(target="professor.firstname", source="examDTO.professorFirstName")
	@Mapping(target="professor.lastname", source="examDTO.professorLastName")
	
	@Mapping(target="student.id", source="examDTO.studentId")
	@Mapping(target="student.firstname", source="examDTO.studentFirstName")
	@Mapping(target="student.lastname", source="examDTO.studentLastName")
	public Exam toExamEntity(ExamDTO examDTO);
	
	public List<ExamDTO> toExamDTOs(List<Exam> exams);

}
