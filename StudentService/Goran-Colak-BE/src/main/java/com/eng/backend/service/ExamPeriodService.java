package com.eng.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eng.backend.dto.ExamPeriodDTO;
import com.eng.backend.exception.MyExamPeriodException;
import com.eng.backend.mapper.ExamPeriodMapper;
import com.eng.backend.model.ExamPeriod;
import com.eng.backend.repository.ExamPeriodRepository;

@Service
public class ExamPeriodService {

	private ExamPeriodRepository examPeriodRepository;
	private ExamPeriodMapper examPeriodMapper;

	@Autowired
	public ExamPeriodService(ExamPeriodRepository examPeriodRepository, 
							 ExamPeriodMapper examPeriodMapper) {

		this.examPeriodRepository = examPeriodRepository;
		this.examPeriodMapper = examPeriodMapper;
	}

	public ResponseEntity<ExamPeriodDTO> saveExamPeriod(ExamPeriodDTO examPeriodDTO) {
		ExamPeriod examPeriod = examPeriodMapper.toExamPeriodEntity(examPeriodDTO);

		List<ExamPeriod> examPeriodActiveList = new ArrayList<>();
		examPeriodActiveList = examPeriodRepository.findAllActive();

		String startDate = examPeriod.getExamstart().toString();
		String endDate = examPeriod.getExamend().toString();

		List<ExamPeriod> examPeriodIntervalList = new ArrayList<>();
		examPeriodIntervalList = examPeriodRepository.checkExamPeriodInterval(startDate, endDate);

		if (examPeriodIntervalList != null && !examPeriodIntervalList.isEmpty()) {
			System.out.println("Already exists this interval.");
			throw new MyExamPeriodException(startDate, endDate);
		}

		if (examPeriodActiveList.size() == 1 && examPeriod.getStatus() == true) {
			System.out.println("Already exists active exam period.");
			throw new MyExamPeriodException();
		}

		examPeriodRepository.save(examPeriod);
		return ResponseEntity.status(HttpStatus.OK).body(examPeriodDTO);
	}

	public ResponseEntity<List<ExamPeriodDTO>> getExamPeriods() {
		List<ExamPeriodDTO> examPeriods = examPeriodMapper.toExamPeriodDTOs(examPeriodRepository.findAll());
		return ResponseEntity.ok(examPeriods);
	}

	public ResponseEntity<List<ExamPeriodDTO>> getActiveExamPeriods() {
		List<ExamPeriodDTO> examPeriodsActive = examPeriodMapper.toExamPeriodDTOs(examPeriodRepository.findAllActive());
		return ResponseEntity.ok(examPeriodsActive);
	}

	public ResponseEntity<ExamPeriodDTO> getExamPeriodById(int id) {
		Optional<ExamPeriod> examPeriod = examPeriodRepository.findById(id);
		ExamPeriodDTO examPeriodDTO = examPeriodMapper.toExamPeriodDTO(examPeriod.get());
		return ResponseEntity.ok(examPeriodDTO);
	}

	public ResponseEntity<ExamPeriodDTO> deleteExamPeriod(int id) {
		examPeriodRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	public ResponseEntity<ExamPeriodDTO> updateExamPeriod(ExamPeriodDTO examPeriodDTO) {

		ExamPeriod examPeriod = examPeriodMapper.toExamPeriodEntity(examPeriodDTO);
		ExamPeriod existingExamPeriod = examPeriodRepository.findById(examPeriod.getId()).orElse(null);

		List<ExamPeriod> examPeriodActiveList = new ArrayList<>();
		examPeriodActiveList = examPeriodRepository.findAllActive();

		if (examPeriodActiveList != null && !examPeriodActiveList.isEmpty()) {
			if (examPeriodActiveList.size() == 1 && examPeriod.getStatus() == true) {
				ExamPeriod examPeriodObject1 = examPeriodActiveList.get(0);

				if (examPeriod.getId() != examPeriodObject1.getId()) {
					System.out.println("Already exists active exam period.");
					throw new MyExamPeriodException();
				}
			}
		}

		String startDate = examPeriod.getExamstart().toString();
		String endDate = examPeriod.getExamend().toString();

		List<ExamPeriod> examPeriodIntervalList = new ArrayList<>();
		examPeriodIntervalList = examPeriodRepository.checkExamPeriodInterval(startDate, endDate);

		if (examPeriodIntervalList != null && !examPeriodIntervalList.isEmpty()) {
			ExamPeriod examPeriodObject2 = examPeriodIntervalList.get(0);

			if (examPeriod.getId() != examPeriodObject2.getId()) {
				System.out.println("Already exists this interval.");
				throw new MyExamPeriodException(startDate, endDate);
			}
		}

		existingExamPeriod.setName(examPeriod.getName());
		existingExamPeriod.setExamstart(examPeriod.getExamstart());
		existingExamPeriod.setExamend(examPeriod.getExamend());
		existingExamPeriod.setStatus(examPeriod.getStatus());

		examPeriodRepository.save(examPeriod);
		return ResponseEntity.status(HttpStatus.OK).body(examPeriodDTO);
	}

}
