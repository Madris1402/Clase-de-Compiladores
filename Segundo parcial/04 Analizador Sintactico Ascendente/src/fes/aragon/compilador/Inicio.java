/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.compilador;

import fes.aragon.herramientas.refactorizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author eliot
 */
public class Inicio {

    private String ruta;

    public Inicio() {
    }

    public String getRuta() throws URISyntaxException {
        ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "fes" + File.separator + "aragon" + File.separator + "compilador" + File.separator +  "fuente.ref";
        return ruta;
    }

    public static void main(String[] args) throws IOException {
        refactorizer refac = new refactorizer();
        refac.refactorizer();

        try {
            Inicio app = new Inicio();
            //app.crear();
            app.correr();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    private void correr() {
        try {
            analisis_sintactico inicio;
            Analizador_Lexico lex = null;
            lex = new Analizador_Lexico(new FileInputStream(this.getRuta()));
            inicio = new analisis_sintactico(lex);
            inicio.parse();
            System.out.println("resultado = " + lex.yytext() + inicio.resultado);
            for (String salida:inicio.datos){
                System.out.println("Datos finales: "+salida);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
