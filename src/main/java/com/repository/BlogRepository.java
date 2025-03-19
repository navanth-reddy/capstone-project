package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dto.BlogDTO;
import com.entity.BlogEntity;

import jakarta.validation.Valid;

public interface BlogRepository extends JpaRepository<BlogEntity,Long> {

	
	

}
