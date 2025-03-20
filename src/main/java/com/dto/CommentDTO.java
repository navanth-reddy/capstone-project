package com.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//Defines a Data Transfer Object (DTO) for comments.
public class CommentDTO {
	
	private Long id;
	
	 
	private Long blogId;
	
	@NotBlank
	@Size(min=3,max=200,message="Comment must be between 3 and 200 characters")
	private String comment;

	public Long getId() {
		return id; 
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

}
