package com.eng.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eng.backend.dto.ExamDTO;
import com.eng.backend.exception.MyExamException;
import com.eng.backend.mapper.ExamMapper;
import com.eng.backend.model.Exam;
import com.eng.backend.model.ExamPeriod;
import com.eng.backend.model.ExamReg;
import com.eng.backend.model.Student;
import com.eng.backend.model.Subject;
import com.eng.backend.repository.ExamPeriodRepository;
import com.eng.backend.repository.ExamRegRepository;
import com.eng.backend.repository.ExamRepository;
import com.eng.backend.repository.StudentRepository;
import com.eng.backend.repository.SubjectRepository;

@Service
public class ExamService {

	private ExamRepository examRepository;
	private ExamPeriodRepository examPeriodRepository;
	private ExamRegRepository examRegRepository;
	private SubjectRepository subjectRepository;
	private StudentRepository studentRepository;
	private ExamMapper examMapper;
	
	@Autowired
	public ExamService(ExamRepository examRepository, 
					   ExamPeriodRepository examPeriodRepository,
					   ExamRegRepository examRegRepository,
					   SubjectRepository subjectRepository,
					   StudentRepository studentRepository,
					   ExamMapper examMapper
					   ) {
		this.examRepository = examRepository;
		this.examPeriodRepository = examPeriodRepository;
		this.examRegRepository = examRegRepository;
		this.subjectRepository = subjectRepository;
		this.studentRepository = studentRepository;
		this.examMapper = examMapper;
	}

	public ResponseEntity<ExamDTO> saveExam(ExamDTO examDTO) {
		Exam exam = examMapper.toExamEntity(examDTO);
		ExamPeriod examPeriod = examPeriodRepository.findById(exam.getExamperiod().getId()).get();
		
		String startDate = examPeriod.getExamstart().toString();
		String endDate = examPeriod.getExamend().toString();
		String examDate = exam.getExamdate().toString();

		Integer subjectId = exam.getSubject().getId();
		Integer examPeriodId = examPeriod.getId();
		Integer studentId = exam.getStudent().getId();

		List<Exam> exams = examRepository.findAll();
		List<Exam> examIntervalList = new ArrayList<>();
		examIntervalList = examRepository.checkExamInterval(examDate, startDate, endDate);

		List<Exam> examSubjectExistsList = new ArrayList<>();
		examSubjectExistsList = examRepository.checkExamSubjectExists(examPeriodId, subjectId, studentId);
		
		Subject subject = subjectRepository.findById(exam.getSubject().getId()).get();
		Integer yearOfStudy = subject.getYearofstudy();
		
		Student student = studentRepository.findById(exam.getStudent().getId()).get();
		Integer currentYearOfStudy = student.getCurrentyearofstudy();

		if (examSubjectExistsList != null && !examSubjectExistsList.isEmpty()) {
			throw new MyExamException(subject.getName());
		}

		if (exams != null && !exams.isEmpty()) {
			if (examIntervalList != null && examIntervalList.isEmpty()) {
				throw new MyExamException();
			}
		}
		
		if (yearOfStudy > currentYearOfStudy) {
			throw new MyExamException(yearOfStudy);
		}

		examRepository.save(exam);

		if (exam != null && exam.getId() != null) {
			ExamReg er = new ExamReg();
			er.setComment(null);
			er.setExam(exam);
			examRegRepository.save(er);
		}

		return ResponseEntity.status(HttpStatus.OK).body(examDTO);
	}

	public ResponseEntity <List<ExamDTO>> getExams() {
		List<ExamDTO> exams = examMapper.toExamDTOs(examRepository.findAll());
		return ResponseEntity.ok(exams);
	}

	public ResponseEntity<ExamDTO> getExamById(int id) {
		Optional<Exam> exam = examRepository.findById(id);
		ExamDTO examDTO = examMapper.toExamDTO(exam.get());
		return ResponseEntity.ok(examDTO);
	}

	public ResponseEntity<ExamDTO> deleteExamById(int id) {
		examRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build(); 
	}

	public ResponseEntity<ExamDTO> updateExam(ExamDTO examDTO) {
		Exam exam = examMapper.toExamEntity(examDTO);
		Exam existingExam = examRepository.findById(exam.getId()).orElse(null);
		ExamPeriod examPeriod = examPeriodRepository.findById(exam.getExamperiod().getId()).get();
		
		String startDate = examPeriod.getExamstart().toString();
		String endDate = examPeriod.getExamend().toString();
		String examDate = exam.getExamdate().toString();

		Integer subjectId = exam.getSubject().getId();
		Integer examPeriodId = examPeriod.getId();
		Integer studentId = exam.getStudent().getId();

		List<Exam> examIntervalList = new ArrayList<>();
		examIntervalList = examRepository.checkExamInterval(examDate, startDate, endDate);

		List<Exam> examSubjectExistsList = new ArrayList<>();
		examSubjectExistsList = examRepository.checkExamSubjectExists(examPeriodId, subjectId, studentId);
		
		Subject subject = subjectRepository.findById(exam.getSubject().getId()).get();
		Integer yearOfStudy = subject.getYearofstudy();
		
		Student student = studentRepository.findById(exam.getStudent().getId()).get();
		Integer currentYearOfStudy = student.getCurrentyearofstudy();

		if (examSubjectExistsList != null && !examSubjectExistsList.isEmpty()) {

			if (examSubjectExistsList.size() == 1) {
				Exam examObject1 = examSubjectExistsList.get(0);

				if (exam.getId() != examObject1.getId()) {
					throw new MyExamException(subject.getName());
				}
			}
		}

		if (examIntervalList != null && examIntervalList.isEmpty()) {
			throw new MyExamException();
		}
		
		if (yearOfStudy > currentYearOfStudy) {
			throw new MyExamException(yearOfStudy);
		}

		existingExam.setName(exam.getName());
		existingExam.setExamdate(exam.getExamdate());
		existingExam.setGrade(exam.getGrade());
		existingExam.setPassed(exam.getPassed());
		existingExam.setRegistered(exam.getRegistered());
		existingExam.setExamperiod(exam.getExamperiod());
		existingExam.setSubject(exam.getSubject());
		existingExam.setProfessor(exam.getProfessor());
		existingExam.setStudent(exam.getStudent());
		
		examRepository.save(existingExam);
		return ResponseEntity.status(HttpStatus.OK).body(examDTO);
	}

}
