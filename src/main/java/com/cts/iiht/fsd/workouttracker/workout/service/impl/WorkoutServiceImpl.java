package com.cts.iiht.fsd.workouttracker.workout.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutCategory;
import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutCollection;
import com.cts.iiht.fsd.workouttracker.workout.repository.WorkoutCollectionRepository;
import com.cts.iiht.fsd.workouttracker.workout.service.WorkoutService;
import com.cts.iiht.fsd.workouttracker.workout.to.WorkoutTO;
import com.cts.iiht.fsd.workouttracker.workoutactive.repository.WorkoutActiveRepository;

@Service
public class WorkoutServiceImpl implements WorkoutService{
	
	@Autowired
	private WorkoutActiveRepository workoutActiveRepository;
	
	@Autowired
	private WorkoutCollectionRepository workoutCollectionRepository;
	
	public List<WorkoutTO> getWorkoutList(){
		List<WorkoutCollection> workoutList = workoutCollectionRepository.findAll();
		List<WorkoutTO> workoutTOList = new ArrayList<WorkoutTO>();
		if(workoutList != null && !workoutList.isEmpty()) {
			Iterator<WorkoutCollection> workoutItr = workoutList.iterator();
			while(workoutItr.hasNext()) {
				WorkoutCollection workout = workoutItr.next();
				WorkoutTO workoutTO = new WorkoutTO();
				workoutTO.setWorkoutId(workout.getWorkoutId());
				workoutTO.setWorkoutTitle(workout.getWorkoutTitle());
				workoutTO.setWorkoutNote(workout.getWorkoutNote());
				workoutTO.setCaloriesBurnPerMin(workout.getCaloriesBurnPerMin());
				workoutTO.setCategoryId(workout.getCategory().getCategoryId());
				workoutTO.setCategoryName(workout.getCategory().getCategoryName());
				Object[] resultObj = workoutActiveRepository.hasActiveWorkout(workout.getWorkoutId());
				if(resultObj != null && resultObj.length != 0) {
					if(((Long)resultObj[0]).intValue() != 0) {
						workoutTO.setHasStarted(true);
					} else {
						workoutTO.setHasStarted(false);
					}
				} else {
					workoutTO.setHasStarted(false);
				}
				workoutTOList.add(workoutTO);
			}
		}
		return workoutTOList;
	}
	
	public WorkoutTO getWorkoutById(Long workoutId) {
		WorkoutTO workoutTO = new WorkoutTO();
		Optional<WorkoutCollection> workout = workoutCollectionRepository.findById(workoutId);
		if(workout != null && workout.isPresent()) {
			WorkoutCollection workoutObj = workout.get();
			workoutTO.setWorkoutId(workoutObj.getWorkoutId());
			workoutTO.setWorkoutTitle(workoutObj.getWorkoutTitle());
			workoutTO.setWorkoutNote(workoutObj.getWorkoutNote());
			workoutTO.setCaloriesBurnPerMin(workoutObj.getCaloriesBurnPerMin());
			workoutTO.setCategoryId(workoutObj.getCategory().getCategoryId());
			workoutTO.setCategoryName(workoutObj.getCategory().getCategoryName());
		}
		return workoutTO;
	}
	
	public Long saveWorkout(WorkoutTO workoutTO) {
		WorkoutCollection workoutObj = null;
		if(workoutTO != null && !StringUtils.isEmpty(workoutTO.getWorkoutId())) {
			workoutObj = workoutCollectionRepository.findById(workoutTO.getWorkoutId()).get();
		} else {
			workoutObj = new WorkoutCollection();
			workoutObj.setCaloriesBurnPerMin(workoutTO.getCaloriesBurnPerMin());
		}
		workoutObj.setWorkoutTitle(workoutTO.getWorkoutTitle());
		workoutObj.setWorkoutNote(workoutTO.getWorkoutNote());
		workoutObj.setCategory(new WorkoutCategory(workoutTO.getCategoryId()));
		WorkoutCollection savedWorkoutCollection  = workoutCollectionRepository.save(workoutObj);
		return savedWorkoutCollection.getWorkoutId();
	}
	
	public boolean deleteWorkout(Long workoutId) {
		Optional<WorkoutCollection> workoutObj = workoutCollectionRepository.findById(workoutId);
		if(workoutObj != null && workoutObj.isPresent()) {
			workoutCollectionRepository.delete(workoutObj.get());
		}	
		return true;
	}
}
