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

import com.eng.backend.model.City;
import com.eng.backend.repository.CityRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CityController {
	
	@Autowired
	CityRepository cityRepository;
	
	@GetMapping("/cities")
	public ResponseEntity<List<City>> getAllCities() {
		try {
			List<City> cities = new ArrayList<>();
			cityRepository.findAll().forEach(cities::add);

			if (cities.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(cities, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cities/{id}")
	public ResponseEntity<City> getCityById(@PathVariable("id") Integer id) {
		Optional<City> cityData = cityRepository.findById(id);

		if (cityData.isPresent()) {
			return new ResponseEntity<>(cityData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/cities")
	public ResponseEntity<City> createCity(@RequestBody City city) {
		try {
			City cityObject = cityRepository.save(city);

			return new ResponseEntity<>(cityObject, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/cities/{id}")
	public void deleteCity(@PathVariable("id") Integer id) {
		cityRepository.deleteById(id);
	}

}
