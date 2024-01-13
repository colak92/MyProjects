package com.eng.backend.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.eng.backend.dto.StudentDTO;
import com.eng.backend.service.StudentService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// get all students - rest api

	@GetMapping("/students-list")
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		return studentService.getStudents();
	}
	
	// get all students sort and pagination - rest api

	@GetMapping("/students")
	public ResponseEntity<Map<String, Object>> getAllStudentsPageAndSort(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size, 
			@RequestParam String sortBy, 
			@RequestParam String sortDir) {

		return studentService.getPageAndSortStudents(page, size, sortBy, sortDir);
	}

	// get student by id - rest api

	@GetMapping("/students/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Integer id) {
		return studentService.getStudentById(id);
	}

	// create student - rest api

	@PostMapping("/students")
	public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
		return studentService.saveStudent(studentDTO);
	}

	// update student - rest api

	@PutMapping("/students/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id,
			@Valid @RequestBody StudentDTO studentDTO) {
		studentDTO.setId(id);
		return studentService.updateStudent(studentDTO);
	}

	// delete student - rest api

	@DeleteMapping("/students/{id}")
	public ResponseEntity<StudentDTO> deleteStudent(@PathVariable("id") Integer id) {
		return studentService.deleteStudentById(id);
	}

}
