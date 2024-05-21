package fes.aragon.compilador;
import java.util.LinkedList;
import static fes.aragon.compilador.Tokens.*;

%%
%{
    public static LinkedList<TError> TablaEL = new LinkedList<TError>();
     public Lexical_Analyzer(java.io.InputStream in) {
        this(new java.io.InputStreamReader(in));
      }
%}

%public
%class Lexical_Analyzer
%char
%column
%type Tokens
%full
%ignorecase
%line
%unicode

numero = [0-9]+
letra = [a-zA-Z]
id={letra}({letra}|{numero})*
espacio=[ ,\t,\r,\f]+

%%


/*------------  3ra Area: Reglas Lexicas ---------*/

{espacio} {/*Ignore*/}

<YYINITIAL> "+"         { System.out.println("Reconocio "+yytext()+" Mas"); return Plus; }
<YYINITIAL> "*"         { System.out.println("Reconocio "+yytext()+" Por"); return Multiply; }
<YYINITIAL> ";"         { System.out.println("Reconocio "+yytext()+" PuntoyComa"); return Semi; }
<YYINITIAL> "("         { System.out.println("Reconocio "+yytext()+" parA"); return ParA; }
<YYINITIAL> ")"         { System.out.println("Reconocio "+yytext()+" parC"); return ParC; }

<YYINITIAL> {id}    { System.out.println("Reconocio "+yytext()+" id"); return ID; }

.                       { System.out.println("Error Lexico"+yytext()+" Linea "+yyline+" Columna "+yycolumn);
                          TError datos = new TError(yytext(),yyline,yycolumn,"Error Lexico","Simbolo no existe en el lenguaje");
                          TablaEL.add(datos);}

