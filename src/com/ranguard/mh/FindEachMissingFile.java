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
import java.util.stream.IntStream;

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
		
		
		Set<String>  totalInfoData = generateDataForYear(forYear);
		totalInfoData.removeAll(infoSet);
		
		/*totalInfoData.stream().forEach(string -> {
			int year = Integer.valueOf(string
					.substring(0, 4));
			int month = Integer.valueOf(string
					.substring(4, 6));
			int day = Integer.valueOf(string
					.substring(6, 8));
			int hour = Integer.valueOf(string
					.substring(8, string.length()));
			
			System.out.println("Missing file for Year :" + year
					+ " month :" + month + " day :" + day
					+ " hour :" + hour);
		});*/
		
		print(totalInfoData,outputFile);
	}
	
	private Set<String> generateDataForYear(String yearString){
		if(yearString == null){
			throw new RuntimeException("Year is not valid.. "+yearString);
		}
		int year = Integer.valueOf(yearString);
		Set<String> totalInfoSet = new TreeSet<>();
		
		
		IntStream.rangeClosed(1, 12).forEach(month -> {
			YearMonth yearMonthObject = YearMonth.of(year, month);
			int daysInMonth = yearMonthObject.lengthOfMonth(); 
			
			IntStream.rangeClosed(1, daysInMonth).forEach(day -> {
				
				IntStream.rangeClosed(0, 23).forEach(hour -> {
					
					StringBuilder sb = new StringBuilder();
					sb.append(year)
					  .append(String.format("%02d", month))
					  .append(String.format("%02d", day))
					  .append(String.format("%02d", hour));
					
					totalInfoSet.add(sb.toString());
				});
				
			});
			
		});
		return totalInfoSet;
	}
	
	private void print(Set<String>  totalInfoSet,String outPutFile){
		Path path = Paths.get(outPutFile);
		totalInfoSet.stream().forEach(string -> {
			try {
				int year = Integer.valueOf(string
						.substring(0, 4));
				int month = Integer.valueOf(string
						.substring(4, 6));
				int day = Integer.valueOf(string
						.substring(6, 8));
				int hour = Integer.valueOf(string
						.substring(8, string.length()));
				
				StringBuilder sb = new StringBuilder();
				sb.append("Missing file for Year :" + year
						+ " month :" + month + " day :" + day
						+ " hour :" + hour);
				Files.write(path,(sb.toString() + "\n").getBytes(),StandardOpenOption.APPEND);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
