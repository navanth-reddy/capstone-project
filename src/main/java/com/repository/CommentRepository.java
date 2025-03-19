package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity,Long>{
	List<CommentEntity> findByBlogId(Long blogId);
}
