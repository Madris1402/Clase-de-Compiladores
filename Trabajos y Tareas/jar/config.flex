package fes.aragon.profe.jflex;
import static fes.aragon.profe.jflex.Tokens.*;
%%
%class Lexico
%type Tokens
L=[a-zA-Z]
D=[0-9]
WHITE=[\t\r\n]
FINCADENA=";"
%{
    public String lexema;
%}
%%
{WHITE} {/* NO HACER NADA */}
"=" {return ASIGNACION;}
"+" {return SUMA;}
"*" {return MULTIPLICACION;}
"-" {return RESTA;}
"/" {return DIVISION;}
{L}({L}|{D})* {lexema=yytext(); return ID;}
(("-"|"+")?{D}+){FINCADENA}|{D}+{FINCADENA} {lexema=yytext();return INT;}
. {return ERROR;}