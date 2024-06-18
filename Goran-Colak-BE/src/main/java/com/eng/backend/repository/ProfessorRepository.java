package com.eng.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
	
	@Query(value = "select * from professors p where p.email = :email", nativeQuery = true)
	public Professor findProfessorByEmail(@Param ("email") String email);

}
