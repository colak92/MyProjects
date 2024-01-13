package com.eng.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eng.backend.dto.SubjectDTO;
import com.eng.backend.exception.MySubjectException;
import com.eng.backend.mapper.SubjectMapper;
import com.eng.backend.model.Exam;
import com.eng.backend.model.Subject;
import com.eng.backend.repository.ExamRepository;
import com.eng.backend.repository.SubjectRepository;

@Service
public class SubjectService {

	private SubjectRepository subjectRepository;
	private ExamRepository examRepository;
	private SubjectMapper subjectMapper;

	@Autowired
	public SubjectService(SubjectRepository subjectRepository, 
						  ExamRepository examRepository,
						  SubjectMapper subjectMapper) {

		this.subjectRepository = subjectRepository;
		this.examRepository = examRepository;
		this.subjectMapper = subjectMapper;
	}

	public ResponseEntity<SubjectDTO> saveSubject(SubjectDTO subjectDTO) {
		Subject subject = subjectMapper.toSubjectEntity(subjectDTO);
		subjectRepository.save(subject);
		return ResponseEntity.status(HttpStatus.OK).body(subjectDTO);
	}

	public ResponseEntity<List<SubjectDTO>> getSubjects() {
		List<SubjectDTO> subjects = subjectMapper.toSubjectDTOs(subjectRepository.findAll());
		return ResponseEntity.ok(subjects);
	}

	// sorting and pagination - START

	public List<Subject> getSortSubjects(Sort sort) {
		return subjectRepository.findAll(sort);
	}

	public Page<Subject> getPageSubjects(Pageable pageable) {
		return subjectRepository.findAll(pageable);
	}

	// sorting and pagination - END

	public ResponseEntity<SubjectDTO> getSubjectById(int id) {
		Optional<Subject> subject = subjectRepository.findById(id);
		SubjectDTO subjectDTO = subjectMapper.toSubjectDTO(subject.get());
		return ResponseEntity.ok(subjectDTO);
	}

	public ResponseEntity<SubjectDTO> deleteSubjectById(int id) {
		Subject subject = subjectRepository.findById(id).get();

		List<Exam> examList = new ArrayList<>();
		examList = examRepository.checkSubjectHasExam(subject.getId());

		if (examList != null && !examList.isEmpty() && examList.size() == 1) {
			String examName = examList.get(0).getName();
			throw new MySubjectException(examName);
		}

		subjectRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	public ResponseEntity<SubjectDTO> updateSubject(SubjectDTO subjectDTO) {
		Subject subject = subjectMapper.toSubjectEntity(subjectDTO);
		Subject existingSubject = subjectRepository.findById(subject.getId()).orElse(null);

		existingSubject.setName(subject.getName());
		existingSubject.setDescription(subject.getDescription());
		existingSubject.setYearofstudy(subject.getYearofstudy());
		existingSubject.setNoofesp(subject.getNoofesp());
		existingSubject.setSemester(subject.getSemester());

		subjectRepository.save(existingSubject);
		return ResponseEntity.status(HttpStatus.OK).body(subjectDTO);
	}

}
