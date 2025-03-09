package com.eng.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eng.backend.dto.StudentDTO;
import com.eng.backend.exception.MyStudentException;
import com.eng.backend.mapper.StudentMapper;
import com.eng.backend.model.ExamReg;
import com.eng.backend.model.Role;
import com.eng.backend.model.RoleEnum;
import com.eng.backend.model.Student;
import com.eng.backend.model.User;
import com.eng.backend.repository.ExamRegRepository;
import com.eng.backend.repository.RoleRepository;
import com.eng.backend.repository.StudentRepository;
import com.eng.backend.repository.UserRepository;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private ExamRegRepository examRegRepository;
	private PasswordEncoder passwordEncoder;
	private StudentMapper studentMapper;

	@Autowired
	public StudentService(StudentRepository studentRepository, 
						  UserRepository userRepository,
						  RoleRepository roleRepository,
						  ExamRegRepository examRegRepository,
						  PasswordEncoder passwordEncoder,
						  StudentMapper studentMapper) {
		
		this.studentRepository = studentRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.examRegRepository = examRegRepository;
		this.passwordEncoder = passwordEncoder;
		this.studentMapper = studentMapper;
	}

	public ResponseEntity<StudentDTO> saveStudent(StudentDTO studentDTO) {
		Student student = studentMapper.toStudentEntity(studentDTO);
		User user = new User();
		user.setUsername(student.getFirstname().toLowerCase() + student.getLastname().toLowerCase());
		user.setEmail(student.getEmail());
		user.setPassword(passwordEncoder.encode("student"));

		Role studentRole = roleRepository.findByName(RoleEnum.STUDENT).orElse(null);
		Set<Role> roles = new HashSet<>();
		roles.add(studentRole);
		user.setRoles(roles);
		
		Map<String, String> params = checkIfStudentExists(student);
		if (params != null && !params.isEmpty()) {
			throw new MyStudentException(params);
		}

		userRepository.save(user);
		studentRepository.save(student);
		
		return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
	}

	public ResponseEntity<List<StudentDTO>> getStudents() {
		List<StudentDTO> students = studentMapper.toStudentDTOs(studentRepository.findAll());
		return ResponseEntity.ok(students);
	}
	
	public ResponseEntity<Map<String, Object>> getPageAndSortStudents(Integer page, Integer size, String sortBy, String sortDir) {
		if (sortBy.isEmpty()) {
			sortBy = "id";
		}

		if (sortDir.isEmpty()) {
			sortDir = "asc";
		}

		try {
			List<Student> studentData = new ArrayList<Student>();
			Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
			Pageable pagingSort = PageRequest.of(page, size, sort);
			Page<Student> pageStudents = studentRepository.findAll(pagingSort);
			
			if (pageStudents != null) {
				studentData = pageStudents.getContent();
			}
			
			if (studentData.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			List<StudentDTO> students = studentMapper.toStudentDTOs(studentData);
			Map<String, Object> response = new HashMap<>();
			response.put("students", students);
			response.put("currentPage", pageStudents.getNumber());
			response.put("totalItems", pageStudents.getTotalElements());
			response.put("totalPages", pageStudents.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public Student getStudentByIndexNumber(String indexNumber) {
		return studentRepository.findByIndexnumber(indexNumber);
	}
	
	public Student getStudentByIndexYear(Integer indexYear) {
		return studentRepository.findByIndexyear(indexYear);
	}
	
	public Student getStudentByEmail(String email) {
		return studentRepository.findByEmail(email);
	}
	
	public ResponseEntity<StudentDTO> getStudentById(Integer id) {
		Optional<Student> student = studentRepository.findById(id);
		StudentDTO studentDTO = studentMapper.toStudentDTO(student.get());
		return ResponseEntity.ok(studentDTO);
	}

	public ResponseEntity<StudentDTO> deleteStudentById(Integer id) {
		Student student = studentRepository.findById(id).get();
		User user = userRepository.findByEmail(student.getEmail());

		List<ExamReg> examRegList = new ArrayList<>();
		examRegList = examRegRepository.findRegExamsForStudent(student.getId());
		
		List<String> examNameList = new ArrayList<>();
		if (examRegList != null && !examRegList.isEmpty()) {
			for (ExamReg examReg : examRegList) {
				examNameList.add(examReg.getExam().getName());
			}
		}
		
		if (examNameList != null && !examNameList.isEmpty()) {
			throw new MyStudentException(examNameList);
		}
		
		if (user.getId() != null && student.getId() != null) {
			userRepository.deleteById(user.getId());
			studentRepository.deleteById(student.getId());
		}
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	public ResponseEntity<StudentDTO> updateStudent(StudentDTO studentDTO) throws MyStudentException {
		Student student = studentMapper.toStudentEntity(studentDTO);
		Student existingStudent = studentRepository.findById(student.getId()).orElse(null);

		User user = userRepository.findByEmail(existingStudent.getEmail());
		if (user != null && user.getId() != null) {
			user.setEmail(student.getEmail());
			userRepository.save(user);
		}
		
		existingStudent.setIndexnumber(student.getIndexnumber());
		existingStudent.setIndexyear(student.getIndexyear());
		existingStudent.setFirstname(student.getFirstname());
		existingStudent.setLastname(student.getLastname());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setAddress(student.getAddress());
		existingStudent.setCurrentyearofstudy(student.getCurrentyearofstudy());
		existingStudent.setCity(student.getCity());
		studentRepository.save(existingStudent);
		return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
	}
	
	// private methods
	
	private Map<String, String> checkIfStudentExists(Student student) {
		Student oldStudent = null;
		Map<String, String> params = new HashMap<>();
		
		if (student.getIndexnumber() != null) {
			oldStudent = studentRepository.findByIndexnumber(student.getIndexnumber());
			if (oldStudent != null) {
				params.put("index_number", student.getIndexnumber());
			}
		}
		
		if (student.getIndexyear() != null) {
			oldStudent = studentRepository.findByIndexyear(student.getIndexyear());
			if (oldStudent != null) {
				params.put("index_year", student.getIndexyear().toString());
			}
		}
		
		if (student.getEmail() != null) {
			oldStudent = studentRepository.findByEmail(student.getEmail());
			if (oldStudent != null) {
				params.put("email", student.getEmail());
			}
		}
		
		return params;
	}

}
