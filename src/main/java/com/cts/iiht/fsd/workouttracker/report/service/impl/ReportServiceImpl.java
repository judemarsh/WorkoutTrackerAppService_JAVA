package com.cts.iiht.fsd.workouttracker.report.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.iiht.fsd.workouttracker.report.service.ReportService;
import com.cts.iiht.fsd.workouttracker.report.to.ChartTO;
import com.cts.iiht.fsd.workouttracker.report.to.ReportTO;
import com.cts.iiht.fsd.workouttracker.workoutactive.repository.WorkoutActiveRepository;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private WorkoutActiveRepository workoutActiveRepository;
	
	public ReportTO getWorkoutReport() {
		String[] dayNames = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		String[] monthNames = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		String[] weekNames = {"Week 1","Week 2","Week 3","Week 4","Week 5"};
		ReportTO reportTO = new ReportTO();
		Object[] workoutTimeOfDayObj = workoutActiveRepository.getTotalWorkoutTimeOfDay();
		if(workoutTimeOfDayObj != null) {
			reportTO.setWorkoutTimeOfDay(workoutTimeOfDayObj[0].toString());
		}
		Object[] workoutTimeOfWeekObj = workoutActiveRepository.getTotalWorkoutTimeOfWeek();
		if(workoutTimeOfWeekObj != null) {
			reportTO.setWorkoutTimeOfWeek(workoutTimeOfWeekObj[0].toString());
		}
		Object[] workoutTimeOfMonthObj = workoutActiveRepository.getTotalWorkoutTimeOfMonth();
		if(workoutTimeOfMonthObj != null) {
			reportTO.setWorkoutTimeOfMonth(workoutTimeOfMonthObj[0].toString());
		}
		List<Object[]> caloriesBurntByDaysObj = workoutActiveRepository.getCaloriesBurntByDaysInWeek();
		if(caloriesBurntByDaysObj != null && !caloriesBurntByDaysObj.isEmpty()) {
			Double totalCaloriesInWeek = new Double(0);
			List<ChartTO> caloriesBurntByDaysChartData = new ArrayList<ChartTO>();
			for(int i=0;i<dayNames.length;i++) {
				final String dayName = dayNames[i];
				Double chartValue = new Double(0);
				Optional<Object[]> caloriesBurntOnDay = caloriesBurntByDaysObj.stream().filter(data -> data[0].toString().equals(dayName)).findFirst();
				ChartTO chartData = new ChartTO();
				chartData.setLabel(dayNames[i]);
				if(caloriesBurntOnDay != null && caloriesBurntOnDay.isPresent()) {
					chartValue = (Double) caloriesBurntOnDay.get()[1];
					totalCaloriesInWeek +=  chartValue;
				}
				chartData.setValue(chartValue.toString());
				caloriesBurntByDaysChartData.add(chartData);
			}
			reportTO.setCaloriesWeekChart(caloriesBurntByDaysChartData);
			reportTO.setTotalCaloriesInWeek(totalCaloriesInWeek.toString());
		}
		List<Object[]> caloriesBurntByWeeksObj = workoutActiveRepository.getCaloriesBurntByWeeksInMonth();
		if(caloriesBurntByWeeksObj != null && !caloriesBurntByWeeksObj.isEmpty()) {
			Double totalCaloriesInMonth = new Double(0);
			List<ChartTO> caloriesBurntByWeeksChartData = new ArrayList<ChartTO>();
			for(int i=0;i<weekNames.length;i++) {
				final String weekName = weekNames[i];
				Double chartValue = new Double(0);
				Optional<Object[]> caloriesBurntOnWeek = caloriesBurntByWeeksObj.stream().filter(data -> "Week ".concat(data[0].toString()).equals(weekName)).findFirst();
				ChartTO chartData = new ChartTO();
				chartData.setLabel(weekNames[i]);
				if(caloriesBurntOnWeek != null && caloriesBurntOnWeek.isPresent()) {
					chartValue = (Double) caloriesBurntOnWeek.get()[1];
					totalCaloriesInMonth +=  chartValue;
				}
				chartData.setValue(chartValue.toString());
				caloriesBurntByWeeksChartData.add(chartData);
			}
			reportTO.setCaloriesMonthChart(caloriesBurntByWeeksChartData);
			reportTO.setTotalCaloriesInMonth(totalCaloriesInMonth.toString());
		}
		List<Object[]> caloriesBurntByMonthObj = workoutActiveRepository.getCaloriesBurntByMonthsInYear();	
		if(caloriesBurntByMonthObj != null && !caloriesBurntByMonthObj.isEmpty()) {
			Double totalCaloriesInYear = new Double(0);
			List<ChartTO> caloriesBurntByMonthsChartData = new ArrayList<ChartTO>();
			for(int i=0;i<monthNames.length;i++) {
				final String monthName = monthNames[i];
				Double chartValue = new Double(0);
				Optional<Object[]> caloriesBurntOnMonth = caloriesBurntByMonthObj.stream().filter(data -> data[0].toString().equals(monthName)).findFirst();
				ChartTO chartData = new ChartTO();
				chartData.setLabel(monthNames[i]);
				if(caloriesBurntOnMonth != null && caloriesBurntOnMonth.isPresent()) {
					chartValue = (Double) caloriesBurntOnMonth.get()[1];
					totalCaloriesInYear +=  chartValue;
				}
				chartData.setValue(chartValue.toString());
				caloriesBurntByMonthsChartData.add(chartData);
			}
			reportTO.setCaloriesYearChart(caloriesBurntByMonthsChartData);
			reportTO.setTotalCaloriesInYear(totalCaloriesInYear.toString());
		}
		return reportTO;
	}
}
