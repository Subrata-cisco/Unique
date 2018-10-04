package com.ranguard.mh;

import java.util.Set;
import java.util.TreeSet;

class Day {
	private int dayno = -1;
	private int totalHoursInDay = 24;
	private Set<Integer> hour = new TreeSet<>();
	
	Day(int dayNo){
		this.dayno = dayNo;
	}
	
	public void addHours(int hourNo){
		if(hour.size() != totalHoursInDay){
			hour.add(hourNo);
		}
	}
	
	public Set<Integer> retrieveHours(){
		return hour;
	}
	
}
