package com.eng.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.ExamPeriod;

@Repository
public interface ExamPeriodRepository extends JpaRepository<ExamPeriod, Integer> {
	
	@Query(value = "SELECT * FROM examperiods ep WHERE ep.status = 1;", nativeQuery = true)
	public List<ExamPeriod> findAllActive();
	
	@Query(value = "SELECT * FROM examperiods ep WHERE ep.examstart <= :end AND ep.examend >= :start", nativeQuery = true)
	public List<ExamPeriod> checkExamPeriodInterval(@Param ("start") String start, @Param ("end") String end);

}

