package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.BlogDTO;
import com.entity.BlogEntity;
import com.exceptions.InvalidBlogIdException;
import com.exceptions.ResourceNotFoundException;
import com.repository.BlogRepository;

import jakarta.validation.Valid;

@Service // Marks this class as a Spring service component, making it eligible for dependency injection.
public class BlogService {

	private final BlogRepository blogRepository;

	// Constructor-based Dependency Injection
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	/*
	 * Accepts a BlogDTO, converts it to BlogEntity, saves it in the database, and
	 * returns the saved entity as a BlogDTO.
	 */

	public BlogDTO createBlog(@Valid BlogDTO blogDTO) {
		BlogEntity blog = new BlogEntity();
		blog.setTitle(blogDTO.getTitle());
		blog.setContent(blogDTO.getContent());
		BlogEntity savedBlog = blogRepository.save(blog);
		return mapToDTO(savedBlog);
	}

	/*
	 * Retrieves all blogs from the database.
	 * 
	 * Converts each BlogEntity to BlogDTO.
	 * 
	 * Returns a list of DTOs.
	 */
	public List<BlogDTO> getAllBlogs() {
		return blogRepository.findAll().stream().map(blog -> mapToDTO(blog)).collect(Collectors.toList());
	}

	/*
	 * Checks if id is negative (invalid).
	 * 
	 * Fetches blog from the database.
	 * 
	 * Throws ResourceNotFoundException if not found.
	 * 
	 * Returns the blog as a BlogDTO.
	 */
	public BlogDTO getBlogById(Long id) {
		if (id < 0) {
			throw new InvalidBlogIdException("Negative IDs are not allowed.");
		}

		BlogEntity blog = blogRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id));

		return mapToDTO(blog);

	}

	/*
	 * Converts a BlogEntity to BlogDTO.
	 * 
	 * Used to maintain separation of concerns between entity and DTO layers.
	 */
	private BlogDTO mapToDTO(BlogEntity blog) {
		BlogDTO dto = new BlogDTO();
		dto.setId(blog.getId());
		dto.setTitle(blog.getTitle());
		dto.setContent(blog.getContent());
		return dto;
	}

	/*
	 * Ensures id is valid.
	 * 
	 * Retrieves the blog by id and updates its title & content.
	 * 
	 * Saves the updated blog and returns it.
	 */
	public BlogDTO updateBlog(Long id, BlogDTO blogDto) {
		if (id < 0) {
			throw new InvalidBlogIdException("Negative IDs are not allowed.");
		}
		BlogEntity blog = blogRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id));

		blog.setTitle(blogDto.getTitle());
		blog.setContent(blogDto.getContent());
		BlogEntity updatedBlog = blogRepository.save(blog);
		return mapToDTO(updatedBlog);
	}

	/*
	 * Ensures id is valid.
	 * 
	 * Deletes the blog if it exists.
	 * 
	 * Throws an error if the blog is not found.
	 */
	public void deleteBlog(Long id) {
		if (id < 0) {
			throw new InvalidBlogIdException("Negative IDs are not allowed.");
		}
		BlogEntity blog = blogRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id));
		blogRepository.delete(blog);
	}

}
