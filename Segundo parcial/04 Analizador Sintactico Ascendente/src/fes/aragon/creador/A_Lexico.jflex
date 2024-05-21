package fes.aragon.compilador;
import java_cup.runtime.*;
import java.util.LinkedList;

%%
%{
    public static LinkedList<TError> TablaEL = new LinkedList<TError>();
     public Analizador_Lexico(java.io.InputStream in) {
        this(new java.io.InputStreamReader(in));
      }
%}

%public
%class Analizador_Lexico
%cupsym Simbolos
%cup
%char
%column
%full
%ignorecase
%line
%unicode

%%

/*------------  3ra Area: Reglas Lexicas ---------*/

<YYINITIAL> "true"      { System.out.println("Reconocio "+yytext()+" true"); return new Symbol(Simbolos.tr, yycolumn, yyline, yytext()); }
<YYINITIAL> "false"     { System.out.println("Reconocio "+yytext()+" false"); return new Symbol(Simbolos.fl, yycolumn, yyline, yytext()); }
<YYINITIAL> "and"       { System.out.println("Reconocio "+yytext()+" and"); return new Symbol(Simbolos.and, yycolumn, yyline, yytext()); }
<YYINITIAL> "or"        { System.out.println("Reconocio "+yytext()+" or"); return new Symbol(Simbolos.or, yycolumn, yyline, yytext()); }
<YYINITIAL> "not"       { System.out.println("Reconocio "+yytext()+" not"); return new Symbol(Simbolos.not, yycolumn, yyline, yytext()); }
<YYINITIAL> "("         { System.out.println("Reconocio "+yytext()+" par_a"); return new Symbol(Simbolos.par_a, yycolumn, yyline, yytext()); }
<YYINITIAL> ")"         { System.out.println("Reconocio "+yytext()+" par_c"); return new Symbol(Simbolos.par_c, yycolumn, yyline, yytext()); }
<YYINITIAL> ";"         { System.out.println("Reconocio "+yytext()+" punto_coma"); return new Symbol(Simbolos.punto_coma, yycolumn, yyline, yytext()); }

[ \t\r\n\f]             {/* Espacios en blanco, se ignoran */}

.                       { System.out.println("Error Lexico"+yytext()+" Linea "+yyline+" Columna "+yycolumn);
                          TError datos = new TError(yytext(),yyline,yycolumn,"Error Lexico","Simbolo no existe en el lenguaje");
                          TablaEL.add(datos);}


