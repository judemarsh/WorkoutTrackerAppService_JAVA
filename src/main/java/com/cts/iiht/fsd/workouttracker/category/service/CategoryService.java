package com.cts.iiht.fsd.workouttracker.category.service;

import java.util.List;

import com.cts.iiht.fsd.workouttracker.category.to.WorkoutCategoryTO;

public interface CategoryService {

	public List<WorkoutCategoryTO> getCategoryList();
	
	public WorkoutCategoryTO getCategoryById(Long categoryId);
	
	public Long saveCategory(WorkoutCategoryTO workCategory);
	
	public boolean deleteCategory(Long categoryId);
}
