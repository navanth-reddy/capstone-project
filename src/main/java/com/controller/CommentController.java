package com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CommentDTO;
import com.service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController  // Marks this class as a REST API controller
@RequestMapping("/api/blogs")  // Base URL for all endpoints in this controller
public class CommentController {
	
	private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    
    
    /**
     * Endpoint to add a comment to a specific blog.
     * blogId The ID of the blog to which the comment belongs.
     * commentDto The comment data received in the request body.
     * ResponseEntity containing the saved CommentDTO and HTTP status 200 (OK).
     */
    @PostMapping("/{blogId}")
    @Tag(name="Add the Comment")
    public ResponseEntity<CommentDTO> postComment(@PathVariable Long blogId, @Valid @RequestBody CommentDTO commentDto) {
    	commentDto.setBlogId(blogId); 
        return ResponseEntity.ok(commentService.postComment(blogId,commentDto));
    }
    
    
    /**
     * Endpoint to fetch all comments for a specific blog.
     * blogId The ID of the blog.
     * ResponseEntity containing the list of comments for the blog and HTTP status 200 (OK).
     */
    @GetMapping("/{blogId}/comments")
    @Tag(name="Get all the Comments based on blog id")
    public ResponseEntity<List<CommentDTO>> getCommentsByBlogId(@PathVariable Long blogId) {
        List<CommentDTO> comments = commentService.getCommentsByBlogId(blogId);
        return ResponseEntity.ok(comments);
    }
    
    
    /**
     * Endpoint to fetch a specific comment by its ID.
     * commentId The ID of the comment.
     * ResponseEntity containing the CommentDTO and HTTP status 200 (OK).
     */
    @GetMapping("/comment/{commentId}")
    @Tag(name="Get the comment by id")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long commentId) {
        CommentDTO commentDto = commentService.getCommentById(commentId);
        return ResponseEntity.ok(commentDto);
    }
}

