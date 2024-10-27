package main.jflex;

import java.io.Reader;
import main.byacc.Parser;
import main.byacc.ParserVal;

%%

%{
    private Parser yyparser;
    public ParserVal yylval;

    public Lexer(Reader r, Parser yyparser) {
        this(r);
        this.yyparser = yyparser;
    }

    public int getLine() { 
        return yyline; 
    }
%}

%public
%class Lexer
%standalone
%unicode
%line

num = ([1-9][0-9]*|0)(\\.[0-9]+)?

%%
// Número entero o decimal
{num} { 
    yylval = new ParserVal(Double.valueOf(yytext())); 
    return Parser.NUM; 
}

// Operadores
"+"          { return Parser.PLUS; }
"-"          { return Parser.MINUS; }
"*"          { return Parser.TIMES; }
"/"          { return Parser.DIV; }
"^"          { return Parser.POW; }
"("          { return Parser.LPAREN; }
")"          { return Parser.RPAREN; }

[ \t\r]+     { /* Ignorar espacios en blanco */ }
\n           { return '\n'; }
<<EOF>>      { return 0; }
.            { return -1; }
