package com.cts.iiht.fsd.workouttracker.workout;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.iiht.fsd.workouttracker.WorkoutTrackerApplication;
import com.cts.iiht.fsd.workouttracker.workout.controller.WorkoutController;
import com.cts.iiht.fsd.workouttracker.workout.service.WorkoutService;
import com.cts.iiht.fsd.workouttracker.workout.to.WorkoutTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WorkoutTrackerApplication.class)
@AutoConfigureMockMvc
public class WorkoutMVCTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private WorkoutTO workoutTO;
	
	@Mock
	private WorkoutService workoutService;
	
	@InjectMocks
	private WorkoutController workoutController = new WorkoutController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(workoutController).build();
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(workoutTO);
		assertNotNull("Workout service injection failed.", workoutService);
	}
	
	private static String asJsonStringConvert(final Object obj){
		try{
			return new ObjectMapper().writeValueAsString(obj);
		}catch(Exception ex){
			throw new  RuntimeException(ex);
		}
	}
	
	@Test
	public void testGetWorkoutList() throws Exception{
		WorkoutTO workoutTO1 = new WorkoutTO();
		workoutTO1.setWorkoutId(1L);
		workoutTO1.setWorkoutTitle("Workout 1");
		workoutTO1.setWorkoutNote("Workout Note 1");
		workoutTO1.setCategoryId(1L);
		workoutTO1.setCategoryName("Category 1");
		workoutTO1.setCaloriesBurnPerMin(10F);
		workoutTO1.setHasStarted(false);
		
		WorkoutTO workoutTO2 = new WorkoutTO();
		workoutTO2.setWorkoutId(2L);
		workoutTO2.setWorkoutTitle("Workout 2");
		workoutTO2.setWorkoutNote("Workout Note 2");
		workoutTO2.setCategoryId(2L);
		workoutTO2.setCategoryName("Category 2");
		workoutTO2.setCaloriesBurnPerMin(4F);
		workoutTO2.setHasStarted(true);
		
		when(workoutService.getWorkoutList()).thenReturn(Arrays.asList(workoutTO1, workoutTO2));
		
		mockMvc.perform(get("/workouttracker/workouts/").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].workoutTitle", is("Workout 1")))
				.andExpect(jsonPath("$[1].workoutTitle", is("Workout 2")));
		verify(workoutService, times(1)).getWorkoutList();
	}
	

	@Test
	public void testGetWorkoutById() throws Exception {
		WorkoutTO workoutTO1 = new WorkoutTO();
		workoutTO1.setWorkoutId(11L);
		workoutTO1.setWorkoutTitle("Workout 1");
		workoutTO1.setWorkoutNote("Workout Note 1");
		workoutTO1.setCategoryId(1L);
		workoutTO1.setCategoryName("Category 1");
		workoutTO1.setCaloriesBurnPerMin(10F);
		workoutTO1.setHasStarted(false);
		
		when(workoutService.getWorkoutById(1L)).thenReturn(workoutTO1);
		
		mockMvc.perform(get("/workouttracker/workouts/11"))
				.andExpect(status().isOk());
		verify(workoutService, times(1)).getWorkoutById(11L);
	}
	
	@Test
	public void testSaveWorkout() throws Exception {
		WorkoutTO workoutTO1 = new WorkoutTO();
		workoutTO1.setWorkoutId(null);
		workoutTO1.setCaloriesBurnPerMin(5F);
		workoutTO1.setWorkoutTitle("New Workout");
		workoutTO1.setWorkoutNote("Workout Note 1");
		workoutTO1.setCategoryId(2L);
		
		when(workoutService.getWorkoutById(10L)).thenReturn(null);
		when(workoutService.saveWorkout(workoutTO1)).thenReturn(10L);
		
		mockMvc.perform(post("/workouttracker/workouts/").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonStringConvert(workoutTO1)))
					.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateWorkout() throws Exception {
		WorkoutTO workoutTO1 = new WorkoutTO();
		workoutTO1.setWorkoutId(10L);
		workoutTO1.setWorkoutTitle("New Workout");
		workoutTO1.setWorkoutNote("Workout Note 1");
		workoutTO1.setCategoryId(2L);
		
		when(workoutService.getWorkoutById(10L)).thenReturn(workoutTO1);
		when(workoutService.saveWorkout(workoutTO1)).thenReturn(10L);
		
		mockMvc.perform(put("/workouttracker/workouts/").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonStringConvert(workoutTO1)))
					.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteWorkout() throws Exception{
		WorkoutTO workoutTO1 = new WorkoutTO();
		workoutTO1.setWorkoutId(1L);
		workoutTO1.setWorkoutTitle("Workout 1");
		workoutTO1.setWorkoutNote("Workout Note 1");
		workoutTO1.setCategoryId(1L);
		workoutTO1.setCategoryName("Category 1");
		workoutTO1.setCaloriesBurnPerMin(10F);
		workoutTO1.setHasStarted(false);
		
		when(workoutService.getWorkoutById(1L)).thenReturn(workoutTO1);
		when(workoutService.deleteWorkout(1L)).thenReturn(true);
		when(workoutService.getWorkoutById(1L)).thenReturn(workoutTO1);
		
		mockMvc.perform(delete("/workouttracker/workouts/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
				
	}

}
