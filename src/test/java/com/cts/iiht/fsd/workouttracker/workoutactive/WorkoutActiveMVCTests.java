package com.cts.iiht.fsd.workouttracker.workoutactive;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.cts.iiht.fsd.workouttracker.workoutactive.controller.WorkoutActiveController;
import com.cts.iiht.fsd.workouttracker.workoutactive.service.WorkoutActiveService;
import com.cts.iiht.fsd.workouttracker.workoutactive.to.WorkoutActiveTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WorkoutTrackerApplication.class)
@AutoConfigureMockMvc
public class WorkoutActiveMVCTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private WorkoutActiveTO workoutActiveTO;
	
	@Mock
	private WorkoutActiveService workoutActiveService;
	
	@InjectMocks
	private WorkoutActiveController workoutActiveController = new WorkoutActiveController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(workoutActiveController).build();
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(workoutActiveTO);
		assertNotNull("Workout service injection failed.", workoutActiveService);
	}
	
	private static String asJsonStringConvert(final Object obj){
		try{
			return new ObjectMapper().writeValueAsString(obj);
		}catch(Exception ex){
			throw new  RuntimeException(ex);
		}
	}
	
	@Test
	public void testGetWorkoutActive() throws Exception {
		WorkoutActiveTO workoutActiveTO = new WorkoutActiveTO();
		workoutActiveTO.setWorkoutActiveId(1L);
		workoutActiveTO.setWorkoutId(10L);
		workoutActiveTO.setWorkoutTitle("Workout 10");
		workoutActiveTO.setWorkoutNote("Workout Note 10");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		workoutActiveTO.setStartDate(dateFormat.format(new Date()));
		workoutActiveTO.setStartTime(new Time(new Date().getTime()).getTime());
		workoutActiveTO.setStatus(true);
		
		when(workoutActiveService.getWorkoutActive(10L)).thenReturn(workoutActiveTO);
		
		mockMvc.perform(get("/workouttracker/workoutactivities/10"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.workoutTitle", is("Workout 10")));
		verify(workoutActiveService, times(1)).getWorkoutActive(10L);
	}
	
	@Test
	public void testStartWorkout() throws Exception {
		WorkoutActiveTO workoutActiveTO = new WorkoutActiveTO();
		workoutActiveTO.setWorkoutActiveId(null);
		workoutActiveTO.setWorkoutId(10L);
		workoutActiveTO.setStartDate("2018-10-05");
		workoutActiveTO.setStartTime(1000L);
		
		when(workoutActiveService.startWorkout(workoutActiveTO)).thenReturn(20L);
		
		mockMvc.perform(post("/workouttracker/workoutactivities/").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonStringConvert(workoutActiveTO)))
					.andExpect(status().isOk());
	}
	
	@Test
	public void testEndWorkout() throws Exception {
		WorkoutActiveTO workoutActiveTO = new WorkoutActiveTO();
		workoutActiveTO.setWorkoutActiveId(null);
		workoutActiveTO.setComments("Ended the activity");
		workoutActiveTO.setWorkoutId(10L);
		workoutActiveTO.setStartDate("2018-10-05");
		workoutActiveTO.setStartTime(500000L);
		
		when(workoutActiveService.getWorkoutActive(10L)).thenReturn(workoutActiveTO);
		when(workoutActiveService.startWorkout(workoutActiveTO)).thenReturn(20L);
		
		mockMvc.perform(put("/workouttracker/workoutactivities/").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonStringConvert(workoutActiveTO)))
					.andExpect(status().isOk());
	}

}
