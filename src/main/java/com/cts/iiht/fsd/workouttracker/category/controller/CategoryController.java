package com.cts.iiht.fsd.workouttracker.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.iiht.fsd.workouttracker.category.service.CategoryService;
import com.cts.iiht.fsd.workouttracker.category.to.WorkoutCategoryTO;

@RestController
@RequestMapping("/workouttracker/categories")
@CrossOrigin
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WorkoutCategoryTO> getCategoryList(){
		return categoryService.getCategoryList();
	}
	
	@GetMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public WorkoutCategoryTO getCategoryById(@PathVariable("categoryId") Long categoryId) {
		return categoryService.getCategoryById(categoryId);
	}
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long saveCategory(@RequestBody WorkoutCategoryTO workCategoryTO) {
		return categoryService.saveCategory(workCategoryTO);
	}
	
	@PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long updateCategory(@RequestBody WorkoutCategoryTO workCategoryTO) {
		return categoryService.saveCategory(workCategoryTO);
	}
	
	@DeleteMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteCategory(@PathVariable("categoryId") Long categoryId) {
		return categoryService.deleteCategory(categoryId);
	}

}
