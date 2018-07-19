package com.cts.iiht.fsd.workouttracker.category.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cts.iiht.fsd.workouttracker.category.repository.CategoryRepository;
import com.cts.iiht.fsd.workouttracker.category.service.CategoryService;
import com.cts.iiht.fsd.workouttracker.category.to.WorkoutCategoryTO;
import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutCategory;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository workoutCategoryRepository;
	
	public List<WorkoutCategoryTO> getCategoryList(){
		List<WorkoutCategory> workoutCategoryList = workoutCategoryRepository.findAll();
		List<WorkoutCategoryTO> categoryTOList = new ArrayList<WorkoutCategoryTO>();
		if(workoutCategoryList != null && !workoutCategoryList.isEmpty()) {
			Iterator<WorkoutCategory> workoutCategoryItr = workoutCategoryList.iterator();
			while(workoutCategoryItr.hasNext()) {
				WorkoutCategory workoutCategory = workoutCategoryItr.next();
				WorkoutCategoryTO workoutCategoryTO = new WorkoutCategoryTO();
				workoutCategoryTO.setCategoryId(workoutCategory.getCategoryId());
				workoutCategoryTO.setCategoryName(workoutCategory.getCategoryName());
				categoryTOList.add(workoutCategoryTO);
			}
		}
		return categoryTOList;
	}
	
	public WorkoutCategoryTO getCategoryById(Long categoryId) {
		WorkoutCategoryTO workoutCategoryTO = new WorkoutCategoryTO();
		Optional<WorkoutCategory> workCategory = workoutCategoryRepository.findById(categoryId);
		if(workCategory != null && workCategory.isPresent()) {
			WorkoutCategory workoutCategoryObj = workCategory.get();
			workoutCategoryTO.setCategoryId(workoutCategoryObj.getCategoryId());
			workoutCategoryTO.setCategoryName(workoutCategoryObj.getCategoryName());
		}
		return workoutCategoryTO;
	}
	
	public Long saveCategory(WorkoutCategoryTO workCategoryTO) {
		WorkoutCategory workCategory = null;
		if(StringUtils.isEmpty(workCategoryTO.getCategoryId())) {
			workCategory = new WorkoutCategory();
		} else {
			workCategory = workoutCategoryRepository.findById(workCategoryTO.getCategoryId()).get();
		}
		workCategory.setCategoryName(workCategoryTO.getCategoryName());
		WorkoutCategory savedWorkoutCategory = workoutCategoryRepository.save(workCategory);
		return savedWorkoutCategory.getCategoryId();
	}
	
	public boolean deleteCategory(Long categoryId) {
		Optional<WorkoutCategory> categoryObj = workoutCategoryRepository.findById(categoryId); 
		if(categoryObj != null && categoryObj.isPresent()) {
			workoutCategoryRepository.delete(categoryObj.get());
		}
		return true;
	}
}
