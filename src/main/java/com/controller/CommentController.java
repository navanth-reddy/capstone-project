package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CommentDTO;
import com.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blogs")
public class CommentController {
	
	private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{blogId}")
    public ResponseEntity<CommentDTO> postComment(@PathVariable Long blogId, @Valid @RequestBody CommentDTO commentDto) {
    	commentDto.setBlogId(blogId); 
        return ResponseEntity.ok(commentService.postComment(blogId,commentDto));
    }
    
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long commentId) {
        CommentDTO commentDto = commentService.getCommentById(commentId);
        return ResponseEntity.ok(commentDto);
    }
}

