package com.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.lexer.Lexer;
import com.sd.Token;

public class Main {

    public static void main(String[] args) {
    	
    	Scanner scanner = new Scanner(System.in);
        
        Token token = new Token();
        try {
        Lexer lexer = new Lexer();
        //Scanner scanner =new Scanner(new File("src/com/main/lang.txt"));
        Thread t = new Thread();
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