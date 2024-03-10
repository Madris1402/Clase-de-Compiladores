package fes.aragon.profe.jflex;
import java.io.*;
public class Main {
    public static void main(String[] args){
        try {
            Reader rd = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "files" + File.separator + "jflex.txt"));
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
