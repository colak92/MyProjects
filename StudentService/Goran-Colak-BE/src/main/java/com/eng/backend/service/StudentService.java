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
import com.eng.backend.model.Exam;
import com.eng.backend.model.Role;
import com.eng.backend.model.RoleEnum;
import com.eng.backend.model.Student;
import com.eng.backend.model.User;
import com.eng.backend.repository.ExamRepository;
import com.eng.backend.repository.RoleRepository;
import com.eng.backend.repository.StudentRepository;
import com.eng.backend.repository.UserRepository;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private ExamRepository examRepository;
	private PasswordEncoder passwordEncoder;
	private StudentMapper studentMapper;

	@Autowired
	public StudentService(StudentRepository studentRepository, 
						  UserRepository userRepository,
						  RoleRepository roleRepository, 
						  ExamRepository examRepository, 
						  PasswordEncoder passwordEncoder,
						  StudentMapper studentMapper) {
		
		this.studentRepository = studentRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.examRepository = examRepository;
		this.passwordEncoder = passwordEncoder;
		this.studentMapper = studentMapper;
	}

	public ResponseEntity<StudentDTO> saveStudent(StudentDTO studentDTO) {
		Student student = studentMapper.toStudentEntity(studentDTO);

		User user = new User();
		user.setUsername(student.getFirstname().toLowerCase() + student.getLastname().toLowerCase());
		user.setEmail(student.getEmail());
		user.setPassword(passwordEncoder.encode("student"));

		Role studentRole = roleRepository.findByName(RoleEnum.ROLE_STUDENT).orElse(null);
		Set<Role> roles = new HashSet<>();
		roles.add(studentRole);
		user.setRoles(roles);

		List<Student> students = new ArrayList<>();
		students.add(student);
		user.setStudents(students);
		userRepository.save(user);

		studentRepository.save(student);
		return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
	}

	public ResponseEntity<List<StudentDTO>> getStudents() {
		List<StudentDTO> students = studentMapper.toStudentDTOs(studentRepository.findAll());
		return ResponseEntity.ok(students);
	}
	
	public ResponseEntity<Map<String, Object>> getPageAndSortStudents(int page, int size, String sortBy, String sortDir) {
		
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

			Page<Student> pageStudents;
			pageStudents = studentRepository.findAll(pagingSort);
			studentData = pageStudents.getContent();

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
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<StudentDTO> getStudentById(int id) {
		Optional<Student> student = studentRepository.findById(id);
		StudentDTO studentDTO = studentMapper.toStudentDTO(student.get());
		return ResponseEntity.ok(studentDTO);
	}

	public Student getStudentByIndexNumber(String indexnumber) {
		return studentRepository.findByIndexnumber(indexnumber);
	}

	public ResponseEntity<StudentDTO> deleteStudentById(int id) {
		Student student = studentRepository.findById(id).get();
		User user = userRepository.findByUserEmail(student.getEmail());

		List<Exam> examList = new ArrayList<>();
		examList = examRepository.checkStudentHasExam(student.getId());

		if (examList != null && !examList.isEmpty() && examList.size() == 1) {
			String examName = examList.get(0).getName();
			throw new MyStudentException(examName);
		}

		userRepository.deleteById(user.getId());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	public ResponseEntity<StudentDTO> updateStudent(StudentDTO studentDTO) throws MyStudentException {
		Student student = studentMapper.toStudentEntity(studentDTO);
		Student existingStudent = studentRepository.findById(student.getId()).orElse(null);

		User user = userRepository.findByUserEmail(existingStudent.getEmail());
		if (user != null && user.getId() != null) {
			user.setEmail(student.getEmail());
			userRepository.save(user);
		}

		existingStudent.setIndexnumber(student.getIndexnumber());
		existingStudent.setIndexyear(student.getIndexyear());
		existingStudent.setFirstname(student.getFirstname());
		existingStudent.setLastname(student.getLastname());
		existingStudent.setJmbg(student.getJmbg());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setAddress(student.getAddress());
		existingStudent.setCurrentyearofstudy(student.getCurrentyearofstudy());
		existingStudent.setCity(student.getCity());

		studentRepository.save(existingStudent);
		return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
	}

}
