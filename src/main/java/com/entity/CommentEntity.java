package com.entity;

import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity   // Marks this class as a JPA entity that maps to a database table
@Table(name = "comments")  // Specifies that this entity maps to the "comments" table in the database.
public class CommentEntity {

	@Id   // Specifies that this field is the primary key.
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Uses auto-increment strategy for ID.
	@Column(name = "comment_id")  // Maps this field to the "comment_id" column in the "comments" table.
	private Long id;

	@NotNull
	private String comment;

	@ManyToOne  // Defines a many-to-one relationship (many comments belong to one blog).
	// Specifies the foreign key column in the "comments" table that references the "blogs" table.
    // `nullable = false` ensures that every comment must be associated with a blog.
	@JoinColumn(name = "blog", nullable = false)
	private BlogEntity blog;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BlogEntity getBlog() {
		return blog;
	}

	public void setBlog(BlogEntity blog) {
		this.blog = blog;
	}

	

}
