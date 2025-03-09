package com.eng.backend.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eng.backend.model.Professor;
import com.eng.backend.model.Role;
import com.eng.backend.model.RoleEnum;
import com.eng.backend.model.Student;
import com.eng.backend.model.Title;
import com.eng.backend.model.User;
import com.eng.backend.repository.ProfessorRepository;
import com.eng.backend.repository.RoleRepository;
import com.eng.backend.repository.StudentRepository;
import com.eng.backend.repository.TitleRepository;
import com.eng.backend.repository.UserRepository;
import com.eng.backend.request.LoginRequest;
import com.eng.backend.request.SignupRequest;
import com.eng.backend.response.JwtResponse;
import com.eng.backend.response.MessageResponse;
import com.eng.backend.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
	
	private static final String OLD_PASSWORD = "password";

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	ProfessorRepository professorRepository;
	
	@Autowired
	TitleRepository titleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		if (encoder.matches(loginRequest.getPassword(), encoder.encode(OLD_PASSWORD))) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: You must change old password!"));
		}

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		User user = (User) authentication.getPrincipal();
		List<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, user.getId(), user.getUsername(), user.getEmail(), roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = new HashSet<>();
		strRoles.add(signUpRequest.getRoleName());
		Set<Role> roles = new HashSet<>();

		if (strRoles != null && !strRoles.isEmpty()) {
			strRoles.forEach(role -> {
				switch (role) {
				case "Student":
					Role studentRole = roleRepository.findByName(RoleEnum.STUDENT).orElseThrow(() -> new RuntimeException("Error: Student role is not found."));
					roles.add(studentRole);
					break;
				case "Professor":
					Role professorRole = roleRepository.findByName(RoleEnum.PROFESSOR).orElseThrow(() -> new RuntimeException("Error: Professor role is not found."));
					roles.add(professorRole);
					break;
				default:
					Role adminRole = roleRepository.findByName(RoleEnum.ADMIN).orElseThrow(() -> new RuntimeException("Error: Admin role is not found."));
					roles.add(adminRole);
				}
			});
		}
		
		user.setRoles(roles);
		
		for (Role r : roles) {
			if (r.getName().equals(RoleEnum.STUDENT)) {
				Student s = new Student();
				s.setEmail(signUpRequest.getEmail());
				
				// Not null columns
				s.setIndexnumber(signUpRequest.getIndexNumber());
				s.setIndexyear(signUpRequest.getIndexYear());
				s.setFirstname(signUpRequest.getStudentFirstName());
				s.setLastname(signUpRequest.getStudentLastName());
				s.setCurrentyearofstudy(signUpRequest.getCurrentYearoFStudy());
				studentRepository.save(s);
			}
			
			else if (r.getName().equals(RoleEnum.PROFESSOR)) {
				Professor p = new Professor();
				p.setEmail(signUpRequest.getEmail());
				
				// Not null columns
				p.setFirstname(signUpRequest.getProfessorFirstName());
				p.setLastname(signUpRequest.getProfessorLastName());
				p.setReelectiondate(signUpRequest.getReelectionDate());
				Title title = titleRepository.findByName(signUpRequest.getTitleName()).orElseThrow(() -> new RuntimeException("Error: Title is not found."));
				p.setTitle(title);
				professorRepository.save(p);
			}
		}
		
		userRepository.save(user);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
