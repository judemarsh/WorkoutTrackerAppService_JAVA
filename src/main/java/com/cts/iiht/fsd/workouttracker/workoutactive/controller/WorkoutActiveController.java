package com.cts.iiht.fsd.workouttracker.workoutactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.iiht.fsd.workouttracker.workoutactive.service.WorkoutActiveService;
import com.cts.iiht.fsd.workouttracker.workoutactive.to.WorkoutActiveTO;

@RestController
@RequestMapping("/workouttracker/workoutactivities")
@CrossOrigin
public class WorkoutActiveController {
	
	@Autowired
	private WorkoutActiveService workoutActiveService;
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long startWorkout(@RequestBody WorkoutActiveTO workoutActiveTO) {
		return workoutActiveService.startWorkout(workoutActiveTO);
	}
	
	@GetMapping(value = "/{workoutId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public WorkoutActiveTO getWorkoutActive(@PathVariable Long workoutId) {
		return workoutActiveService.getWorkoutActive(workoutId);
	}
	
	@PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long endWorkout(@RequestBody WorkoutActiveTO workoutActiveTO) {
		return workoutActiveService.EndWorkout(workoutActiveTO);
	}
	
}
