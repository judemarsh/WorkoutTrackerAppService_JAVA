package com.cts.iiht.fsd.workouttracker.framework.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="workout_active")
public class WorkoutActive implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="workout_active_id", nullable=false)
	private Long workoutActiveId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="workout_id")
	private WorkoutCollection workout;
	
	@Column(name="start_time")
	private Time startTime;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_time")
	private Time endTime;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="comment",length=64)
	private String comment;
	
	@Column(name="status")
	private boolean status;
	
	public WorkoutActive() {
		
	}
	
	public WorkoutActive(Long workoutActiveId) {
		this.workoutActiveId = workoutActiveId;
	}

	public Long getWorkoutActiveId() {
		return workoutActiveId;
	}

	public void setWorkoutActiveId(Long workoutActiveId) {
		this.workoutActiveId = workoutActiveId;
	}

	public WorkoutCollection getWorkout() {
		return workout;
	}

	public void setWorkout(WorkoutCollection workout) {
		this.workout = workout;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
