package com.ranguard.mh;

import java.io.IOException;

public class Client {
	public static void main(String[] args) throws IOException {
		// files from where the files will be picked up..
		String filePath = "C:\\Users\\sususaha.ORADEV\\Desktop\\consultB\\fileRepo\\allFiles.txt";
		MissingHourFinder finder = new MissingHourFinder();
		finder.findInfo(filePath);
	}
}
