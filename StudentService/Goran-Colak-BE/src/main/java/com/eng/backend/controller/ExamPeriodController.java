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

import com.eng.backend.dto.ExamPeriodDTO;
import com.eng.backend.service.ExamPeriodService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ExamPeriodController {

	@Autowired
	private ExamPeriodService examPeriodService;

	@GetMapping("/examperiods-active")
	public ResponseEntity<List<ExamPeriodDTO>> getAllActiveExamPeriods() {
		return examPeriodService.getActiveExamPeriods();
	}

	// get all examperiods - rest api

	@GetMapping("/examperiods")
	public ResponseEntity<List<ExamPeriodDTO>> getAllExamPeriods() {
		return examPeriodService.getExamPeriods();
	}

	// get examperiod by id - rest api

	@GetMapping("/examperiods/{id}")
	public ResponseEntity<ExamPeriodDTO> getExamPeriodById(@PathVariable("id") Integer id) {
		return examPeriodService.getExamPeriodById(id);
	}

	// create examperiod - rest api

	@PostMapping("/examperiods")
	public ResponseEntity<ExamPeriodDTO> createExamPeriod(@Valid @RequestBody ExamPeriodDTO examPeriodDTO) {
		return examPeriodService.saveExamPeriod(examPeriodDTO);
	}

	// update examperiod - rest api

	@PutMapping("/examperiods/{id}")
	public ResponseEntity<ExamPeriodDTO> updateExamPeriod(@PathVariable Integer id, @Valid @RequestBody ExamPeriodDTO examPeriodDTO) {
		examPeriodDTO.setId(id);
		return examPeriodService.updateExamPeriod(examPeriodDTO);
	}

	// delete examperiod - rest api

	@DeleteMapping("/examperiods/{id}")
	public ResponseEntity<ExamPeriodDTO> deleteExamPeriod(@PathVariable("id") Integer id) {
		return examPeriodService.deleteExamPeriod(id);
	}

}
