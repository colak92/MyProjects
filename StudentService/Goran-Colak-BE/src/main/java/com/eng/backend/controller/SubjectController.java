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

import com.eng.backend.dto.SubjectDTO;
import com.eng.backend.model.Subject;
import com.eng.backend.service.SubjectService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class SubjectController {
	
	@Autowired
    private SubjectService subjectService;
	
	// get all subjects - rest api
	
	@GetMapping("/subjects-list")
	public ResponseEntity <List<SubjectDTO>> getAllSubjects() {
		return subjectService.getSubjects();
	}

	// get all subjects sort and pagination - rest api

	@GetMapping("/subjects")
	public ResponseEntity<Map<String, Object>> getAllSubjectsPageAndSort(
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
			List<Subject> subjects = new ArrayList<Subject>();
			Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
			Pageable pagingSort = PageRequest.of(page, size, sort);

			Page<Subject> pageSubjects;
			pageSubjects = subjectService.getPageSubjects(pagingSort);
			subjects = pageSubjects.getContent();

			if (subjects.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("subjects", subjects);
			response.put("currentPage", pageSubjects.getNumber());
			response.put("totalItems", pageSubjects.getTotalElements());
			response.put("totalPages", pageSubjects.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get subject by id - rest api

	@GetMapping("/subjects/{id}")
	public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable("id") Integer id) {
		return subjectService.getSubjectById(id);
	}

	// create subject - rest api

	@PostMapping("/subjects")
	public ResponseEntity<SubjectDTO> createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
		return subjectService.saveSubject(subjectDTO);
	}

	// update subject - rest api

	@PutMapping("/subjects/{id}")
	public ResponseEntity<SubjectDTO> updateSubject(@PathVariable Integer id, @Valid @RequestBody SubjectDTO subjectDTO) {
		subjectDTO.setId(id);
		return subjectService.updateSubject(subjectDTO);
	}

	// delete subject - rest api

	@DeleteMapping("/subjects/{id}")
	public ResponseEntity<SubjectDTO> deleteSubject(@PathVariable("id") Integer id) {
		return subjectService.deleteSubjectById(id);
	}

}
