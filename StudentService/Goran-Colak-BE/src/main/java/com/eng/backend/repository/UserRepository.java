package com.eng.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username); 

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);
	
	@Query(value = "select * from users u where u.email = :userEmail", nativeQuery = true)
	public User findByUserEmail(@Param ("userEmail") String userEmail);
	
	
}
