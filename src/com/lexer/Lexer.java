package com.lexer;

import com.token.Token;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;


public class Lexer {
    private final int POZZO = 100;

    public Lexer() {
    }

    public Token getToken(String fileName) throws IOException {
        FileInputStream fr = new FileInputStream(fileName);
        Token toReturn = new Token();
        boolean scan = true;
        boolean pozzo = false;
        char begin, forward;
        String id = "";
        int state = 0;
        begin = (char) fr.read();
        forward = begin;
        while (scan) {

            System.out.println(forward);
            switch (state) {
                case 0:
                    if (forward == '<') state = 1;
                    else if (forward == '=') state = 5;
                    else if (forward == '>') state = 6;
                    else {state = POZZO;
                          pozzo=true;}
                    break;
                case 1:
                    if (forward == '=') state = 2;
                    else if (forward == '>') state = 3;
                    else state = 4;
                    break;
                case 2:
                    toReturn.setClasse("RELOP");
                    toReturn.setLessema("LE");
                    scan = false;
                    break;
                case 3:
                    toReturn.setClasse("RELOP");
                    toReturn.setLessema("NE");
                    scan = false;
                    break;
                case 4:
                    toReturn.setClasse("RELOP");
                    toReturn.setLessema("LT");
                    scan = false;
                    break;
                case 5:
                    toReturn.setClasse("RELOP");
                    toReturn.setLessema("EQ");
                    scan = false;
                    break;
                case 6:
                    if (forward == '=') state = 7;
                    else state = 8;
                    break;
                case 7:
                    toReturn.setClasse("RELOP");
                    toReturn.setLessema("GE");
                    scan = false;
                    break;
                case 8:
                    toReturn.setClasse("RELOP");
                    toReturn.setLessema("GT");
                    scan = false;
                    break;
                case POZZO:System.out.print(POZZO);
                          break;
            }
            forward = (char) fr.read();
        }//fine while
        fr.close();
        return toReturn;
    }
}
