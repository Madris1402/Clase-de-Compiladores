package fes.aragon.tareas.tarea09;

import java.io.*;

public class Main {
    private static Lexico lexico;
    private Tokens token;

    public static void main(String[] args) {
        Main app = new Main();

        try {
            Reader rd = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "files" + File.separator + "tarea09.fes"));
            app.lexico = new Lexico(rd);
            Tokens resultado;
            do{
                resultado = lexico.yylex();
                System.out.println(resultado);
            }while (resultado != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}