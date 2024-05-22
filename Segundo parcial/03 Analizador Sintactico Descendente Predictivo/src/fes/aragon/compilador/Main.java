package fes.aragon.compilador;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    private String path;

    public Main(){
    }

    public static void main(String[] args){

        try {

            Main app = new Main();
            app.run();
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private void run() throws URISyntaxException, IOException {
        Analizador_Sintactico_DP myAnalyzer = new Analizador_Sintactico_DP();
        
        myAnalyzer.Inicio();
    }
}
