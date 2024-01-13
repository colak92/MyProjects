package com.eng.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eng.backend.dto.ExamRegDTO;
import com.eng.backend.service.ExamRegService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ExamRegController {
	
	@Autowired
	ExamRegService examRegService;
	
	// get all examregs - rest api
	
	@GetMapping("/examregs")
	public ResponseEntity <List<ExamRegDTO>> getAllExamRegs() {
		return examRegService.getExamRegs();
	}

	// get examreg by id - rest api

	@GetMapping("/examregs/{id}")
	public ResponseEntity<ExamRegDTO> getExamRegById(@PathVariable("id") Integer id) {
		return examRegService.getExamRegById(id);
	}

	// save examreg - rest api

	@PutMapping("/examregs/{id}")
	public ResponseEntity<ExamRegDTO> saveExamReg(@PathVariable Integer id, @RequestBody ExamRegDTO examRegDTO) {
		examRegDTO.setId(id);
		return examRegService.saveExamReg(examRegDTO);
	}

	// delete examreg - rest api

	@DeleteMapping("/examregs/{id}")
	public ResponseEntity<ExamRegDTO> deleteExamReg(@PathVariable("id") Integer id) {
		return examRegService.deleteExamRegById(id);
	}

}
