package fes.aragon.herramientas;

import java.io.*;
import java.util.*;

public class refactorizer {

    public static void refactorizer() throws IOException {
        String archivoEntrada = System.getProperty("user.dir") + File.separator + "fuente.txt" ;

        String archivoSalida = System.getProperty("user.dir") + File.separator + "fuente.ref";

        String contenidoEntrada = leerArchivo(archivoEntrada);

        String contenidoSinSaltos = contenidoEntrada.replaceAll("(\r\n|\n)", "");

        escribirArchivo(archivoSalida, contenidoSinSaltos);


        String contenidoSalida = leerArchivo(archivoSalida);

        String  espacioSimple = contenidoSalida.replaceAll(";", "; ");

        escribirArchivo(archivoSalida, espacioSimple);

        System.out.println("Archivo refactorizado exitosamente!");
    }

    private static String leerArchivo(String rutaArchivo) throws IOException {
        StringBuilder contenido = new StringBuilder();

        try (FileReader lector = new FileReader(rutaArchivo)) {
            int caracter;
            while ((caracter = lector.read()) != -1) {
                contenido.append((char) caracter);
            }
        }

        return contenido.toString();
    }

    private static void escribirArchivo(String rutaArchivo, String contenido) throws IOException {
        try (FileWriter escritor = new FileWriter(rutaArchivo)) {
            escritor.write(contenido);
        }
    }
}
