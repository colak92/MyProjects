package com.eng.backend.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eng.backend.exception.MyProfileException;
import com.eng.backend.model.Professor;
import com.eng.backend.model.Role;
import com.eng.backend.model.RoleEnum;
import com.eng.backend.model.Student;
import com.eng.backend.model.User;
import com.eng.backend.repository.ProfessorRepository;
import com.eng.backend.repository.StudentRepository;
import com.eng.backend.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepository;
	private StudentRepository studentRepository;
	private ProfessorRepository professorRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(@Lazy UserRepository userRepository, @Lazy StudentRepository studentRepository,
			@Lazy ProfessorRepository professorRepository, @Lazy PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.studentRepository = studentRepository;
		this.professorRepository = professorRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return User.build(user);
	}

	public User getUserById(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		return User.build(user);
	}

	public User updateUser(User user) {

		if (!passwordEncoder.matches(user.getPassword(), passwordEncoder.encode(user.getConfirmPassword()))) {
			throw new MyProfileException();
		}

		User existingUser = userRepository.findById(user.getId()).orElse(null);

		if (existingUser.getRoles() != null) {
			Set<Role> roles = existingUser.getRoles();

			if (roles.iterator().next().getName().equals(RoleEnum.STUDENT)) {
				if (user.getEmail() != null && !user.getEmail().isEmpty()) {
					Student s = studentRepository.findByEmail(existingUser.getEmail());
					s.setEmail(user.getEmail());
					studentRepository.save(s);
				}
			}

			if (roles.iterator().next().getName().equals(RoleEnum.PROFESSOR)) {
				if (user.getEmail() != null && !user.getEmail().isEmpty()) {
					Professor p = professorRepository.findByEmail(existingUser.getEmail());
					p.setEmail(user.getEmail());
					professorRepository.save(p);
				}
			}
		}
		
		existingUser.setUsername(user.getUsername());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepository.save(existingUser);
		return User.build(existingUser);
	}

}
