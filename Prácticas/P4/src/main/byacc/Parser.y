%{
  import java.lang.Math;
  import java.io.Reader;
  import java.io.IOException;
  import main.jflex.Lexer;
%}

/* Declaraciones de YACC */
%token NUM NL
%token PLUS MINUS TIMES DIV POW NEG LPAREN RPAREN

%left PLUS MINUS
%left TIMES DIV
%right POW /* Exponenciación */
%left NEG   /* Negación unaria */
%nonassoc LPAR RPAR

/* Gramática */
%%
input:
    /* Cadena vacía */
  | input line
;

line:
    NL
  | exp NL { System.out.println("Resultado: " + $1.dval); }
;

exp:
    NUM              { $$ = $1; }
  | exp PLUS exp     { $$ = new ParserVal($1.dval + $3.dval); }
  | exp MINUS exp    { $$ = new ParserVal($1.dval - $3.dval); }
  | exp TIMES exp     { $$ = new ParserVal($1.dval * $3.dval); }
  | exp DIV exp      { $$ = new ParserVal($1.dval / $3.dval); }
  | MINUS exp %prec NEG { $$ = new ParserVal(-$2.dval); }
  | exp POW exp      { $$ = new ParserVal(Math.pow($1.dval, $3.dval)); }
  | LPAREN exp RPAREN   { $$ = $2; }
;

%%

/* Instancia del lexer */
Lexer scanner;

/* Constructor del parser */
public Parser(Reader r) {
  this.scanner = new Lexer(r, this);
}

/* Método para establecer yylval */
public void setYylval(ParserVal yylval) {
  this.yylval = yylval;
}

/* Método de parseo */
public void parse() {
  this.yyparse();
}

/* Manejo de errores */
void yyerror(String s) {
  System.out.println("Error sintáctico: " + s);
}

/* Método para obtener el token actual */
int yylex() {
  int yyl_return = -1;
  try {
    yyl_return = scanner.yylex();
  } catch (IOException e) {
    System.err.println("Error de E/S: " + e.getMessage());
  }
  return yyl_return;
}
