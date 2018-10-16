package com.lexer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.sd.Token;

public class Main {

    public static void main(String[] args) {
    	
    	Scanner scanner = new Scanner(System.in);
        Lexer lexer = new Lexer();
        Token token = new Token();
        try {
        InputStream fr = new BufferedInputStream(new FileInputStream("src/com/lexer/lang.txt"));
        System.out.println("Insert a digit between 1 and 9 to get next token, 0 to stop token flow");
        //lexer.begin=(char) fr.read();
        while(scanner.nextInt() != 0) {
        token=lexer.getToken(fr);
        System.out.println(token);
        }
    	
        fr.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}