package com.cts.iiht.fsd.workouttracker.report.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String workoutTimeOfDay;
	
	private String workoutTimeOfWeek;
	
	private String workoutTimeOfMonth;
	
	private String totalCaloriesInWeek;
	
	private String totalCaloriesInMonth;
	
	private String totalCaloriesInYear;
	
	private List<ChartTO> caloriesWeekChart = new ArrayList<ChartTO>();
	
	private List<ChartTO> caloriesMonthChart = new ArrayList<ChartTO>();
	
	private List<ChartTO> caloriesYearChart = new ArrayList<ChartTO>();

	public String getWorkoutTimeOfDay() {
		return workoutTimeOfDay;
	}

	public void setWorkoutTimeOfDay(String workoutTimeOfDay) {
		this.workoutTimeOfDay = workoutTimeOfDay;
	}

	public String getWorkoutTimeOfWeek() {
		return workoutTimeOfWeek;
	}

	public void setWorkoutTimeOfWeek(String workoutTimeOfWeek) {
		this.workoutTimeOfWeek = workoutTimeOfWeek;
	}

	public String getWorkoutTimeOfMonth() {
		return workoutTimeOfMonth;
	}

	public void setWorkoutTimeOfMonth(String workoutTimeOfMonth) {
		this.workoutTimeOfMonth = workoutTimeOfMonth;
	}

	public String getTotalCaloriesInWeek() {
		return totalCaloriesInWeek;
	}

	public void setTotalCaloriesInWeek(String totalCaloriesInWeek) {
		this.totalCaloriesInWeek = totalCaloriesInWeek;
	}

	public String getTotalCaloriesInMonth() {
		return totalCaloriesInMonth;
	}

	public void setTotalCaloriesInMonth(String totalCaloriesInMonth) {
		this.totalCaloriesInMonth = totalCaloriesInMonth;
	}

	public String getTotalCaloriesInYear() {
		return totalCaloriesInYear;
	}

	public void setTotalCaloriesInYear(String totalCaloriesInYear) {
		this.totalCaloriesInYear = totalCaloriesInYear;
	}

	public List<ChartTO> getCaloriesWeekChart() {
		return caloriesWeekChart;
	}

	public void setCaloriesWeekChart(List<ChartTO> caloriesWeekChart) {
		this.caloriesWeekChart = caloriesWeekChart;
	}

	public List<ChartTO> getCaloriesMonthChart() {
		return caloriesMonthChart;
	}

	public void setCaloriesMonthChart(List<ChartTO> caloriesMonthChart) {
		this.caloriesMonthChart = caloriesMonthChart;
	}

	public List<ChartTO> getCaloriesYearChart() {
		return caloriesYearChart;
	}

	public void setCaloriesYearChart(List<ChartTO> caloriesYearChart) {
		this.caloriesYearChart = caloriesYearChart;
	}
}
