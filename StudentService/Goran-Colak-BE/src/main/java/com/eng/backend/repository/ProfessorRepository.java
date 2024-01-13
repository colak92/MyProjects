package com.eng.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
