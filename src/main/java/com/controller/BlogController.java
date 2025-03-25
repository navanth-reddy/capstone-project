package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.BlogDTO;
import com.service.BlogService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController // Marks this class as a REST API controller
@RequestMapping("/api/blogs")  // Base URL for all endpoints in this controller

public class BlogController {

	private final BlogService blogService;

	// Constructor-based dependency injection of BlogService
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	/**
	 * Endpoint to create a new blog.
	 * 
	 * ResponseEntity containing the created BlogDTO and HTTP status 201 (Created).
	 */

	@PostMapping
	@Tag(name="Add the Blog") 
	public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDto) {
		BlogDTO createdBlog = blogService.createBlog(blogDto);
		return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint to fetch all blogs.
	 * ResponseEntity containing the list of all BlogDTOs and HTTP status 200 (OK).
	 */
	@GetMapping
	@Tag(name="Get all the Blogs")
	public ResponseEntity<List<BlogDTO>> getAllBlogs() {
		List<BlogDTO> blogs = blogService.getAllBlogs();
		return ResponseEntity.ok(blogs);
	}
	
	
	/**
	 * Endpoint to fetch a specific blog by ID.
	 * 
	 * ResponseEntity containing the BlogDTO and HTTP status 200 (OK).
	 *         If the blog is not found, an exception is thrown by the service layer.
	 */
	@GetMapping("/{id}")
	@Tag(name="Get the Blog By id")
	public ResponseEntity<?> getBlogById(@PathVariable Long id) {
		BlogDTO blog = blogService.getBlogById(id);
		return new ResponseEntity<BlogDTO>(blog, HttpStatus.OK);
	}

	/**
	 * Endpoint to update an existing blog by ID.
	 * id The ID of the blog to update.
	 * blogDto The updated blog data.
	 * ResponseEntity containing the updated BlogDTO and HTTP status 200 (OK).
	 */
	@PutMapping("/{id}")
	@Tag(name="Update the Blog")
	public ResponseEntity<?> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDTO blogDto) {
		BlogDTO blog = blogService.updateBlog(id, blogDto);
		return new ResponseEntity<BlogDTO>(blog, HttpStatus.OK);
	}

	/**
	 * Endpoint to delete a blog by ID.
	 * id The ID of the blog to delete.
	 * ResponseEntity with a success message and HTTP status 200 (OK).
	 */
	@DeleteMapping("/{id}")
	@Tag(name="Delete the Blog")
	public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
		blogService.deleteBlog(id);
		return ResponseEntity.ok("The blog deleted Successfully with id : " + id);
	}

}
