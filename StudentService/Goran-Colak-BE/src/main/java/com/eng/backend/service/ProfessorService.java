package com.eng.backend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eng.backend.dto.ProfessorDTO;
import com.eng.backend.exception.MyProfessorException;
import com.eng.backend.mapper.ProfessorMapper;
import com.eng.backend.model.Exam;
import com.eng.backend.model.Professor;
import com.eng.backend.model.Role;
import com.eng.backend.model.RoleEnum;
import com.eng.backend.model.User;
import com.eng.backend.repository.ExamRepository;
import com.eng.backend.repository.ProfessorRepository;
import com.eng.backend.repository.RoleRepository;
import com.eng.backend.repository.UserRepository;

@Service
public class ProfessorService {

	private ProfessorRepository professorRepository;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private ExamRepository examRepository;
	private PasswordEncoder passwordEncoder;
	private ProfessorMapper professorMapper;

	@Autowired
	public ProfessorService(ProfessorRepository professorRepository, 
							UserRepository userRepository,
							RoleRepository roleRepository, 
							ExamRepository examRepository, 
							PasswordEncoder passwordEncoder,
							ProfessorMapper professorMapper) {

		this.professorRepository = professorRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.examRepository = examRepository;
		this.passwordEncoder = passwordEncoder;
		this.professorMapper = professorMapper;
	}

	public ResponseEntity<ProfessorDTO> saveProfessor(ProfessorDTO professorDTO) {
		Professor professor = professorMapper.toProfessorEntity(professorDTO);

		User user = new User();
		user.setUsername(professor.getFirstname().toLowerCase() + professor.getLastname().toLowerCase());
		user.setEmail(professor.getEmail());
		user.setPassword(passwordEncoder.encode("professor"));

		Role studentRole = roleRepository.findByName(RoleEnum.ROLE_PROFESSOR).orElse(null);
		Set<Role> roles = new HashSet<>();
		roles.add(studentRole);
		user.setRoles(roles);

		List<Professor> professors = new ArrayList<>();
		professors.add(professor);
		user.setProfessors(professors);
		userRepository.save(user);

		professorRepository.save(professor);

		return ResponseEntity.status(HttpStatus.OK).body(professorDTO);
	}

	public ResponseEntity<List<ProfessorDTO>> getProfessors() {
		List<ProfessorDTO> professors = professorMapper.toProfessorDTOs(professorRepository.findAll());
		return ResponseEntity.ok(professors);
	}

	// sorting and pagination - START

	public Page<Professor> getPageProfessors(Pageable pageable) {
		return professorRepository.findAll(pageable);
	}

	// sorting and pagination - END

	public ResponseEntity<ProfessorDTO> getProfessorById(int id) {
		Optional<Professor> professor = professorRepository.findById(id);
		ProfessorDTO professorDTO = professorMapper.toProfessorDTO(professor.get());
		return ResponseEntity.ok(professorDTO);
	}

	public ResponseEntity<ProfessorDTO> deleteProfessorById(int id) {
		Professor professor = professorRepository.findById(id).get();
		User user = userRepository.findByUserEmail(professor.getEmail());

		List<Exam> examList = new ArrayList<>();
		examList = examRepository.checkProfessorHasExam(professor.getId());

		if (examList != null && !examList.isEmpty() && examList.size() == 1) {
			String examName = examList.get(0).getName();
			throw new MyProfessorException(examName);
		}

		userRepository.deleteById(user.getId());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	public ResponseEntity<ProfessorDTO> updateProfessor(ProfessorDTO professorDTO) {
		Professor professor = professorMapper.toProfessorEntity(professorDTO);
		Professor existingProfessor = professorRepository.findById(professor.getId()).orElse(null);

		User user = userRepository.findByUserEmail(existingProfessor.getEmail());
		if (user != null && user.getId() != null) {
			user.setEmail(professor.getEmail());
			userRepository.save(user);
		}

		existingProfessor.setFirstname(professor.getFirstname());
		existingProfessor.setLastname(professor.getLastname());
		existingProfessor.setEmail(professor.getEmail());
		existingProfessor.setAddress(professor.getAddress());
		existingProfessor.setPhone(professor.getPhone());
		existingProfessor.setReelectiondate(professor.getReelectiondate());
		existingProfessor.setCity(professor.getCity());
		existingProfessor.setTitle(professor.getTitle());
		existingProfessor.setSubjectList(professor.getSubjectList());

		professorRepository.save(existingProfessor);
		return ResponseEntity.status(HttpStatus.OK).body(professorDTO);
	}

}
