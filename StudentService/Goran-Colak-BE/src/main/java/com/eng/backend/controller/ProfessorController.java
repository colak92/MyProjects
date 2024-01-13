package com.eng.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eng.backend.dto.ProfessorDTO;
import com.eng.backend.model.Professor;
import com.eng.backend.service.ProfessorService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;

	// get all professors - rest api

	@GetMapping("/professors-list")
	public ResponseEntity<List<ProfessorDTO>> getAllProfessors() {
		return professorService.getProfessors();
	}

	// get all professors sort and pagination - rest api

	@GetMapping("/professors")
	public ResponseEntity<Map<String, Object>> getAllProfessorsPageAndSort(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size, 
			@RequestParam String sortBy,
			@RequestParam String sortDir) {
		
		if (sortBy.isEmpty()) {
			sortBy = "id";
		}
		
		if (sortDir.isEmpty()) {
			sortDir = "asc";
		}

		try {
			List<Professor> professors = new ArrayList<Professor>();
			Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
			Pageable pagingSort = PageRequest.of(page, size, sort);

			Page<Professor> pageProfessors;
			pageProfessors = professorService.getPageProfessors(pagingSort);
			professors = pageProfessors.getContent();

			if (professors.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("professors", professors);
			response.put("currentPage", pageProfessors.getNumber());
			response.put("totalItems", pageProfessors.getTotalElements());
			response.put("totalPages", pageProfessors.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get professor by id - rest api

	@GetMapping("/professors/{id}")
	public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable("id") Integer id) {
		return professorService.getProfessorById(id);
	}

	// create professor - rest api

	@PostMapping("/professors")
	public ResponseEntity<ProfessorDTO> createProfessor(@Valid @RequestBody ProfessorDTO professorDTO) {
		return professorService.saveProfessor(professorDTO);
	}

	// update professor - rest api

	@PutMapping("/professors/{id}")
	public ResponseEntity<ProfessorDTO> updateProfessor(@PathVariable Integer id,
			@Valid @RequestBody ProfessorDTO professorDTO) {
		professorDTO.setId(id);
		return professorService.updateProfessor(professorDTO);
	}

	// delete professor - rest api

	@DeleteMapping("/professors/{id}")
	public ResponseEntity<ProfessorDTO> deleteProfessor(@PathVariable("id") Integer id) {
		return professorService.deleteProfessorById(id);
	}

}
