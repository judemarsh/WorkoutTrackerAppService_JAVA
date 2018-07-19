package com.cts.iiht.fsd.workouttracker.category.to;

import java.io.Serializable;

public class WorkoutCategoryTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long categoryId;
	
	private String categoryName;

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
