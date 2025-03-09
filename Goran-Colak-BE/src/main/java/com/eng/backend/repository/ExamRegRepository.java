package com.eng.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.ExamReg;

@Repository
public interface ExamRegRepository extends JpaRepository<ExamReg, Integer>{
	
	@Query(value = "SELECT * FROM examregs er, students st WHERE er.student_id = st.id AND st.id = :studentid", nativeQuery = true)
	public List<ExamReg> findRegExamsForStudent(@Param("studentid") Integer studentid);
		
	@Query(value = "SELECT * FROM examregs ereg, students stud WHERE ereg.student_id = stud.id AND ereg.student_id = :studentid AND ereg.exam_id = :examid", nativeQuery = true)
	public List<ExamReg> checkRegExamsForStudent(@Param("studentid") Integer studentid, @Param("examid") Integer examid);

}
