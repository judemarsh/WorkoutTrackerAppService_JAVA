package com.cts.iiht.fsd.workouttracker.workoutactive.service;

import com.cts.iiht.fsd.workouttracker.workoutactive.to.WorkoutActiveTO;

public interface WorkoutActiveService {

	public Long startWorkout(WorkoutActiveTO workoutActive);
	
	public WorkoutActiveTO getWorkoutActive(Long workoutId);
	
	public Long EndWorkout(WorkoutActiveTO workoutActive);
	
}
