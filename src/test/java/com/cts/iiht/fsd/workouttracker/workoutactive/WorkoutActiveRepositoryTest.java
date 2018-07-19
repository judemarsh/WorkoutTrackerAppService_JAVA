package com.cts.iiht.fsd.workouttracker.workoutactive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutActive;
import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutCategory;
import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutCollection;
import com.cts.iiht.fsd.workouttracker.workout.repository.WorkoutCollectionRepository;
import com.cts.iiht.fsd.workouttracker.workoutactive.repository.WorkoutActiveRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest(classes = WorkoutTrackerApplication.class)
@Transactional
public class WorkoutActiveRepositoryTest {
	
	@Autowired
	private transient WorkoutActiveRepository workoutActiveRepository;
	
	@Autowired
	private transient WorkoutCollectionRepository workoutCollectionRepository;
	
	@Autowired
	private transient CategoryRepository workoutCategoryRepository;
	
	public void setRepo(final CategoryRepository repository) {
		this.workoutCategoryRepository = repository;
	}
	
	public void setRepo(final WorkoutActiveRepository repository) {
		this.workoutActiveRepository = repository;
	}
	
	public void setRepo(final WorkoutCollectionRepository repository) {
		this.workoutCollectionRepository = repository;
	}
	
	@Test
	public void testGetWorkoutActive() throws Exception {
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
		WorkoutActive workoutActive = new WorkoutActive();
		workoutActive.setWorkoutActiveId(1L);
		workoutActive.setWorkout(savedWorkoutEntity);
		workoutActive.setStartDate(new Date());
		workoutActive.setStartTime(new Time(100L));
		workoutActive.setStatus(true);
		workoutActiveRepository.save(workoutActive);
		final List<WorkoutActive> workoutActiveList = workoutActiveRepository.findByWorkoutId(savedWorkoutEntity.getWorkoutId());
		assertNotNull(workoutActiveList);
		assertEquals(workoutActiveList.get(0).getWorkout().getWorkoutTitle(), "Workout 1");
		
	}
	
	@Test
	public void testStartWorkout() throws Exception {
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
		WorkoutActive workoutActive = new WorkoutActive();
		workoutActive.setWorkoutActiveId(1L);
		workoutActive.setWorkout(savedWorkoutEntity);
		workoutActive.setStartDate(new Date());
		workoutActive.setStartTime(new Time(100L));
		workoutActive.setStatus(true);
		WorkoutActive savedWorkoutActiveEntity = workoutActiveRepository.save(workoutActive);
		assertNotNull(savedWorkoutActiveEntity);
		assertNotNull(savedWorkoutActiveEntity.getWorkoutActiveId());
	}
	
	@Test
	public void testEndWorkout() throws Exception {
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
		WorkoutActive workoutActive = new WorkoutActive();
		workoutActive.setWorkoutActiveId(1L);
		workoutActive.setWorkout(savedWorkoutEntity);
		workoutActive.setStartDate(new Date());
		workoutActive.setStartTime(new Time(100L));
		workoutActive.setStatus(true);
		WorkoutActive startedWorkoutActiveEntity = workoutActiveRepository.save(workoutActive);
		assertNotNull(startedWorkoutActiveEntity);
		assertNotNull(startedWorkoutActiveEntity.getWorkoutActiveId());
		
		final List<WorkoutActive> workoutActiveList = workoutActiveRepository.findByWorkoutId(savedWorkoutEntity.getWorkoutId());
		WorkoutActive startedWorkoutActive = workoutActiveList.get(0);
		startedWorkoutActive.setComment("Workout is ended");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		startedWorkoutActive.setEndDate(dateFormat.parse("2018-10-10"));
		startedWorkoutActive.setEndTime(new Time(1500L));
		startedWorkoutActive.setStatus(false);
		WorkoutActive endedWorkoutActiveEntity = workoutActiveRepository.save(startedWorkoutActive);
		assertNotNull(endedWorkoutActiveEntity);
		assertEquals(endedWorkoutActiveEntity.getComment(), "Workout is ended");		
	}
	
	@Test
	public void testHasActiveWorkout() {
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
		WorkoutActive workoutActive = new WorkoutActive();
		workoutActive.setWorkoutActiveId(1L);
		workoutActive.setWorkout(savedWorkoutEntity);
		workoutActive.setStartDate(new Date());
		workoutActive.setStartTime(new Time(100L));
		workoutActive.setStatus(true);
		WorkoutActive startedWorkoutActiveEntity = workoutActiveRepository.save(workoutActive);
		assertNotNull(startedWorkoutActiveEntity);
		assertNotNull(startedWorkoutActiveEntity.getWorkoutActiveId());
		
		Object[] resultSet = workoutActiveRepository.hasActiveWorkout(savedWorkoutEntity.getWorkoutId());
		assertNotNull(resultSet);
	}
	
	@Test
	public void testGetTotalWorkoutTimeOfDay() {
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
		WorkoutActive workoutActive = new WorkoutActive();
		workoutActive.setWorkoutActiveId(1L);
		workoutActive.setWorkout(savedWorkoutEntity);
		workoutActive.setStartDate(new Date());
		workoutActive.setStartTime(new Time(100L));
		workoutActive.setStatus(true);
		WorkoutActive startedWorkoutActiveEntity = workoutActiveRepository.save(workoutActive);
		assertNotNull(startedWorkoutActiveEntity);
		assertNotNull(startedWorkoutActiveEntity.getWorkoutActiveId());
		
		final List<WorkoutActive> workoutActiveList = workoutActiveRepository.findByWorkoutId(savedWorkoutEntity.getWorkoutId());
		WorkoutActive startedWorkoutActive = workoutActiveList.get(0);
		startedWorkoutActive.setComment("Workout is ended");
		startedWorkoutActive.setEndDate(new Date());
		startedWorkoutActive.setEndTime(new Time(1500L));
		startedWorkoutActive.setStatus(false);
		workoutActiveRepository.save(startedWorkoutActive);
		
		Object[] resultSet = workoutActiveRepository.getTotalWorkoutTimeOfDay();
		assertNotNull(resultSet);
	}
	
	@Test
	public void testGetTotalWorkoutTimeOfWeek() {
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
		WorkoutActive workoutActive = new WorkoutActive();
		workoutActive.setWorkoutActiveId(1L);
		workoutActive.setWorkout(savedWorkoutEntity);
		workoutActive.setStartDate(new Date());
		workoutActive.setStartTime(new Time(100L));
		workoutActive.setStatus(true);
		WorkoutActive startedWorkoutActiveEntity = workoutActiveRepository.save(workoutActive);
		assertNotNull(startedWorkoutActiveEntity);
		assertNotNull(startedWorkoutActiveEntity.getWorkoutActiveId());
		
		final List<WorkoutActive> workoutActiveList = workoutActiveRepository.findByWorkoutId(savedWorkoutEntity.getWorkoutId());
		WorkoutActive startedWorkoutActive = workoutActiveList.get(0);
		startedWorkoutActive.setComment("Workout is ended");
		startedWorkoutActive.setEndDate(new Date());
		startedWorkoutActive.setEndTime(new Time(1500L));
		startedWorkoutActive.setStatus(false);
		workoutActiveRepository.save(startedWorkoutActive);
		
		Object[] resultSet = workoutActiveRepository.getTotalWorkoutTimeOfWeek();
		assertNotNull(resultSet);
	}
	
	@Test
	public void testGetTotalWorkoutTimeOfMonth() {
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
		WorkoutActive workoutActive = new WorkoutActive();
		workoutActive.setWorkoutActiveId(1L);
		workoutActive.setWorkout(savedWorkoutEntity);
		workoutActive.setStartDate(new Date());
		workoutActive.setStartTime(new Time(100L));
		workoutActive.setStatus(true);
		WorkoutActive startedWorkoutActiveEntity = workoutActiveRepository.save(workoutActive);
		assertNotNull(startedWorkoutActiveEntity);
		assertNotNull(startedWorkoutActiveEntity.getWorkoutActiveId());
		
		final List<WorkoutActive> workoutActiveList = workoutActiveRepository.findByWorkoutId(savedWorkoutEntity.getWorkoutId());
		WorkoutActive startedWorkoutActive = workoutActiveList.get(0);
		startedWorkoutActive.setComment("Workout is ended");
		startedWorkoutActive.setEndDate(new Date());
		startedWorkoutActive.setEndTime(new Time(1500L));
		startedWorkoutActive.setStatus(false);
		workoutActiveRepository.save(startedWorkoutActive);
		
		Object[] resultSet = workoutActiveRepository.getTotalWorkoutTimeOfMonth();
		assertNotNull(resultSet);
	}
	
	@Test
	public void testGetCaloriesBurntByDaysInWeek() {
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
		WorkoutActive workoutActive = new WorkoutActive();
		workoutActive.setWorkoutActiveId(1L);
		workoutActive.setWorkout(savedWorkoutEntity);
		workoutActive.setStartDate(new Date());
		workoutActive.setStartTime(new Time(100L));
		workoutActive.setStatus(true);
		WorkoutActive startedWorkoutActiveEntity = workoutActiveRepository.save(workoutActive);
		assertNotNull(startedWorkoutActiveEntity);
		assertNotNull(startedWorkoutActiveEntity.getWorkoutActiveId());
		
		final List<WorkoutActive> workoutActiveList = workoutActiveRepository.findByWorkoutId(savedWorkoutEntity.getWorkoutId());
		WorkoutActive startedWorkoutActive = workoutActiveList.get(0);
		startedWorkoutActive.setComment("Workout is ended");
		startedWorkoutActive.setEndDate(new Date());
		startedWorkoutActive.setEndTime(new Time(1500L));
		startedWorkoutActive.setStatus(false);
		workoutActiveRepository.save(startedWorkoutActive);
		
		List<Object[]> resultSet = workoutActiveRepository.getCaloriesBurntByDaysInWeek();
		assertNotNull(resultSet);
	}

	@Test
	public void testGetCaloriesBurntByWeeksInMonth() {
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
		WorkoutActive workoutActive = new WorkoutActive();
		workoutActive.setWorkoutActiveId(1L);
		workoutActive.setWorkout(savedWorkoutEntity);
		workoutActive.setStartDate(new Date());
		workoutActive.setStartTime(new Time(100L));
		workoutActive.setStatus(true);
		WorkoutActive startedWorkoutActiveEntity = workoutActiveRepository.save(workoutActive);
		assertNotNull(startedWorkoutActiveEntity);
		assertNotNull(startedWorkoutActiveEntity.getWorkoutActiveId());
		
		final List<WorkoutActive> workoutActiveList = workoutActiveRepository.findByWorkoutId(savedWorkoutEntity.getWorkoutId());
		WorkoutActive startedWorkoutActive = workoutActiveList.get(0);
		startedWorkoutActive.setComment("Workout is ended");
		startedWorkoutActive.setEndDate(new Date());
		startedWorkoutActive.setEndTime(new Time(1500L));
		startedWorkoutActive.setStatus(false);
		workoutActiveRepository.save(startedWorkoutActive);
		
		List<Object[]> resultSet = workoutActiveRepository.getCaloriesBurntByWeeksInMonth();
		assertNotNull(resultSet);
	}
	
	@Test
	public void testGetCaloriesBurntByMonthsInYear() {
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
		WorkoutActive workoutActive = new WorkoutActive();
		workoutActive.setWorkoutActiveId(1L);
		workoutActive.setWorkout(savedWorkoutEntity);
		workoutActive.setStartDate(new Date());
		workoutActive.setStartTime(new Time(100L));
		workoutActive.setStatus(true);
		WorkoutActive startedWorkoutActiveEntity = workoutActiveRepository.save(workoutActive);
		assertNotNull(startedWorkoutActiveEntity);
		assertNotNull(startedWorkoutActiveEntity.getWorkoutActiveId());
		
		final List<WorkoutActive> workoutActiveList = workoutActiveRepository.findByWorkoutId(savedWorkoutEntity.getWorkoutId());
		WorkoutActive startedWorkoutActive = workoutActiveList.get(0);
		startedWorkoutActive.setComment("Workout is ended");
		startedWorkoutActive.setEndDate(new Date());
		startedWorkoutActive.setEndTime(new Time(1500L));
		startedWorkoutActive.setStatus(false);
		workoutActiveRepository.save(startedWorkoutActive);
		
		List<Object[]> resultSet = workoutActiveRepository.getCaloriesBurntByMonthsInYear();
		assertNotNull(resultSet);
	}
}
