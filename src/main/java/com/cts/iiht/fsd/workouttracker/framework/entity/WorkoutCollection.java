package com.cts.iiht.fsd.workouttracker.framework.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="workout_collection")
public class WorkoutCollection implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="workout_id")
	private Long workoutId;
	
	@Column(name="workout_title")
	private String workoutTitle;
	
	@Column(name="workout_note")
	private String workoutNote;
	
	@Column(name="calories_burn_per_min")
	private Float caloriesBurnPerMin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private WorkoutCategory category;
	
	@OneToMany(mappedBy="workout",fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Set<WorkoutActive> workoutActiveSet = new HashSet<WorkoutActive>();
	
	public WorkoutCollection() {
		
	}
	
	public WorkoutCollection(Long workoutId) {
		this.workoutId = workoutId;
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

	public Float getCaloriesBurnPerMin() {
		return caloriesBurnPerMin;
	}

	public void setCaloriesBurnPerMin(Float caloriesBurnPerMin) {
		this.caloriesBurnPerMin = caloriesBurnPerMin;
	}

	public WorkoutCategory getCategory() {
		return category;
	}

	public void setCategory(WorkoutCategory category) {
		this.category = category;
	}
}
