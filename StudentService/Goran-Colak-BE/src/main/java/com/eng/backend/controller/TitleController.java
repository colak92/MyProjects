package com.eng.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eng.backend.model.Title;
import com.eng.backend.repository.TitleRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TitleController {
	
	@Autowired
	TitleRepository titleRepository;
	
	@GetMapping("/titles")
	public ResponseEntity<List<Title>> getAllCities() {
		try {
			List<Title> titles = new ArrayList<>();
			titleRepository.findAll().forEach(titles::add);

			if (titles.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(titles, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/titles/{id}")
	public ResponseEntity<Title> getTitleById(@PathVariable("id") Integer id) {
		Optional<Title> titleData = titleRepository.findById(id);

		if (titleData.isPresent()) {
			return new ResponseEntity<>(titleData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/titles")
	public ResponseEntity<Title> createTitle(@RequestBody Title title) {
		try {
			Title titleObject = titleRepository.save(title);

			return new ResponseEntity<>(titleObject, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/titles/{id}")
	public void deleteTitle(@PathVariable("id") Integer id) {
		titleRepository.deleteById(id);
	}

}
