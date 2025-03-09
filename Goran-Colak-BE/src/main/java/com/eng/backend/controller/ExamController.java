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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eng.backend.dto.ExamDTO;
import com.eng.backend.dto.ExamRegDTO;
import com.eng.backend.dto.StudentDTO;
import com.eng.backend.service.ExamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:6060")
public class ExamController {

	@Autowired
	private ExamService examService;

	// get all exams - rest api

	@GetMapping("/exams")
	public ResponseEntity<List<ExamDTO>> getAllExams() {
		return examService.getExams();
	}

	// get all exams for professor - rest api

	@GetMapping("/exams/professor/{id}")
	public ResponseEntity<?> getExamsForProfessor(@PathVariable("id") Integer id) {
		return examService.getExamsForProfessor(id);
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
	
	// register exams - rest api
	
	@GetMapping("/exams/exam-reg")
    public ResponseEntity<List<ExamRegDTO>> getParamsForExamReg(@RequestParam String student, @RequestParam String examList) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		
		StudentDTO studentDTO = null;
		List<ExamDTO> examListDTOs = null;
		
		try {
			studentDTO = objectMapper.readValue(student, StudentDTO.class);
			examListDTOs = objectMapper.readValue(examList, new TypeReference<List<ExamDTO>>() {});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return examService.registerExams(studentDTO, examListDTOs);
    }

}
