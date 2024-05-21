package fes.aragon.compilador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    private String path;

    public Main(){
    }

    public static void main(String[] args) {
        try {
            Main app = new Main();
            app.run();
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private void run() throws URISyntaxException, IOException {
        ASPD_Analyzer myAnalyzer = new ASPD_Analyzer();
        
        myAnalyzer.StartAnalysis();
    }
}
