package fes.aragon.tareas.tarea08;
import fes.aragon.tareas.tarea08.Lexico;

import java.io.*;
public class Main {
    public static void main(String[] args){
        try {
            Reader rd = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "files" + File.separator + "tarea08.fes"));
            Lexico lexico = new Lexico(rd);
            Tokens resultado;
            do{
                resultado = lexico.yylex();
                System.out.println(resultado);
            }while (resultado != null);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}