package com.cts.iiht.fsd.workouttracker.framework.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="workout_category")
public class WorkoutCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private Long categoryId;
	
	@Column(name="category_name",nullable=false)
	private String categoryName;
	
	@OneToMany(mappedBy="category",fetch=FetchType.LAZY)
	private Set<WorkoutCollection> workoutCollectionSet = new HashSet<WorkoutCollection>();
	
	public WorkoutCategory() {
		
	}
	
	public WorkoutCategory(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public WorkoutCategory(Long categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
