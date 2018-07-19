package com.cts.iiht.fsd.workouttracker.report;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.iiht.fsd.workouttracker.WorkoutTrackerApplication;
import com.cts.iiht.fsd.workouttracker.report.controller.ReportController;
import com.cts.iiht.fsd.workouttracker.report.service.ReportService;
import com.cts.iiht.fsd.workouttracker.report.to.ChartTO;
import com.cts.iiht.fsd.workouttracker.report.to.ReportTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WorkoutTrackerApplication.class)
@AutoConfigureMockMvc
public class ReportMVCTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private ReportTO reportTO;
	
	@Mock
	private ReportService reportService;
	
	@InjectMocks
	private ReportController reportController = new ReportController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(reportTO);
		assertNotNull("Workout service injection failed.", reportService);
	}
	
	@Test
	public void testGetWorkoutReport() throws Exception {
		ReportTO reportTO = new ReportTO();
		reportTO.setTotalCaloriesInMonth("10");
		reportTO.setTotalCaloriesInWeek("5");
		reportTO.setTotalCaloriesInYear("100");
		reportTO.setWorkoutTimeOfDay("10");
		reportTO.setWorkoutTimeOfMonth("100");
		reportTO.setWorkoutTimeOfWeek("1000");
		ChartTO yearlyChartVal1 = new  ChartTO("January","100");
		ChartTO yearlyChartVal2 = new  ChartTO("February","100");
		ChartTO yearlyChartVal3 = new  ChartTO("March","100");
		ChartTO yearlyChartVal4 = new  ChartTO("April","100");
		ChartTO yearlyChartVal5 = new  ChartTO("May","100");
		ChartTO yearlyChartVal6 = new  ChartTO("June","100");
		ChartTO yearlyChartVal7 = new  ChartTO("July","100");
		ChartTO yearlyChartVal8 = new  ChartTO("August","100");
		ChartTO yearlyChartVal9 = new  ChartTO("September","100");
		ChartTO yearlyChartVal10 = new  ChartTO("October","100");
		ChartTO yearlyChartVal11 = new  ChartTO("November","100");
		ChartTO yearlyChartVal12 = new  ChartTO("December","100");
		ChartTO monthlyChartVal1 = new  ChartTO("Week 1","100");
		ChartTO monthlyChartVal2 = new  ChartTO("Week 2","100");
		ChartTO monthlyChartVal3 = new  ChartTO("Week 3","100");
		ChartTO monthlyChartVal4 = new  ChartTO("Week 4","100");
		ChartTO monthlyChartVal5 = new  ChartTO("Week 5","100");
		ChartTO WeeklyChartVal1 = new  ChartTO("Monday","100");
		ChartTO WeeklyChartVal2 = new  ChartTO("Tuesday","100");
		ChartTO WeeklyChartVal3 = new  ChartTO("Wednesday","100");
		ChartTO WeeklyChartVal4 = new  ChartTO("Thursday","100");
		ChartTO WeeklyChartVal5 = new  ChartTO("Friday","100");
		ChartTO WeeklyChartVal6 = new  ChartTO("Saturday","100");
		ChartTO WeeklyChartVal7 = new  ChartTO("Sunday","100");
		List<ChartTO> yearlyChartData = new ArrayList<ChartTO>();
		yearlyChartData.add(yearlyChartVal1);
		yearlyChartData.add(yearlyChartVal2);
		yearlyChartData.add(yearlyChartVal3);
		yearlyChartData.add(yearlyChartVal4);
		yearlyChartData.add(yearlyChartVal5);
		yearlyChartData.add(yearlyChartVal6);
		yearlyChartData.add(yearlyChartVal7);
		yearlyChartData.add(yearlyChartVal8);
		yearlyChartData.add(yearlyChartVal9);
		yearlyChartData.add(yearlyChartVal10);
		yearlyChartData.add(yearlyChartVal11);
		yearlyChartData.add(yearlyChartVal12);
		List<ChartTO> monthlyChartData = new ArrayList<ChartTO>();
		monthlyChartData.add(monthlyChartVal1);
		monthlyChartData.add(monthlyChartVal2);
		monthlyChartData.add(monthlyChartVal3);
		monthlyChartData.add(monthlyChartVal4);
		monthlyChartData.add(monthlyChartVal5);
		List<ChartTO> weeklyChartData = new ArrayList<ChartTO>();
		weeklyChartData.add(WeeklyChartVal1);
		weeklyChartData.add(WeeklyChartVal2);
		weeklyChartData.add(WeeklyChartVal3);
		weeklyChartData.add(WeeklyChartVal4);
		weeklyChartData.add(WeeklyChartVal5);
		weeklyChartData.add(WeeklyChartVal6);
		weeklyChartData.add(WeeklyChartVal7);
		reportTO.setCaloriesMonthChart(monthlyChartData);
		reportTO.setCaloriesWeekChart(weeklyChartData);
		reportTO.setCaloriesYearChart(yearlyChartData);

		when(reportService.getWorkoutReport()).thenReturn(reportTO);
		
		mockMvc.perform(get("/workouttracker/report/"))
				.andExpect(status().isOk());
		verify(reportService, times(1)).getWorkoutReport();
	}

}
