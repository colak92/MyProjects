package com.eng.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.eng.backend.dto.ProfessorDTO;
import com.eng.backend.model.Professor;

@Mapper(componentModel = "spring", uses = { SubjectMapper.class })
public interface ProfessorMapper {

	@Mapping(target = "cityId", source = "city.id")
	@Mapping(target = "cityName", source = "city.name")

	@Mapping(target = "titleId", source = "title.id")
	@Mapping(target = "titleName", source = "title.name")
	public ProfessorDTO toProfessorDTO(Professor professor);

	@Mapping(target = "city.id", source = "professorDTO.cityId")
	@Mapping(target = "city.name", source = "professorDTO.cityName")

	@Mapping(target = "title.id", source = "professorDTO.titleId")
	@Mapping(target = "title.name", source = "professorDTO.titleName")
	public Professor toProfessorEntity(ProfessorDTO professorDTO);

	public List<ProfessorDTO> toProfessorDTOs(List<Professor> professors);
}
