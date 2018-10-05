package com.ranguard.mh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.YearMonth;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FindEachMissingFile {

	public void findInfo(String inputFile,String outputFile,String forYear) throws IOException {

		Set<String> infoSet = new TreeSet<>();
		Set<String> set = Files.lines(Paths.get(inputFile))
				.filter(line -> line.endsWith(".csv.gz"))
				.map(String::trim)
				.sorted().collect(Collectors.toSet());
		
		set.stream().forEach( line -> {
			String[] fileNameArray = line.split("_");
			if (fileNameArray.length != 7) {
				throw new RuntimeException(
						"File is not valid :" + line);
			}

			String hourMonthString = fileNameArray[3];
			if(hourMonthString == null || hourMonthString.length() != 10){
				throw new RuntimeException("hour month string (3rd) word is not according to convention..");
			}
			infoSet.add(hourMonthString);
		});
		
		if(infoSet.size() > 0){
			String lastDate = ((TreeSet<String>) infoSet).last();
			Set<String>  totalInfoData = generateDataForYear(forYear,lastDate);
			totalInfoData.removeAll(infoSet);
			print(totalInfoData,outputFile);
			
			//totalInfoData.stream().forEach(System.out::println);
		}
		
		
		
	}
	
	private Set<String> generateDataForYear(String yearString,String upto){
		if(yearString == null){
			throw new RuntimeException("Year is not valid.. "+yearString);
		}
		int year = Integer.valueOf(yearString);
		Set<String> totalInfoSet = new TreeSet<>();
		
		outerloop:
		for(int month=1;month<=12;month++){
			YearMonth yearMonthObject = YearMonth.of(year, month);
			int daysInMonth = yearMonthObject.lengthOfMonth(); 
			
			for(int day=1;day<=daysInMonth;day++){
					
				for(int hour=0;hour<24;hour++){
					StringBuilder sb = new StringBuilder();
					sb.append(year)
					  .append(String.format("%02d", month))
					  .append(String.format("%02d", day))
					  .append(String.format("%02d", hour));
					
					if(sb.toString().equals(upto)){
						break outerloop;
					}else{
						totalInfoSet.add(sb.toString());
					}
				}
			}		
			
		}
		return totalInfoSet;
	}
	
	private void print(Set<String>  totalInfoSet,String outPutFile){
		Path path = Paths.get(outPutFile);
		totalInfoSet.stream().forEach(string -> {
			try {
				Files.write(path,(string + "\n").getBytes(),StandardOpenOption.APPEND);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
