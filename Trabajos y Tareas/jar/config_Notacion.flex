package fes.aragon.tareas.tarea09;
import static fes.aragon.tareas.tarea09.Tokens.*;
%%
%class Lexico
%type Tokens
D=[0-9]
WHITE=[\t\r\n]
%{
    public String lexema;
%}
%%
{WHITE} {/* NO HACER NADA */}
{D} {return DIGITO;}
"e" {return EXP;}
"+" {return MAS;}
"-" {return MENOS;}
"." {return PUNTO;}
. {return ERROR;}