package com.eng.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username); 

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);
	
	public User findByEmail(String email);
	
}
