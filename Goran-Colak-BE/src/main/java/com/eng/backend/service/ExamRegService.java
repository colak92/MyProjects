package com.eng.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eng.backend.dto.ExamRegDTO;
import com.eng.backend.mapper.ExamRegMapper;
import com.eng.backend.model.ExamReg;
import com.eng.backend.model.Student;
import com.eng.backend.model.User;
import com.eng.backend.repository.ExamRegRepository;
import com.eng.backend.repository.StudentRepository;
import com.eng.backend.repository.UserRepository;

@Service
public class ExamRegService {

	private ExamRegRepository examRegRepository;
	private StudentRepository studentRepository;
	private UserRepository userRepository;
	private ExamRegMapper examRegMapper;

	@Autowired
	public ExamRegService(
			ExamRegRepository examRegRepository, 
			StudentRepository studentRepository,
			UserRepository userRepository,
			ExamRegMapper examRegMapper) {

		this.examRegRepository = examRegRepository;
		this.studentRepository = studentRepository;
		this.userRepository = userRepository;
		this.examRegMapper = examRegMapper;
	}

	public ResponseEntity<List<ExamRegDTO>> getExamRegs() {
		List<ExamRegDTO> examRegs = examRegMapper.toExamRegDTOs(examRegRepository.findAll());
		return ResponseEntity.ok(examRegs);
	}

	public ResponseEntity<List<ExamRegDTO>> getExamRegsForStudent(Integer userID) {
		List<ExamRegDTO> studentExams = new ArrayList<>();
		if (userID != null) {
			Optional<User> u = userRepository.findById(userID);
			Student s = studentRepository.findByEmail(u.get().getEmail());

			if (s != null) {
				studentExams = examRegMapper.toExamRegDTOs(examRegRepository.findRegExamsForStudent(s.getId()));
			}
		}

		return ResponseEntity.ok(studentExams);
	}

	public ResponseEntity<ExamRegDTO> getExamRegById(Integer id) {
		Optional<ExamReg> examReg = examRegRepository.findById(id);
		ExamRegDTO examRegDTO = examRegMapper.toExamRegDTO(examReg.get());
		return ResponseEntity.ok(examRegDTO);
	}

	public ResponseEntity<ExamRegDTO> deleteExamRegById(Integer id) {
		examRegRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	public ResponseEntity<ExamRegDTO> enterExamGrade(ExamRegDTO examRegDTO) {
		ExamReg examReg = examRegMapper.toExamRegEntity(examRegDTO);
		ExamReg existingExamReg = examRegRepository.findById(examReg.getId()).orElse(null);

		existingExamReg.setGrade(examReg.getGrade());
		existingExamReg.setComment(examReg.getComment());

		if (examReg.getGrade() > 5) {
			existingExamReg.setPassed(true);
		} else {
			existingExamReg.setPassed(false);
		}

		examRegRepository.save(existingExamReg);
		return ResponseEntity.status(HttpStatus.OK).body(examRegDTO);
	}

}
