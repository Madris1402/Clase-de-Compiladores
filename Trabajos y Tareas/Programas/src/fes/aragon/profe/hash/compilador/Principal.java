/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.profe.hash.compilador;

import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 *
 * @author PC-16
 */
public class Principal {

    private String ruta;

    public Principal() {
    }

    public String getRuta() throws URISyntaxException {
        ruta = System.getProperty("user.dir") + File.separator + "files" + File.separator + "fuente.txt";
        return ruta;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Principal app = new Principal();
        parser inicio;
        Lexico lex = null;
        try {
            lex = new Lexico(new FileInputStream(app.getRuta()));
            inicio = new parser(lex);
            inicio.parse();
            //inicio.action_obj.imprimirValor();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("linea: " + lex.getYyline()
                    + " columna: " + lex.getYy_currentPos());
            //e.printStackTrace();
        }
        System.out.println("----------");

        /*for (String arg : inicio.getAction_obj().getDatos()) {
            System.out.println(arg);            
        }*/
    }
}
