package fes.aragon.gramatica;
import static fes.aragon.gramatica.Tokens.*;
%%
%class Lexico
%type Tokens
L=[a-zA-Z]
D=[0-9]
WHITE=[ \t\r]
%{
    public String lexema;
%}
%%
{WHITE} {/* NO HACER NADA */}
" " { }
"or" {System.out.println("Reconocio "+yytext()+" OR"); return OR;}
"and" {System.out.println("Reconocio "+yytext()+" AND"); return AND;}
"not" {System.out.println("Reconocio "+yytext()+" NOT"); return NOT;}
")" {System.out.println("Reconocio "+yytext()+" parC"); return CIERREPARENTESIS;}
"(" {System.out.println("Reconocio "+yytext()+" parA"); return ABREPARENTESIS;}
"true" {System.out.println("Reconocio "+yytext()+" TRUE"); return TRUE;}
"false" {System.out.println("Reconocio "+yytext()+" FALSE"); return FALSE;}
";" {System.out.println("Reconocio "+yytext()+" ;"); return PUNTOYCOMA;}
. {return ERROR;}