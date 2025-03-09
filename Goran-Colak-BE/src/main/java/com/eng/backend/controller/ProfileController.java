package com.eng.backend.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eng.backend.model.Role;
import com.eng.backend.model.RoleEnum;
import com.eng.backend.model.User;
import com.eng.backend.repository.RoleRepository;
import com.eng.backend.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:6060")
public class ProfileController {

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;

	// get user by id - rest api

	@GetMapping("/profile/{id}")
	public ResponseEntity<User> getProfileById(@PathVariable("id") Integer id) {
		Optional<User> userData = Optional.ofNullable(userService.getUserById(id));

		User user = userData.get();
		Collection<? extends GrantedAuthority> authorities;
		authorities = user.getAuthorities();

		GrantedAuthority grantedAuthority = authorities.stream().toList().get(0);
		String roleAsString = grantedAuthority.getAuthority();

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName(RoleEnum.valueOf(roleAsString)).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		user.setRoles(roles);

		if (userData.isPresent()) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/profile/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User authenticatedUser = (User) authentication.getPrincipal();

		// Verify that the authenticated user is the same as the user being updated
		if (!authenticatedUser.getId().equals(id)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		// Update the user information
		user.setId(id);
		userService.updateUser(user);

		return ResponseEntity.ok().build();
	}

}
