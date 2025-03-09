package com.eng.backend.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eng.backend.dto.ExamDTO;
import com.eng.backend.dto.ExamRegDTO;
import com.eng.backend.dto.StudentDTO;
import com.eng.backend.exception.MyExamException;
import com.eng.backend.exception.MyExamRegException;
import com.eng.backend.mapper.ExamMapper;
import com.eng.backend.mapper.ExamRegMapper;
import com.eng.backend.mapper.StudentMapper;
import com.eng.backend.model.Exam;
import com.eng.backend.model.ExamPeriod;
import com.eng.backend.model.ExamReg;
import com.eng.backend.model.Professor;
import com.eng.backend.model.Student;
import com.eng.backend.model.Subject;
import com.eng.backend.model.User;
import com.eng.backend.repository.ExamPeriodRepository;
import com.eng.backend.repository.ExamRegRepository;
import com.eng.backend.repository.ExamRepository;
import com.eng.backend.repository.ProfessorRepository;
import com.eng.backend.repository.StudentRepository;
import com.eng.backend.repository.SubjectRepository;
import com.eng.backend.repository.UserRepository;

@Service
public class ExamService {

	private ExamRepository examRepository;
	private ExamPeriodRepository examPeriodRepository;
	private SubjectRepository subjectRepository;
	private StudentRepository studentRepository;
	private UserRepository userRepository;
	private ProfessorRepository professorRepository;
	private ExamRegRepository examRegRepository;
	private ExamMapper examMapper;
	private ExamRegMapper examRegMapper;
	private StudentMapper studentMapper;
	
	@Autowired
	public ExamService(ExamRepository examRepository, 
					   ExamPeriodRepository examPeriodRepository,
					   SubjectRepository subjectRepository,
					   StudentRepository studentRepository,
					   ProfessorRepository professorRepository,
					   UserRepository userRepository,
					   ExamRegRepository examRegRepository,
					   ExamMapper examMapper,
					   ExamRegMapper examRegMapper,
					   StudentMapper studentMapper
					   ) {
		this.examRepository = examRepository;
		this.examPeriodRepository = examPeriodRepository;
		this.subjectRepository = subjectRepository;
		this.studentRepository = studentRepository;
		this.professorRepository = professorRepository;
		this.userRepository = userRepository;
		this.examRegRepository = examRegRepository;
		this.examMapper = examMapper;
		this.examRegMapper = examRegMapper;
		this.studentMapper = studentMapper;
	}

	public ResponseEntity<ExamDTO> saveExam(ExamDTO examDTO) {
		Exam exam = examMapper.toExamEntity(examDTO);
		ExamPeriod examPeriod = examPeriodRepository.findById(exam.getExamperiod().getId()).get();
		
		String startDate = examPeriod.getExamstart().toString();
		String endDate = examPeriod.getExamend().toString();
		String examDate = exam.getExamdate().toString();

		Integer subjectId = exam.getSubject().getId();
		Integer examPeriodId = examPeriod.getId();
		Subject subject = subjectRepository.findById(subjectId).get();

		List<Exam> exams = examRepository.findAll();
		List<Exam> examIntervalList = new ArrayList<>();
		examIntervalList = examRepository.checkExamInterval(examDate, startDate, endDate);

		List<Exam> examSubjectExistsList = new ArrayList<>();
		examSubjectExistsList = examRepository.checkExamSubjectExists(examPeriodId, subjectId);
		
		if (examSubjectExistsList != null && !examSubjectExistsList.isEmpty()) {
			throw new MyExamException(subject.getName());
		}

		if (exams != null && !exams.isEmpty()) {
			if (examIntervalList != null && examIntervalList.isEmpty()) {
				throw new MyExamException();
			}
		}
		
		examRepository.save(exam);
		return ResponseEntity.status(HttpStatus.OK).body(examDTO);
	}

	public ResponseEntity <List<ExamDTO>> getExams() {
		List<ExamDTO> exams = examMapper.toExamDTOs(examRepository.findAll());
		return ResponseEntity.ok(exams);
	}
	
	public ResponseEntity <List<ExamDTO>> getExamsForProfessor(Integer userID) {
		List<ExamDTO> professorExams = new ArrayList<>();
		if (userID != null)
		{	
			Optional<User> u = userRepository.findById(userID);
			Professor p = professorRepository.findByEmail(u.get().getEmail());
			
			if (p != null) {
				professorExams = examMapper.toExamDTOs(examRepository.checkProfessorHasExam(p.getId()));
			}
		}
		
		return ResponseEntity.ok(professorExams);
	}

	public ResponseEntity<ExamDTO> getExamById(Integer id) {
		Optional<Exam> exam = examRepository.findById(id);
		ExamDTO examDTO = examMapper.toExamDTO(exam.get());
		return ResponseEntity.ok(examDTO);
	}

	public ResponseEntity<ExamDTO> deleteExamById(Integer id) {
		examRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build(); 
	}
	
	// TODO Trenutno se ne koristi, metoda za izmenu registrovanog ispita

	public ResponseEntity<ExamDTO> updateExam(ExamDTO examDTO) {
		Exam exam = examMapper.toExamEntity(examDTO);
		Exam existingExam = examRepository.findById(exam.getId()).orElse(null);
		ExamPeriod examPeriod = examPeriodRepository.findById(exam.getExamperiod().getId()).get();
		
		String startDate = examPeriod.getExamstart().toString();
		String endDate = examPeriod.getExamend().toString();
		String examDate = exam.getExamdate().toString();

		Integer subjectId = exam.getSubject().getId();
		Integer examPeriodId = examPeriod.getId();
		Subject subject = subjectRepository.findById(subjectId).get();

		List<Exam> examIntervalList = new ArrayList<>();
		examIntervalList = examRepository.checkExamInterval(examDate, startDate, endDate);

		List<Exam> examSubjectExistsList = new ArrayList<>();
		examSubjectExistsList = examRepository.checkExamSubjectExists(examPeriodId, subjectId);
		
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
		
		existingExam.setName(exam.getName());
		existingExam.setExamdate(exam.getExamdate());
		existingExam.setExamperiod(exam.getExamperiod());
		existingExam.setSubject(exam.getSubject());
		existingExam.setProfessor(exam.getProfessor());
		
		examRepository.save(existingExam);
		return ResponseEntity.status(HttpStatus.OK).body(examDTO);
	}
	
	public ResponseEntity<List<ExamRegDTO>> registerExams(StudentDTO studentDTO, List<ExamDTO> examListDTOs) {
		Student student = studentMapper.toStudentEntity(studentDTO);
		
		List<Exam> examList = examMapper.toExamEntities(examListDTOs);
		List<ExamReg> examRegList = new ArrayList<>();
		
		List<ExamRegDTO> examRegDTOs = null;
		ExamReg examReg = null;
		for (Exam e : examList) {
			examReg = new ExamReg();
			examReg.setStudent(student);
			examReg.setExam(e);
			examRegList.add(examReg);
		}
		
		examRegDTOs = examRegMapper.toExamRegDTOs(examRegList);
		
		if (examRegDTOs != null && !examRegDTOs.isEmpty()) {
			checkExistedRegExams(examRegDTOs);
			checkCorrectExamDateRegExams(examRegDTOs);
			checkYearOfStudyRegExams(examRegDTOs);
			
			for (ExamRegDTO examRegDTO : examRegDTOs) {
				saveExamReg(examRegDTO);
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(examRegDTOs);
	}
	
	public ResponseEntity<List<ExamRegDTO>> checkExistedRegExams(List<ExamRegDTO> examRegDTOs) {
		
		// Exam registration is possible only if exam is not registered.
		
		List <String> examNamesExistList = new ArrayList<>();
		
		Student student = null;
		Exam exam = null;
		
		for (ExamRegDTO examRegDTO : examRegDTOs) {
			exam = examRepository.findById(examRegDTO.getExamId()).get();
			student = studentRepository.findById(examRegDTO.getStudentId()).get();
			
			List <ExamReg> tempList = examRegRepository.checkRegExamsForStudent(student.getId(), exam.getId());
			
			if (tempList != null && !tempList.isEmpty()) {
				for (ExamReg examRegOld : tempList) {
					examNamesExistList.add(examRegOld.getExam().getName());
				}
				tempList.clear();
			}
		}
		
		if (examNamesExistList != null && !examNamesExistList.isEmpty()) {
			String fullName = student.getFirstname() + " " + student.getLastname();
			throw new MyExamRegException(fullName, examNamesExistList);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(examRegDTOs);
	}
	
	public ResponseEntity<List<ExamRegDTO>> checkYearOfStudyRegExams(List<ExamRegDTO> examRegDTOs) {
		
		// Exam registration is possible only if year of study is less than or equal to the year of study that the student is currently attending.
		
		Exam exam = null;
		Student student = null;
		Subject subject = null;
		
		HashMap<String, Integer> map = new HashMap<>();
		
		for (ExamRegDTO examRegDTO : examRegDTOs) {
			exam = examRepository.findById(examRegDTO.getExamId()).get();
			student = studentRepository.findById(examRegDTO.getStudentId()).get();
			subject = subjectRepository.findById(exam.getSubject().getId()).get();
			
			Integer yearOfStudy = subject.getYearofstudy();
			Integer currentYearOfStudy = student.getCurrentyearofstudy();
			
			if (yearOfStudy > currentYearOfStudy) {
				String examName = exam.getName();
				map.put(examName, yearOfStudy);
			}
		}
		
		if (map != null && !map.isEmpty()) {
			throw new MyExamRegException(map);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(examRegDTOs);
	}
	
	public ResponseEntity<List<ExamRegDTO>> checkCorrectExamDateRegExams(List<ExamRegDTO> examRegDTOs) {
		
		// Exam registration is possible only in the last week before the start of the currently active exam period
		
		List <String> examNamesList = new ArrayList<>();
		Exam exam = null;
		ExamPeriod examPeriod = null;
		Long daysToWait = null;
		String formattedPeriodStart = null;
		
		for (ExamRegDTO examRegDTO : examRegDTOs) {
			exam = examRepository.findById(examRegDTO.getExamId()).get();
			examPeriod = exam.getExamperiod();
			
			LocalDateTime currentExamRegDate = LocalDateTime.now();
			
			LocalDateTime examPeriodStart = examPeriod.getExamstart();
			System.out.println("Exam period start: " + examPeriodStart);
			
			LocalDateTime startOfLastWeek = examPeriodStart.minusWeeks(1).with(DayOfWeek.MONDAY);
			System.out.println("Start of last week: " + startOfLastWeek);

			LocalDateTime endOfLastWeek = startOfLastWeek.plusDays(6);
			System.out.println("End of last week: " + endOfLastWeek);
			
			daysToWait = Duration.between(currentExamRegDate, startOfLastWeek).toDays();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			formattedPeriodStart = dtf.format(examPeriodStart);
			
			if (!(currentExamRegDate.isAfter(startOfLastWeek) && currentExamRegDate.isBefore(endOfLastWeek))) {
				System.out.println("The exam registration date is NOT correct.");
				examNamesList.add(exam.getName());
			}
			else {
				examRegDTO.setExamregdate(currentExamRegDate);
			}
		}
		
		if (examNamesList != null && !examNamesList.isEmpty()) {
			throw new MyExamRegException(formattedPeriodStart, daysToWait, examNamesList);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(examRegDTOs);
	}
	
	public ResponseEntity<ExamRegDTO> saveExamReg(ExamRegDTO examRegDTO) {
		ExamReg examReg = examRegMapper.toExamRegEntity(examRegDTO);
		Exam exam = examRepository.findById(examReg.getExam().getId()).get();
		Student student = studentRepository.findById(examReg.getStudent().getId()).get();
		
		examReg.setStudent(student);
		examReg.setExam(exam);

		examRegRepository.save(examReg);
		return ResponseEntity.status(HttpStatus.OK).body(examRegDTO);
	}

}
