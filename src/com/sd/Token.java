package com.sd;

public class Token {
    private String classe;
    private String lessema;

    public Token(String classe, String lessema) {
        this.classe = classe;
        this.lessema = lessema;
    }

    public Token(){
        this.classe = null;
        this.lessema = null;
    }

    public String getClasse(){
        return classe;
    }

    public String getLessema(){
        return lessema;
    }

    public void setClasse(String classe){
        this.classe = classe;
    }

    public void setLessema(String lessema) {
        this.lessema = lessema;
    }

    @Override
    public String toString() {
        return "<"+this.classe+", "+this.lessema+">";
    }
}