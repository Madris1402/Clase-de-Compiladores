package fes.aragon.tareas.tarea08;
import fes.aragon.tareas.tarea08.Lexico;

import java.io.*;
public class Main {
    private Lexico lexico;
    private Tokens token;
    public static void main(String[] args){
        Main app = new Main();

        try {
            Reader rd = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "files" + File.separator + "tarea08.fes"));
                app.lexico = new Lexico(rd);
                app.token = app.lexico.yylex();
                app.S();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void S() throws IOException{
        System.out.println("S");
        E();
        token = lexico.yylex();
        if (token != Tokens.PUNTOYCOMA){
            System.out.println("Error, se esperaba ;");
        }
        return;
    }
    private void E() throws IOException{
        System.out.println("E");
        T();
        Ep();
        return;
    }

    private void Ep() throws IOException {
        if (token == Tokens.OR){
            token = lexico.yylex();
            T();
            Ep();
        }else{
            System.out.println("Error, se espera or");
        }
    }

    private void T() throws IOException{
        System.out.println("T");
        F();
        Tp();
    }

    private void Tp() throws IOException {
        if(token == Tokens.AND){
            F();
            Tp();
        } else {
            System.out.println("Error, se espera and");
        }
    }

    private void F() throws IOException{
        System.out.println("F");

        if (token == Tokens.NOT){
            token = lexico.yylex();
            F();
        } else{
            return;
        }
    }

}