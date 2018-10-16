package com.lexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.TreeMap;

import com.sd.Token;


public class Lexer {
    
	private final int POZZO = 100;
    private TreeMap<Integer, String> symbTbl;
    
    private  char begin;
    private  char forward;
    private static boolean keep;
    public Lexer() {
    	this.symbTbl = new TreeMap<>();
    	this.begin = '\0';
    	this.forward = '\0';
    	this.keep=false;

    }

    public Token getToken(InputStream fr) throws IOException {
        Token toReturn = new Token("INVALID","TOKEN");

        String id = "";
        int state = 0;
        if(!keep) {begin=(char) fr.read();
        forward = begin;
        }
        keep=false;
        
        while (true) {
            switch (state) {
            case 0: 
            		if(forward == '<') {state=1;
            							forward=(char) fr.read();
            							}
            		else if(forward == '=') state=5;
            		else if(forward == '>') {state=6;
            								 forward=(char) fr.read();
            								 }
            		break;
            case 1: if(forward == '=') state=2;
            		else if(forward == '>') state=3;
            		else {state=4;
            			  keep=true;}
            		break;
            case 2: toReturn.setClasse("RELOP");
            		toReturn.setLessema("LE");
            		return toReturn;
            case 3: toReturn.setClasse("RELOP");
    				toReturn.setLessema("NE");
    				return toReturn;
            case 4: toReturn.setClasse("RELOP");
    				toReturn.setLessema("LT");
    				return toReturn;
            case 5: toReturn.setClasse("RELOP");
    				toReturn.setLessema("EQ");
    				return toReturn;
            case 6: if(forward == '=') {state=7;
            							}
            		else {state=8;
            			  keep=true;}
            		break;
            case 7: toReturn.setClasse("RELOP");
    				toReturn.setLessema("GE");
    				return toReturn;
            case 8: toReturn.setClasse("RELOP");
    				toReturn.setLessema("GT");
    				return toReturn;
            case POZZO: toReturn.setClasse("INVALID");
			 			toReturn.setLessema("TOKEN");
			 			return toReturn;
            }
        	
        }//fine while
    }
    

    
    
}
