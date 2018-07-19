package com.cts.iiht.fsd.workouttracker.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.iiht.fsd.workouttracker.report.service.ReportService;
import com.cts.iiht.fsd.workouttracker.report.to.ReportTO;

@RestController
@RequestMapping("/workouttracker/report")
@CrossOrigin
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ReportTO getWorkoutReport(){
		return reportService.getWorkoutReport();
	}
	
}
