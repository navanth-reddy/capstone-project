package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.CommentDTO;
import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.exceptions.InvalidBlogIdException;
import com.exceptions.ResourceNotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

@Service // Marks this class as a Spring Service, making it a managed bean.
public class CommentService {

	private final CommentRepository commentRepository;
	private final BlogRepository blogRepository;

	// Constructor-based Dependency Injection
	public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
		this.commentRepository = commentRepository;
		this.blogRepository = blogRepository;
	}

	// Saves a CommentEntity directly to the database.
	public CommentEntity addComment(CommentEntity commentEntity) {
		return commentRepository.save(commentEntity);
	}

	/*
	 * Checks if id is valid.
	 * 
	 * Fetches the BlogEntity to ensure it exists.
	 * 
	 * Saves the comment in the database.
	 * 
	 */
	public CommentDTO postComment(Long id, CommentDTO commentDTO) {
		if (id < 0) {
			throw new InvalidBlogIdException("Negative IDs are not allowed.");
		}
		BlogEntity blog = blogRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No Blog Available with Id : " + commentDTO.getBlogId()));

		CommentEntity comment = new CommentEntity();
		comment.setComment(commentDTO.getComment());
		comment.setBlog(blog);

		CommentEntity savedComment = commentRepository.save(comment);
		commentDTO.setId(savedComment.getId());
		return commentDTO;
	}

	public CommentDTO getCommentById(Long commentId) {
		if (commentId < 0) {
			throw new InvalidBlogIdException("Negative IDs are not allowed.");
		}
		CommentEntity comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment not found with the comment id :" + commentId));

		return mapToDTO(comment);
	}

	/*
	 * Ensures commentId is valid.
	 * 
	 * Fetches the comment from the database.
	 * 
	 * Throws an error if the comment is not found.
	 */
	public List<CommentDTO> getCommentsByBlogId(Long blogId) {
		if (blogId < 0) {
			throw new InvalidBlogIdException("Negative IDs are not allowed.");
		}
		// Check if blog exists
		if (!blogRepository.existsById(blogId)) {
			throw new ResourceNotFoundException("Blog not found with ID: " + blogId);
		}

		// Fetch comments and map to DTO
		return commentRepository.findByBlogId(blogId).stream().map(comment -> mapToDTO(comment))
				.collect(Collectors.toList());
	}

	//Converts CommentEntity to CommentDTO.
	private CommentDTO mapToDTO(CommentEntity comment) {
		CommentDTO dto = new CommentDTO();

		dto.setId(comment.getId());
		dto.setComment(comment.getComment());
		dto.setBlogId(comment.getBlog().getId());
		return dto;
	}

}
