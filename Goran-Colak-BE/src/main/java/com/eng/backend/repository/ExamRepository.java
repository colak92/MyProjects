package com.eng.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer>{
	
	@Query(value = "SELECT * FROM exams e, examperiods ep WHERE e.examperiod_id = ep.id AND :examdate BETWEEN :start AND :end", nativeQuery = true)
	public List<Exam> checkExamInterval(@Param("examdate") String examdate, @Param("start") String start, @Param("end") String end);
	
	@Query(value = "SELECT * FROM exams e, examperiods ep, subjects s, students st WHERE e.examperiod_id = ep.id AND e.subject_id = s.id AND e.student_id = st.id AND e.examperiod_id = :examperiodid AND e.subject_id = :subjectid AND e.student_id = :studentid",nativeQuery = true)
	public List<Exam> checkExamSubjectExists(@Param("examperiodid") Integer examperiodid, @Param("subjectid") Integer subjectid, @Param("studentid") Integer studentid);

	@Query(value = "SELECT * FROM exams e, professors p WHERE e.professor_id = p.id AND p.id = :professorid", nativeQuery = true)
	public List<Exam> checkProfessorHasExam(@Param("professorid") Integer professorid);
	
	@Query(value = "SELECT * FROM exams e, students s WHERE e.student_id = s.id AND s.id = :studentid", nativeQuery = true)
	public List<Exam> checkStudentHasExam(@Param("studentid") Integer studentid);
	
	@Query(value = "SELECT * FROM exams e, subjects s WHERE e.subject_id = s.id AND s.id = :subjectid", nativeQuery = true)
	public List<Exam> checkSubjectHasExam(@Param("subjectid") Integer studentid);

}
