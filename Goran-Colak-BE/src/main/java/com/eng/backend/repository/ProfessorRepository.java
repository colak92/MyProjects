package com.eng.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
	
	public Professor findByEmail(String email);
	
	@Query(value = "SELECT * FROM professors p INNER JOIN professor_subjects ps ON p.id = ps.professor_id AND ps.subject_id = :subjectid", nativeQuery = true)
	public List<Professor> findAllProfessorsForSubject(Integer subjectid);

}
