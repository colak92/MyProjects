package com.eng.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
