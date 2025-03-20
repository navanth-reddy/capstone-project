package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.CommentDTO;
import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.exceptions.ResourceNotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

	public CommentEntity addComment(CommentEntity commentEntity) {
		return commentRepository.save(commentEntity);
	}
	
	public CommentDTO postComment(Long id,CommentDTO commentDTO) {
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Blog Available with Id : " + commentDTO.getBlogId()));

        CommentEntity comment = new CommentEntity();
        comment.setComment(commentDTO.getComment());
        comment.setBlog(blog);

        CommentEntity savedComment = commentRepository.save(comment);
        commentDTO.setId(savedComment.getId());
        return commentDTO;
    }
	
	public CommentDTO getCommentById(Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with the blog id "+commentId));

        return mapToDTO(comment);
    }
	public List<CommentDTO> getCommentsByBlogId(Long blogId) {
        // Check if blog exists
        if (!blogRepository.existsById(blogId)) {
            throw new ResourceNotFoundException("Blog not found with ID: " + blogId);
        }

        // Fetch comments and map to DTO
        return commentRepository.findByBlogId(blogId)
                .stream()
                .map(comment -> mapToDTO(comment))
                .collect(Collectors.toList());
    }
	
	private CommentDTO mapToDTO(CommentEntity comment) {
        CommentDTO dto=new CommentDTO();
        
        dto.setId(comment.getId());
        dto.setComment(comment.getComment());
        dto.setBlogId(comment.getBlog().getId());
        return dto;
    }
	
}
