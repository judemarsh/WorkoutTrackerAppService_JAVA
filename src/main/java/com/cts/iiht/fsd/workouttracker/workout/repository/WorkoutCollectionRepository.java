package com.cts.iiht.fsd.workouttracker.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutCollection;

@Repository
public interface WorkoutCollectionRepository extends JpaRepository<WorkoutCollection, Long>{
	
}
