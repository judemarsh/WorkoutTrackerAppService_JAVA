package com.cts.iiht.fsd.workouttracker.report.to;

import java.io.Serializable;

public class ChartTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public ChartTO() {
	}
	
	public ChartTO(String label, String value) {
		this.label = label;
		this.value = value;
	}
	
	private String label;
	
	private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
