package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

	private final BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@PostMapping
	@Tag(name="Add the Blog")
	public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDto) {
		BlogDTO createdBlog = blogService.createBlog(blogDto);
		return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
	}

	@GetMapping
	@Tag(name="Get all the Blogs")
	public ResponseEntity<List<BlogDTO>> getAllBlogs() {
		List<BlogDTO> blogs = blogService.getAllBlogs();
		return ResponseEntity.ok(blogs);
	}

	@GetMapping("/{id}")
	@Tag(name="Get the Blog By id")
	public ResponseEntity<?> getBlogById(@PathVariable Long id) {
		BlogDTO blog = blogService.getBlogById(id);
		if (blog == null) {
			return new ResponseEntity<String>("The Blog was not found with id" + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BlogDTO>(blog, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@Tag(name="Update the Blog")
	public ResponseEntity<?> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDTO blogDto) {
		BlogDTO blog = blogService.updateBlog(id, blogDto);
		if (blog == null) {
			return new ResponseEntity<String>("The Blog was not found with id" + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BlogDTO>(blog, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Tag(name="Delete the Blog")
	public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
		blogService.deleteBlog(id);
		return ResponseEntity.ok("The blog deleted Successfully with id" + id);
	}

}
