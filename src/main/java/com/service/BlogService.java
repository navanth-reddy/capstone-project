package com.service;

import org.springframework.stereotype.Service;

import com.dto.BlogDTO;
import com.entity.BlogEntity;
import com.exceptions.ResourceNotFoundException;
import com.repository.BlogRepository;

import jakarta.validation.Valid;
@Service
public class BlogService {
	
	private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    
    public BlogDTO createBlog(@Valid BlogDTO blogDTO) {
    	BlogEntity blog = new BlogEntity();
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        BlogEntity savedBlog = blogRepository.save(blog);
        return mapToDTO(savedBlog);
    }

    public BlogDTO getBlogById(Long id) {
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id));
        return mapToDTO(blog);
    }

	
	private BlogDTO mapToDTO(BlogEntity blog) {
        BlogDTO dto = new BlogDTO();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        return dto;
    }
	
	public BlogDTO updateBlog(Long id, BlogDTO blogDto) {
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id));

        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        BlogEntity updatedBlog = blogRepository.save(blog);
        return mapToDTO(updatedBlog);
    }
	
	public void deleteBlog(Long id) {
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id));
        blogRepository.delete(blog);
    }

}
