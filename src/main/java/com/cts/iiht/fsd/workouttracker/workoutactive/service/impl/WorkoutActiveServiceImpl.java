package com.cts.iiht.fsd.workouttracker.workoutactive.service.impl;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutActive;
import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutCollection;
import com.cts.iiht.fsd.workouttracker.workoutactive.repository.WorkoutActiveRepository;
import com.cts.iiht.fsd.workouttracker.workoutactive.service.WorkoutActiveService;
import com.cts.iiht.fsd.workouttracker.workoutactive.to.WorkoutActiveTO;

@Service
@SuppressWarnings("finally")
public class WorkoutActiveServiceImpl implements WorkoutActiveService{
	
	@Autowired
	private WorkoutActiveRepository workoutActiveRepository;
	
	public WorkoutActiveTO getWorkoutActive(Long workoutId) {
		WorkoutActiveTO workoutActiveTO = new WorkoutActiveTO();
		WorkoutActive workoutActive = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<WorkoutActive> workoutActiveList = workoutActiveRepository.findByWorkoutId(workoutId);
		if(workoutActiveList != null && !workoutActiveList.isEmpty()) {
			workoutActive = workoutActiveList.get(0);
			workoutActiveTO.setWorkoutActiveId(workoutActive.getWorkoutActiveId());
			workoutActiveTO.setWorkoutNote(workoutActive.getWorkout().getWorkoutNote());
			workoutActiveTO.setWorkoutId(workoutActive.getWorkout().getWorkoutId());
			workoutActiveTO.setWorkoutTitle(workoutActive.getWorkout().getWorkoutTitle());
			if(workoutActive.getStartDate() != null) {
				workoutActiveTO.setStartDate(dateFormat.format(workoutActive.getStartDate()));	
			} else {
				workoutActiveTO.setStartDate(dateFormat.format(new Date()));
			}
			if(workoutActive.getStartTime() != null) {
				//workoutActiveTO.setStartTime(timeFormat.format(workoutActive.getStartTime()));	
				workoutActiveTO.setStartTime(workoutActive.getStartTime().getTime());
			} else {
				//workoutActiveTO.setStartTime(timeFormat.format(new Time(new Date().getTime())));
				workoutActiveTO.setStartTime(new Time(new Date().getTime()).getTime());
			}
		}
		return workoutActiveTO;
	}
	
	public Long startWorkout(WorkoutActiveTO workoutActiveTO) {
		WorkoutActive workoutActive = new WorkoutActive();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		WorkoutActive savedWorkoutActive = null;
		try {
			workoutActive.setStartDate(dateFormat.parse(workoutActiveTO.getStartDate()));
			workoutActive.setStartTime(new Time(workoutActiveTO.getStartTime()));
			workoutActive.setWorkout(new WorkoutCollection(workoutActiveTO.getWorkoutId()));
			workoutActive.setStatus(true);
			savedWorkoutActive = workoutActiveRepository.save(workoutActive);
		} catch(Exception e){
			
		} finally {
			if(savedWorkoutActive != null) {
				return savedWorkoutActive.getWorkoutActiveId();
			} else {
				return null;
			}
		}
	}
	
	public Long EndWorkout(WorkoutActiveTO workoutActiveTO) {
		WorkoutActive workoutActive = null;
		Optional<WorkoutActive> workActiveObj = workoutActiveRepository.findById(workoutActiveTO.getWorkoutActiveId());
		if(workActiveObj != null && workActiveObj.isPresent()) {
			workoutActive = workActiveObj.get();
		}
		WorkoutActive savedWorkoutActive = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			workoutActive.setComment(workoutActiveTO.getComments());
			workoutActive.setEndDate(dateFormat.parse(workoutActiveTO.getEndDate()));
			workoutActive.setEndTime(new Time(workoutActiveTO.getEndTime()));
			workoutActive.setStatus(false);
			savedWorkoutActive = workoutActiveRepository.save(workoutActive);
		} catch(Exception e) {
			
		} finally {
			if(savedWorkoutActive != null) {
				return savedWorkoutActive.getWorkoutActiveId();
			} else {
				return null;
			}
		}
	}
}
