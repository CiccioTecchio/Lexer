package com.sd;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class KeyWordTbl extends TreeMap<String,String> {
	
	public KeyWordTbl() throws IOException {
		init();
	}
/**
 * read in keywords.txt and insert the readed value
 * @throws IOException
 */
	private void init() throws IOException {
		File file = new File("src/com/sd/keywords.txt");
		Scanner in = new Scanner(file);
		String keyword="";
		while(in.hasNext()) {
			keyword=in.next();
			put(keyword.toUpperCase(),keyword.toLowerCase());
		}
		in.close();
	}
	
}