package com.ranguard.mh;

import java.util.HashMap;
import java.util.Map;

class Year {
	private int yearNo = -1;
	private int totalMonthInYear = 12;
	private Map<Integer,Month> months = new HashMap<>();
	
	Year(int whichYear){
		yearNo = whichYear;
	}
	
	public void addMonths(Integer monthNo , Month months){
		if(this.months.size() != totalMonthInYear){
			this.months.put(monthNo,months);
		}
	}
	
	public Map<Integer,Month> retrieveMonths(){
		return months;
	}
	
	public Month getMonth(Integer monthNo){
		return this.months.get(monthNo);
	}
	
	public int getYearNo(){
		return yearNo;
	}
	
}
