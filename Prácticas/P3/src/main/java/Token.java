package main.java;

public class Token {
    private int clase;
    private String lexema;

    public Token(int clase, String lexema) {
        this.clase = clase;
        this.lexema = lexema;
    }

    public int getClaseLexica() {
        return clase;
    }

    @Override
    public String toString() {
        return "<" + this.clase + "," + this.lexema + ">";
    }
}