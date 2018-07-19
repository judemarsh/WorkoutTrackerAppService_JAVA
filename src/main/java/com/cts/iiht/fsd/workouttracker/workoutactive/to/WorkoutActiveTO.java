package com.cts.iiht.fsd.workouttracker.workoutactive.to;

import java.io.Serializable;

public class WorkoutActiveTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long workoutActiveId;
	
	private String comments;
	
	private Long workoutId;
	
	private String workoutTitle;
	
	private String workoutNote;
	
	private String startDate;
	
	private Long startTime;
	
	private String endDate;
	
	private Long endTime;
	
	private boolean status;

	public Long getWorkoutActiveId() {
		return workoutActiveId;
	}

	public void setWorkoutActiveId(Long workoutActiveId) {
		this.workoutActiveId = workoutActiveId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(Long workoutId) {
		this.workoutId = workoutId;
	}

	public String getWorkoutTitle() {
		return workoutTitle;
	}

	public void setWorkoutTitle(String workoutTitle) {
		this.workoutTitle = workoutTitle;
	}

	public String getWorkoutNote() {
		return workoutNote;
	}

	public void setWorkoutNote(String workoutNote) {
		this.workoutNote = workoutNote;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
