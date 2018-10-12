package com.company.mh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Set;
import java.util.stream.Collectors;

public class FindMissingDataBetweenTwoFiles {
	
	public void findInfo(String firstFile,String secondFile,String outputFile,String forYear) throws IOException {
		Set<String> firstData = getDataFromFile(firstFile);
		Set<String> secondData = getDataFromFile(secondFile);
		
		firstData.removeAll(secondData);
		print(firstData,outputFile);
	}
	
	private Set<String> getDataFromFile(String filePath) throws IOException{
		//Set<String> infoSet = new TreeSet<>();
		Set<String> set = Files.lines(Paths.get(filePath))
				//.filter(line -> line.endsWith(".csv.gz"))
				.map(String::trim)
				.sorted().collect(Collectors.toSet());
		
		/*set.stream().forEach( line -> {
			String[] fileNameArray = line.split("_");
			if (fileNameArray.length != 7) {
				throw new RuntimeException(
						"File is not valid :" + line+ " from file :"+filePath);
			}

			String hourMonthString = fileNameArray[3];
			if(line == null || line.length() != 10){
				throw new RuntimeException("hour month string is not according to convention..");
			}
			infoSet.add(hourMonthString);
		});*/
		
		return set;
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
