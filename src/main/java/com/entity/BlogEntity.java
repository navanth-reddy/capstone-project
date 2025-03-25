package com.entity;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity // Marks this class as a JPA entity, meaning it maps to a database table.
@Table(name = "blogs") // Specifies that this entity maps to the "blogs" table in the database.
public class BlogEntity {
	
	@Id    // Marks this field as the primary key.
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Uses auto-increment strategy for ID.
	@Column(name = "blog_id")  // Maps this field to the "blog_id" column in the "blogs" table.
	private Long id;

	@NotNull
	private String title;

	@NotNull
	private String content;

	// Defines a one-to-many relationship with CommentEntity.
    // `mappedBy = "blog"` means this relationship is managed by the `blog` field in CommentEntity.
    // `cascade = CascadeType.ALL` ensures that any changes (persist, remove, etc.) to BlogEntity
    // are also applied to related CommentEntity objects.
    // `fetch = FetchType.LAZY` means that comments will be loaded only when explicitly accessed.
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CommentEntity> comments;

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

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

}
