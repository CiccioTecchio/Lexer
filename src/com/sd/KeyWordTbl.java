package com.sd;

import java.util.TreeMap;

public class KeyWordTbl {

	private TreeMap<String,String> keyWord;
	
	public KeyWordTbl() {
		this.keyWord = new TreeMap<>();
		keyWord.put("IF", "if");
		keyWord.put("THEN", "then");
		keyWord.put("ELSE", "else");
		keyWord.put("INT", "int");
	}
	
	public Token search(String key) {
		String value;
		value=this.keyWord.get(key);
		Token toReturn = new Token(key,value);
		return toReturn;
	}
}
