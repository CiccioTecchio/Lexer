package com.lexer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.sd.KeyWordTbl;
import com.sd.Token;


public class Lexer {
	public static Logger logger=Logger.getLogger("global");
	private final int POZZO = 100;
    private TreeMap<Integer, String> symbTbl;
    private KeyWordTbl keyWord;
    private  char forward;
    private static boolean keep=false;;
    private static Integer key=0;
    private int state;
    
    public Lexer() throws IOException {
    	this.symbTbl = new TreeMap<>();
    	this.keyWord = new KeyWordTbl();
    	this.state=0;
    }

    public Token getToken(InputStream fr) throws IOException {
        Token toReturn = new Token();
        String str = "";
        String num = "";
        state = 0;
        if(!keep) forward=(char) fr.read();
        keep=false;
        if(!route(forward))forward=(char)fr.read();
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
            case 9: //if(Character.isLetter(forward)) {
            										 state=10;
            										 str+=forward;
            										 forward=(char) fr.read();
            						//				 }
            	//	else state=POZZO;
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
            				   							  symbTbl.put(key, str);
            				   							  toReturn.setClasse(key.toString());
            				 							  toReturn.setLessema(str);
            				 							  key++;
            				 							  return toReturn;
            			 								 }
            			 else {
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
            case 12: state=13;
			 		 num+=forward;
			 		 forward=(char) fr.read();
            	     break;
            case 13: if(Character.isDigit(forward)) {num+=forward;
            									     forward=(char) fr.read();}
            		 else if(forward == '.') {num+=forward;
            		 						  state=14;
            		 						  forward=(char) fr.read();}
            		 else if(forward == 'E') {num+=forward;
            			                      state=16;
            			                      forward=(char) fr.read();}
            		 else if(forward != '.' || forward != 'E' || !Character.isDigit(forward)){state=20;keep=true; }
            		 else state=POZZO;
            		 break;
            case 14: if(Character.isDigit(forward)) {num+=forward;
            										 forward=(char) fr.read();}
            		 else if(!Character.isDigit(forward)){state=21;
            		 									  keep=true;}
            		 else state=POZZO;
            		 break;
            case 20: toReturn.setClasse("NCONST");
            		 toReturn.setLessema(num);
            		 return toReturn;
            case 21: toReturn.setClasse("RCONST");
            		 toReturn.setLessema(num);
            		 return toReturn;
            case POZZO: 
            		   if(!route(forward))forward=(char)fr.read();
            		   break;
            }
            
        }//fine while
    }
    /**
     * @description prende un carattere e vede dove reindirizzarlo
     * @param forward carattere da esaminare
     * @return se c'è bisogno di leggere un nuovo carattere restituisce false, true altrimenti
     * @throws IOException 
     */
    private boolean route(char forward) {
    	boolean toReturn=false;
    	if(forward == '='||forward == '>'|| forward == '<') {state=0;
    														 toReturn=true;}
		else if(Character.isLetter(forward)) {state=9;
											  toReturn=true;}
		else if(Character.isDigit(forward)) {state=12;
    										   toReturn=true;}
		else toReturn=false;
			 
    	return toReturn;
    	}

    
    
}
