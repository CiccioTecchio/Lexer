package com.lexer;

import com.token.Token;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Lexer lexer = new Lexer();
        Token token = new Token();
        try {
        token=lexer.getToken("com/lexer/lang.txt");
        System.out.print(token);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}