package com.cts.iiht.fsd.workouttracker.workout;

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
import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutCollection;
import com.cts.iiht.fsd.workouttracker.workout.repository.WorkoutCollectionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest(classes = WorkoutTrackerApplication.class)
@Transactional
public class WorkoutRepositoryTest {
	
	@Autowired
	private transient WorkoutCollectionRepository workoutCollectionRepository;
	
	@Autowired
	private transient CategoryRepository workoutCategoryRepository;
	
	public void setRepo(final WorkoutCollectionRepository repository) {
		this.workoutCollectionRepository = repository;
	}
	
	public void setRepo(final CategoryRepository repository) {
		this.workoutCategoryRepository = repository;
	}
	
	@Test
	public void testGetWorkoutList() throws Exception{
		WorkoutCategory category = new WorkoutCategory();
		category.setCategoryId(null);
		category.setCategoryName("Category 1");
		WorkoutCategory savedEntity = workoutCategoryRepository.save(category);
		WorkoutCollection workout = new WorkoutCollection();
		workout.setWorkoutId(null);
		workout.setWorkoutTitle("Workout 1");
		workout.setWorkoutNote("Workout Note 1");
		workout.setCategory(savedEntity);
		workout.setCaloriesBurnPerMin(10F);
		
		final List<WorkoutCollection> workoutCategoryList = workoutCollectionRepository.findAll();
		assertNotNull(workoutCategoryList);
	}
	
	@Test
	public void testGetWorkoutById() throws Exception {
		WorkoutCategory category = new WorkoutCategory();
		category.setCategoryId(null);
		category.setCategoryName("Category 1");
		WorkoutCategory savedCategoryEntity = workoutCategoryRepository.save(category);
		WorkoutCollection workout = new WorkoutCollection();
		workout.setWorkoutId(null);
		workout.setWorkoutTitle("Workout 1");
		workout.setWorkoutNote("Workout Note 1");
		workout.setCategory(savedCategoryEntity);
		workout.setCaloriesBurnPerMin(10F);
		WorkoutCollection savedWorkoutEntity = workoutCollectionRepository.save(workout);
		final Optional<WorkoutCollection> workoutCategory = workoutCollectionRepository.findById(savedWorkoutEntity.getWorkoutId());
		assertNotNull(workoutCategory);
		assertEquals(workoutCategory.get().getWorkoutTitle(), "Workout 1");
	}
	
	@Test
	public void testSaveWorkout() throws Exception {
		WorkoutCategory category = new WorkoutCategory();
		category.setCategoryId(null);
		category.setCategoryName("Category 1");
		WorkoutCategory savedCategoryEntity = workoutCategoryRepository.save(category);
		WorkoutCollection workout = new WorkoutCollection();
		workout.setWorkoutId(null);
		workout.setWorkoutTitle("Workout 1");
		workout.setWorkoutNote("Workout Note 1");
		workout.setCategory(savedCategoryEntity);
		workout.setCaloriesBurnPerMin(10F);
		WorkoutCollection savedWorkoutEntity = workoutCollectionRepository.save(workout);
		assertNotNull(savedWorkoutEntity);
		assertNotNull(savedWorkoutEntity.getWorkoutId());
	}
	
	
	@Test
	public void testUpdateWorkout() throws Exception {
		WorkoutCategory category = new WorkoutCategory();
		category.setCategoryId(null);
		category.setCategoryName("Category 1");
		WorkoutCategory savedCategoryEntity = workoutCategoryRepository.save(category);
		WorkoutCollection workout = new WorkoutCollection();
		workout.setWorkoutId(null);
		workout.setWorkoutTitle("Workout 1");
		workout.setWorkoutNote("Workout Note 1");
		workout.setCategory(savedCategoryEntity);
		workout.setCaloriesBurnPerMin(10F);
		WorkoutCollection savedWorkoutEntity = workoutCollectionRepository.save(workout);
		WorkoutCollection updateWorkout = new WorkoutCollection();
		updateWorkout.setWorkoutId(savedWorkoutEntity.getWorkoutId());
		updateWorkout.setWorkoutTitle("New Workout Title");
		updateWorkout.setWorkoutNote("Workout Note 1");
		updateWorkout.setCategory(savedCategoryEntity);
		updateWorkout.setCaloriesBurnPerMin(10F);
		WorkoutCollection updatedEntityObj = workoutCollectionRepository.save(updateWorkout);
		assertNotNull(updatedEntityObj);
		assertNotNull(updatedEntityObj.getWorkoutId());
		assertEquals(updatedEntityObj.getWorkoutTitle(), "New Workout Title");
	}
	
	@Test
	public void testDeleteWorkout() throws Exception{
		WorkoutCategory category = new WorkoutCategory();
		category.setCategoryId(null);
		category.setCategoryName("Category 1");
		WorkoutCategory savedCategoryEntity = workoutCategoryRepository.save(category);
		WorkoutCollection workout = new WorkoutCollection();
		workout.setWorkoutId(null);
		workout.setWorkoutTitle("Workout 1");
		workout.setWorkoutNote("Workout Note 1");
		workout.setCategory(savedCategoryEntity);
		workout.setCaloriesBurnPerMin(10F);
		WorkoutCollection savedWorkoutEntity = workoutCollectionRepository.save(workout);
		workoutCollectionRepository.delete(savedWorkoutEntity);
		final Optional<WorkoutCollection> workoutObj = workoutCollectionRepository.findById(savedWorkoutEntity.getWorkoutId());
		assertEquals(workoutObj.isPresent(), false);
				
	}
}
