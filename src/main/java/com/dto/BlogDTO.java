package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BlogDTO {
	
	private Long id;
	
	@NotBlank(message="Title must not be Blank")
	@Size(min=3,max=100,message="title must be between 3 and 100 characters")
	private String title;
	
	@NotBlank(message="Content must not be blank")
	@Size(min=3,max=200,message="Content must be between 3 and 200 characters")
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
