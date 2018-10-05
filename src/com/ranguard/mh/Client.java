package com.ranguard.mh;

import java.io.IOException;

public class Client {
	public static void main(String[] args) throws IOException {
		// files from where the files will be picked up..
		String inputFile = "C:\\Users\\sususaha.ORADEV\\Desktop\\consultB\\fileRepo\\input.txt";
		String outPutFile = "C:\\Users\\sususaha.ORADEV\\Desktop\\consultB\\fileRepo\\out.txt";
		
		FindEachMissingFile finder = new FindEachMissingFile();
		finder.findInfo(inputFile,outPutFile,"2018");
	}
	
}
