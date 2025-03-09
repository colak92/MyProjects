package com.eng.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eng.backend.model.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer>{
	
	public Optional<Title> findByName(String titleName);

}
