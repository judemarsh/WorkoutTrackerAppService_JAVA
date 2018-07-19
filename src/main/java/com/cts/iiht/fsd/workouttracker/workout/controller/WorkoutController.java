package com.cts.iiht.fsd.workouttracker.workout.controller;

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

import com.cts.iiht.fsd.workouttracker.workout.service.WorkoutService;
import com.cts.iiht.fsd.workouttracker.workout.to.WorkoutTO;

@RestController
@RequestMapping("/workouttracker/workouts")
@CrossOrigin
public class WorkoutController {
	
	@Autowired
	private WorkoutService workoutService;
	
		
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WorkoutTO> getWorkoutList(){
		return workoutService.getWorkoutList();
	}
	
	@GetMapping(value = "/{workoutId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public WorkoutTO getWorkoutById(@PathVariable("workoutId") Long workoutId) {
		return workoutService.getWorkoutById(workoutId);
	}
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long saveWorkout(@RequestBody WorkoutTO workoutTO) {
		return workoutService.saveWorkout(workoutTO);
	}
	
	@PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long updateWorkout(@RequestBody WorkoutTO workoutTO) {
		return workoutService.saveWorkout(workoutTO);
	}
	
	@DeleteMapping(value = "/{workoutId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteWorkout(@PathVariable("workoutId") Long workoutId) {
		return workoutService.deleteWorkout(workoutId);
	}
}
