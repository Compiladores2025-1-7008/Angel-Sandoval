/**
 * Escáner que detecta números y palabras
*/

%%

%public
%class Lexer
%standalone

digito=[0-9]

hexadecimal=0[xX][0-9a-fA-F]+
identificador=([a-zA-Z][a-zA-Z_0-9]{0,31})
reservados= "Int" | "String" | "Bool" | "class" | "void" | "class"
espacio=[\t\n\r]*



%%
{digito}+ { System.out.print("Encontré un número: "+yytext()+"\n"); }
{espacio} {System.out.print("Encontre un espacio en blanco" + "\n");}
{hexadecimal} {System.out.print("Encontre un Hexadecimal: " +yytext()+ "\n" );}
{reservados} { System.out.print("Encontre la palabra reservada: " +yytext()+ "\n"); }
{identificador} { System.out.print("Encontre un identificador valido: "+yytext()+"\n"); }