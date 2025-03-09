package com.eng.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.eng.backend.dto.StudentDTO;
import com.eng.backend.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	@Mapping(target = "cityId", source = "city.id")
	@Mapping(target = "cityName", source = "city.name")
	public StudentDTO toStudentDTO(Student student);

	@Mapping(target = "city.id", source = "studentDTO.cityId")
	@Mapping(target = "city.name", source = "studentDTO.cityName")
	public Student toStudentEntity(StudentDTO studentDTO);

	public List<StudentDTO> toStudentDTOs(List<Student> students);

}
