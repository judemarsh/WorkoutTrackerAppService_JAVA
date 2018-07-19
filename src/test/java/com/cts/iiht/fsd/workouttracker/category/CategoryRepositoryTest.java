package com.cts.iiht.fsd.workouttracker.category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.iiht.fsd.workouttracker.WorkoutTrackerApplication;
import com.cts.iiht.fsd.workouttracker.category.repository.CategoryRepository;
import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutCategory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest(classes = WorkoutTrackerApplication.class)
@Transactional
public class CategoryRepositoryTest {
	
	@Autowired
	private transient CategoryRepository categoryRepository;
	
	public void setRepo(final CategoryRepository repository) {
		this.categoryRepository = repository;
	}
	
	@Test
	public void testGetAllCategories() throws Exception{
		WorkoutCategory category = new WorkoutCategory();
		category.setCategoryId(null);
		category.setCategoryName("Category 1");
		categoryRepository.save(category);
		final List<WorkoutCategory> workoutCategoryList = categoryRepository.findAll();
		assertNotNull(workoutCategoryList);
	}
	
	@Test
	public void testGetCategoryById() throws Exception {
		WorkoutCategory category = new WorkoutCategory();
		category.setCategoryId(null);
		category.setCategoryName("Category 1");
		WorkoutCategory savedEntityObj = categoryRepository.save(category);
		final Optional<WorkoutCategory> workoutCategory = categoryRepository.findById(savedEntityObj.getCategoryId());
		assertNotNull(workoutCategory);
		assertEquals(workoutCategory.get().getCategoryName(), "Category 1");
	}
	
	@Test
	public void testSaveCategory() throws Exception {
		WorkoutCategory category = new WorkoutCategory();
		category.setCategoryId(null);
		category.setCategoryName("Category 1");
		WorkoutCategory savedEntityObj = categoryRepository.save(category);
		assertNotNull(savedEntityObj);
		assertNotNull(savedEntityObj.getCategoryId());
	}
	
	@Test
	public void testUpdateCategory() throws Exception {
		WorkoutCategory category = new WorkoutCategory();
		category.setCategoryId(null);
		category.setCategoryName("Category 1");
		WorkoutCategory savedEntityObj = categoryRepository.save(category);
		WorkoutCategory updateCategory = new WorkoutCategory();
		updateCategory.setCategoryId(savedEntityObj.getCategoryId());
		updateCategory.setCategoryName("Category 2");
		WorkoutCategory updatedEntityObj = categoryRepository.save(updateCategory);
		assertNotNull(updatedEntityObj);
		assertNotNull(updatedEntityObj.getCategoryId());
		assertEquals(updatedEntityObj.getCategoryName(), "Category 2");
	}
	
	@Test
	public void testDeleteCategory() throws Exception{
		WorkoutCategory category = new WorkoutCategory();
		category.setCategoryId(null);
		category.setCategoryName("Category 1");
		WorkoutCategory savedEntityObj = categoryRepository.save(category);
		WorkoutCategory deleteCategory = new WorkoutCategory();
		deleteCategory.setCategoryId(savedEntityObj.getCategoryId());
		deleteCategory.setCategoryName("Category 2");
		categoryRepository.delete(deleteCategory);
		final Optional<WorkoutCategory> workoutCategory = categoryRepository.findById(savedEntityObj.getCategoryId());
		assertEquals(workoutCategory.isPresent(), false);
				
	}
}
