package com.lexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import com.sd.KeyWordTbl;
import com.sd.Token;


public class Lexer {
    
	private final int POZZO = 100;
    private TreeMap<Integer, String> symbTbl;
    private KeyWordTbl keyWord;
    private  char forward;
    private static boolean keep;
    private static Integer key;
    
    public Lexer() throws IOException {
    	this.symbTbl = new TreeMap<>();
    	this.keyWord = new KeyWordTbl();
    	this.forward = '\0';
    	this.keep=false;
    	this.key=0;
    }

    public Token getToken(InputStream fr) throws IOException {
        Token toReturn = new Token();
        String str = "";
        int state = 0;
        if(!keep) forward=(char) fr.read();
        keep=false;
        while (true) {
            switch (state) {
            case 0: 
            		if(forward == '<') {state=1;
            							forward=(char) fr.read();
            							}
            		else if(forward == '=') {state=5;}
            		else if(forward == '>') {state=6;
            								 forward=(char) fr.read();
            								 }
            		else state=POZZO;
            		break;
            case 1: if(forward == '=') state=2;
            		else if(forward == '>') state=3;
            		else if(forward != '=' || forward != '>'){state=4;
            			  				     				  keep=true;
            			  				     				  }
            		else state=POZZO;
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
            		else if(forward != '='){state=8;
            		keep=true;
            			  					}
            		else state=POZZO;
            		break;
            case 7: toReturn.setClasse("RELOP");
    				toReturn.setLessema("GE");
    				return toReturn;
            case 8: toReturn.setClasse("RELOP");
    				toReturn.setLessema("GT");
    				return toReturn;
            case 9: if(Character.isLetter(forward)) {state=10;
            										 str+=forward;
            										 forward=(char) fr.read();
            										 }
            		else state=POZZO;
            		break;
            case 10: if(Character.isLetterOrDigit(forward)) {str+=forward;
            												forward=(char) fr.read();
            												 }
            		 else if(!Character.isLetterOrDigit(forward)){state=11;
            		 											  keep=true;}
            		 else state=POZZO;
            		break;
            case 11: 
            		if(keyWord.containsValue(str)) { toReturn.setClasse(str.toUpperCase());
            										 toReturn.setLessema(str);
            										 return toReturn;
            										 }
            		 else {
            			   if(!symbTbl.containsValue(str)) {
            				   							  System.out.println("VUOTA O NON CONTIENE");
            				   							  symbTbl.put(key, str);
            				   							  toReturn.setClasse(key.toString());
            				 							  toReturn.setLessema(str);
            				 							  key++;
            				 							  return toReturn;
            			 								 }
            			 else {
            				 System.out.println("CONTIENE");
            				 Set<Entry<Integer,String>>list=symbTbl.entrySet();
            				 for(Entry entry:list) {
            					 String value= (String) entry.getValue();
            					 if(value.compareTo(str)==0) {Integer k=(Integer) entry.getKey();
            						 						  toReturn.setClasse(""+k);
            						 						  toReturn.setLessema(str);
            						 						  return toReturn;}
            				 }
            			 }
            		 }
            		break;
            case POZZO: 
            			if(forward == '='||forward == '>'|| forward == '<') state=0;
            			else if(Character.isLetter(forward)) state=9;
            			//else if(Character.isDigit(forward)) state=<todo>;
            			else {forward=(char) fr.read();
            			
            			}
            			break;
            }
            
        	
        }//fine while
    }
    

    
    
}
