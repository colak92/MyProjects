package com.eng.backend.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.eng.backend.dto.ExamDTO;
import com.eng.backend.service.ExamService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ExamController {

	@Autowired
	private ExamService examService;

	// get all exams - rest api

	@GetMapping("/exams")
	public ResponseEntity<List<ExamDTO>> getAllExams() {
		return examService.getExams();
	}

	// get exam by id - rest api

	@GetMapping("/exams/{id}")
	public ResponseEntity<ExamDTO> getExamById(@PathVariable("id") Integer id) {
		return examService.getExamById(id);
	}

	// create exam - rest api

	@PostMapping("/exams")
	public ResponseEntity<ExamDTO> createExam(@Valid @RequestBody ExamDTO examDTO) {
		return examService.saveExam(examDTO);
	}

	// update exam - rest api

	@PutMapping("/exams/{id}")
	public ResponseEntity<ExamDTO> updateExam(@PathVariable Integer id, @Valid @RequestBody ExamDTO examDTO) {
		examDTO.setId(id);
		return examService.updateExam(examDTO);
	}

	// delete exam - rest api

	@DeleteMapping("/exams/{id}")
	public ResponseEntity<ExamDTO> deleteExam(@PathVariable("id") Integer id) {
		return examService.deleteExamById(id);
	}

}
