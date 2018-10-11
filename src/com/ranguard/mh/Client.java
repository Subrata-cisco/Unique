package com.ranguard.mh;

import java.io.IOException;

public class Client {
	public static void main(String[] args) throws IOException {
		// files from where the files will be picked up..
		String firstFile = "C:\\Users\\sususaha.ORADEV\\Desktop\\personal\\consultB\\fileRepo\\first.txt";
		String secondFile = "C:\\Users\\sususaha.ORADEV\\Desktop\\personal\\consultB\\fileRepo\\second.txt";
		String outPutFile = "C:\\Users\\sususaha.ORADEV\\Desktop\\personal\\consultB\\fileRepo\\out.txt";
		
		FindMissingDataBetweenTwoFiles finder = new FindMissingDataBetweenTwoFiles();
		finder.findInfo(firstFile,secondFile,outPutFile,"2018");
	}
	
}
