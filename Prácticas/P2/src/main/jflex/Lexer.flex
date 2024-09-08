/**
 * Escáner que detecta el lenguaje C_1
*/

package main.jflex;

import main.java.ClaseLexica;
import main.java.Token;

%%

%{

public Token actual;

%}

%public
%class Lexer
%standalone
%unicode


%%
[\t\r\n ]+  {/* La acción léxica puede ir vacía si queremos que el escáner ignore la regla */}

// Reglas léxicas, definimos los patrones necesarios para la identificación de los tokens
"if"            {System.out.println(new Token(ClaseLexica.IF, yytext()));} 
"int"           {System.out.println(new Token(ClaseLexica.INT, yytext()));}
"while"         {System.out.println(new Token(ClaseLexica.WHILE, yytext()));}
"else"          {System.out.println(new Token(ClaseLexica.ELSE, yytext()));}
"float"         {System.out.println(new Token(ClaseLexica.FLOAT, yytext()));}
","             {System.out.println(new Token(ClaseLexica.COMA, yytext()));}
"("             {System.out.println(new Token(ClaseLexica.LPAR, yytext()));}
")"             {System.out.println(new Token(ClaseLexica.RPAR, yytext()));}
";"             {System.out.println(new Token(ClaseLexica.PYC, yytext()));}
([1-9[0-9]+|0]).([0-9]*|([0-9]e[0-9]))          {System.out.println(new Token(ClaseLexica.NUMERO, yytext()));}
([a-z_][a-z_0-9]*){1,31}                   {System.out.println(new Token(ClaseLexica.ID, yytext()));}






