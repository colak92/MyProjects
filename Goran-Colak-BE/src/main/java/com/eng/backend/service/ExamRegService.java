package com.eng.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eng.backend.dto.ExamRegDTO;
import com.eng.backend.mapper.ExamRegMapper;
import com.eng.backend.model.Exam;
import com.eng.backend.model.ExamReg;
import com.eng.backend.repository.ExamRegRepository;
import com.eng.backend.repository.ExamRepository;

@Service
public class ExamRegService {

	private ExamRegRepository examRegRepository;
	private ExamRepository examRepository;
	private ExamRegMapper examRegMapper;

	@Autowired
	public ExamRegService(ExamRegRepository examRegRepository, 
						  ExamRepository examRepository,
						  ExamRegMapper examRegMapper) {
		
		this.examRegRepository = examRegRepository;
		this.examRepository = examRepository;
		this.examRegMapper = examRegMapper;
	}

	public ResponseEntity<List<ExamRegDTO>> getExamRegs() {
		List<ExamRegDTO> examRegs = examRegMapper.toExamRegDTOs(examRegRepository.findAll());
		return ResponseEntity.ok(examRegs);
	}

	public ResponseEntity<ExamRegDTO> getExamRegById(int id) {
		Optional<ExamReg> examReg = examRegRepository.findById(id);
		ExamRegDTO examRegDTO = examRegMapper.toExamRegDTO(examReg.get());
		return ResponseEntity.ok(examRegDTO);
	}

	public ResponseEntity<ExamRegDTO> deleteExamRegById(int id) {
		examRegRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	public ResponseEntity<ExamRegDTO> saveExamReg(ExamRegDTO examRegDTO) {
		ExamReg examReg = examRegMapper.toExamRegEntity(examRegDTO);
		ExamReg existingExamReg = examRegRepository.findById(examReg.getId()).orElse(null);

		existingExamReg.setExam(examReg.getExam());
		existingExamReg.setComment(examReg.getComment());

		examRegRepository.save(existingExamReg);

		if (existingExamReg != null && existingExamReg.getId() != null) {

			if (existingExamReg.getExam() != null && existingExamReg.getExam().getId() != null) {
				Exam exam = existingExamReg.getExam();
				exam.setRegistered(existingExamReg.getExam().getRegistered());
				examRepository.save(exam);
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(examRegDTO);
	}

}
