package com.main;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.lexer.Lexer;
import com.sd.Token;

public class Main {

    public static void main(String[] args) {
    	
        Token token = new Token();
        try {
        Lexer lexer = new Lexer();
        InputStream fr = new BufferedInputStream(new FileInputStream("src/com/main/lang.txt"));
        int i=0;
        while(lexer.getEof()!=-1) {
        token=lexer.getToken(fr);
        System.out.println(i+":\t"+token);
        i++;
        }	
        fr.close();
        }catch (IOException e){
            e.printStackTrace();
        }


        }
        
}