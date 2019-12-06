package com.krithiha.bfit;

import java.io.Serializable;

public class NewWorkoutDetailsModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String activity;
	private String start_time;
	private String end_time;
	private String calorie;
	private String name;
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getCalorie() {
		return calorie;
	}
	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
