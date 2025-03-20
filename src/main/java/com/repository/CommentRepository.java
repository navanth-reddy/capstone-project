package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long>{
	
	// Extends JpaRepository to inherit standard CRUD methods.
	
	
	List<CommentEntity> findByBlogId(Long blogId);
}
