package com.controller;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

	@GetMapping()
	public String greet() {
		return "Hello welcome";
	}

	private final BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@PostMapping
	public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDto) {
		BlogDTO createdBlog = blogService.createBlog(blogDto);
		return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BlogDTO> getBlogById(@PathVariable Long id) {
		return ResponseEntity.ok(blogService.getBlogById(id));
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<BlogDTO> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDTO blogDto) {
        return ResponseEntity.ok(blogService.updateBlog(id, blogDto));
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.ok(true);
    }

}
