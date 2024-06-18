package com.eng.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.ExamReg;

@Repository
public interface ExamRegRepository extends JpaRepository<ExamReg, Integer>{

}
