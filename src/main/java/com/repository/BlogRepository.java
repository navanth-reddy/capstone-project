package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.BlogEntity;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

     // Extends JpaRepository to inherit standard CRUD methods.

	// JpaRepository provides the following methods out of the box:
	// - findAll(): Retrieves all blogs.
	// - findById(Long id): Finds a blog by its ID.
	// - save(BlogEntity blog): Saves or updates a blog.
	// - deleteById(Long id): Deletes a blog by its ID.

	// You can define custom queries here if needed

}
