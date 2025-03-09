package com.eng.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	
	@Query(value = "SELECT * FROM subjects s INNER JOIN professor_subjects ps ON s.id = ps.subject_id AND ps.professor_id = :professorid", nativeQuery = true)
	public List<Subject> findAllSubjectsForProfessor(Integer professorid);
	
	@Query(value = "SELECT * FROM subjects s WHERE NOT EXISTS (SELECT 1 FROM professor_subjects ps WHERE s.id = ps.subject_id)", nativeQuery = true)
	public List<Subject> findSubjectsWithoutProfessor();

}
