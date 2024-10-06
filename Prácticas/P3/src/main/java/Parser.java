package main.java;

import java.io.IOException;
import main.jflex.Lexer;

public class Parser implements ParserInterface {
    private Lexer lexer;
    private int actual;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void eat(int claseLexica) {
        if(actual == claseLexica) {
            try {
                actual = lexer.yylex();
            } catch (IOException ioe) {
                System.err.println("Failed to read next token");
            }
        }
        else
            System.err.println("Se esperaba el token: "+ actual); 
    }

    public void error(String msg) {
        System.err.println("ERROR DE SINTAXIS: "+msg+" en la línea "+lexer.getLine());
    }

    public void parse() {
        try {
            this.actual = lexer.yylex();
        } catch (IOException ioe) {
            System.err.println("Error: No fue posible obtener el primer token de la entrada.");
            System.exit(1);
        }
        S();
        if(actual == 0) //llegamos al EOF sin error
            System.out.println("La cadena es aceptada");
        else 
            System.out.println("La cadena no pertenece al lenguaje generado por la gramática");
    }

    public void S() {
        declaraciones();
        sentencias();
    }

    public void declaraciones() {
        declaracion();
        declaraciones_();
    }
    
    public void declaraciones_() {
        if (actual == ClaseLexica.INT || actual == ClaseLexica.FLOAT) {
            declaracion();
            declaraciones_();
        }
    }

    public void declaracion() {
        tipo();
        lista_var();
        eat(ClaseLexica.PCOMA); // ";"
    }
    
    public void tipo() {
        if (actual == ClaseLexica.INT) {
            eat(ClaseLexica.INT);
        } else if (actual == ClaseLexica.FLOAT) {
            eat(ClaseLexica.FLOAT);
        } else {
            error("Se esperaba 'int' o 'float'");
        }
    }
    
    public void lista_var() {
        eat(ClaseLexica.ID);
        lista_var_();
    }
    
    public void lista_var_() {
        if (actual == ClaseLexica.COMA) {
            eat(ClaseLexica.COMA);
            eat(ClaseLexica.ID);
            lista_var_();
        }
        // si no hay coma, epsilon 
    }
    
    public void sentencias() {
        sentencia();
        sentencias_();
    }
    
    public void sentencias_() {
        if (actual == ClaseLexica.ID || actual == ClaseLexica.IF || actual == ClaseLexica.WHILE) {
            sentencia();
            sentencias_();
        }
    }
    
    public void sentencia() {
        if (actual == ClaseLexica.ID) {
            eat(ClaseLexica.ID);
            eat(ClaseLexica.EQUAL);
            expresion();
            eat(ClaseLexica.PCOMA);
        } else if (actual == ClaseLexica.IF) {
            eat(ClaseLexica.IF);
            eat(ClaseLexica.LPAR);
            expresion();
            eat(ClaseLexica.RPAR);
            sentencias();
            eat(ClaseLexica.ELSE);
            sentencias();
        } else if (actual == ClaseLexica.WHILE) {
            eat(ClaseLexica.WHILE);
            eat(ClaseLexica.LPAR);
            expresion();
            eat(ClaseLexica.RPAR);
            sentencias();
        } else {
            error("Se esperaba una sentencia válida");
        }
    }

    public void expresion() {
        termino();
        expresion_();
    }
    
    public void expresion_() {
        if (actual == ClaseLexica.ADD) {
            eat(ClaseLexica.ADD);
            termino();
            expresion_();
        } else if (actual == ClaseLexica.MINUS) {
            eat(ClaseLexica.MINUS);
            termino();
            expresion_();
        }
    }
    
    public void termino() {
        factor();
        termino_();
    }
    
    public void termino_() {
        if (actual == ClaseLexica.TIMES) {
            eat(ClaseLexica.TIMES);
            factor();
            termino_();
        } else if (actual == ClaseLexica.DIV) {
            eat(ClaseLexica.DIV);
            factor();
            termino_();
        }
    }
    
    public void factor() {
        if (actual == ClaseLexica.ID) {
            eat(ClaseLexica.ID);
        } else if (actual == ClaseLexica.NUM) {
            eat(ClaseLexica.NUM);
        } else if (actual == ClaseLexica.LPAR) {
            eat(ClaseLexica.LPAR);
            expresion();
            eat(ClaseLexica.RPAR);
        } else {
            error("Se esperaba un identificador, número o una expresión entre paréntesis");
        }
    }
    

}