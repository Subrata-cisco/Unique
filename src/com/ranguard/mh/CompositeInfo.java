package com.ranguard.mh;

import java.time.YearMonth;

class CompositeInfo {
	
	private Year years = null;
	
	public void addInfo(int y,int m,int d,int h) {
		if(years == null){
			years = new Year(y);
		}
		
		Month month = years.getMonth(m);
		if(month == null){
			YearMonth yearMonthObject = YearMonth.of(years.getYearNo(), m);
			int daysInMonth = yearMonthObject.lengthOfMonth(); 
			month = new Month(m,daysInMonth);
		}
		
		Day days = month.getDay(d);
		if(days == null){
			days = new Day(d);
		}
		
		days.addHours(h);
		month.addDays(d,days);
		years.addMonths(m, month);
	}
	
	public Year getYears(){
		return years;
	}
}
