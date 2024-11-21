%%

// Declaración de los simbolos terminales
%token PLUS MINUS TIMES DIVIDE ASSIGN LPAREN RPAREN
%token INTEGER DECIMAL VAR

// Declaración de los simbolos no terminales
%type <int> Expr Term Factor Num Var

// Precedencia de los operadores
%left PLUS MINUS
%left TIMES DIVIDE

%%

// Definición de la gramática
S: Expr
  | Asig;

Expr: Term Expr'
    ;

Expr': PLUS Term Expr' 
      | MINUS Term Expr' 
      | /* ε */ 
      ;

Term: Factor Term'
    ;

Term': TIMES Factor Term' 
      | DIVIDE Factor Term' 
      | /* ε */ 
      ;

Factor: Num 
      | Var 
      | LPAREN Expr RPAREN 
      | MINUS Expr
      ;

Num: INTEGER Decimal
   ;

Decimal: '.' INTEGER 
        | /* ε */
        ;

Integer: Digito 
        | Digito Integer
        ;

Digito: '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'
      ;

Asig: VAR Var ASSIGN Expr
    ;

Var: Letra Pos
   ;

Pos: Var 
   | /* ε */
   ;

Letra: '_' 
      | 'a'..'z' 
      | 'A'..'Z'
      ;
