package com.eng.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, PagingAndSortingRepository<Student, Integer> {
	
	public Student findByIndexnumber(String indexnumber);

}
