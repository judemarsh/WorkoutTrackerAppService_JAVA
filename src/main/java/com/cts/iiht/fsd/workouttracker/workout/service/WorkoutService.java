package com.cts.iiht.fsd.workouttracker.workout.service;

import java.util.List;

import com.cts.iiht.fsd.workouttracker.workout.to.WorkoutTO;

public interface WorkoutService {

	public List<WorkoutTO> getWorkoutList();
	
	public WorkoutTO getWorkoutById(Long workoutId);
	
	public Long saveWorkout(WorkoutTO workout);
	
	public boolean deleteWorkout(Long workoutId);
}
