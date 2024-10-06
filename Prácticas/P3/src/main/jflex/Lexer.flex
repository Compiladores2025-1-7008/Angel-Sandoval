package main.jflex;

import main.java.ClaseLexica;
import main.java.Token;

%%

%{

public Token actual;
public int getLine() { return yyline; }

%}

%public
%class Lexer
%standalone
%unicode
%line

espacio=[ \t\n]

id = ([a-z_][a-z_0-9]*){1,31}
numero = ([0-9]+)

%%

{espacio}+ { }
"int" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.INT; }
"float" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.FLOAT; }
"if" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.IF; }
"else" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.ELSE; }
"while" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.WHILE; }
"+" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.ADD; }
"-" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.MINUS; }
"*" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.TIMES; }
"/" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.DIV; }
"=" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.EQUAL; }
"," { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.COMA; }
";" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.PCOMA; }
"(" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.LPAR; }
")" { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.RPAR; }
{id} { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.ID; }
{numero} { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.NUM; }

<<EOF>> { return 0; }
. { return -1; }
