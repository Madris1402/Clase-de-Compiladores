package fes.aragon.tareas.tarea03;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Pares {
    public static void main(String[] args) {

        // Leer Archivos
        Scanner scanner = new Scanner(System.in);
        System.out.println("Buscar Nombre del Archivo: ");
        String archivo = scanner.nextLine();

        ArrayList<String> palabras = new ArrayList<>();
        File f = new File(System.getProperty("user.dir") + File.separator + "files" + File.separator + archivo);
        if (f.exists()) {
            System.out.println("Archivo encontrado");
            System.out.println("Contenido: ");

            try {
                BufferedReader obj = new BufferedReader(new FileReader(f));
                String palabra;
                while ((palabra = obj.readLine()) != null){
                    if (!palabra.trim().isEmpty()) {
                        palabras.add(palabra);
                    }
                }
                System.out.println(palabras);
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e1){
                e1.printStackTrace();
            }
        }
        else{
            System.out.println("No se encontro el archivo");
        }

        for (String palabra : palabras) {
            int indice = 0;
            int estadoActual = 0;
            int columna = 1;
            String mensajeError = "";
            int contadorUnos = 0;
            int contadorParesUnos = 0;

            while (indice <= palabra.length() - 1) {
                char c = palabra.charAt(indice);

                if (c != '0' && c != '1') {
                    mensajeError = "Caracter no válido: " + c + " en la columna " + columna;
                    break;
                }

                if (c == '1') {
                    contadorUnos++;
                }

                switch (estadoActual) {
                    case 0:
                        if (c == '0') {
                            estadoActual = 0;
                        } else if (c == '1') {
                            estadoActual = 1;
                        }
                        break;
                    case 1:
                        if (c == '0') {
                            estadoActual = 2;
                            contadorParesUnos++;
                        } else if (c == '1') {
                            estadoActual = 0;
                        }
                        break;
                    case 2:
                        if (c == '0') {
                            estadoActual = 1;
                        } else if (c == '1') {
                            estadoActual = 2;
                        }
                        break;
                }
                indice++;
                columna++;
            }

            if (mensajeError.isEmpty()) {
                if (contadorUnos % 2 == 0 && contadorParesUnos >= 0) {
                    System.out.println("Palabra \"" + palabra + "\": Valida");
                } else {
                    System.out.println("Palabra \"" + palabra + "\": No Valida");
                }
            } else {
                System.out.println("Palabra \"" + palabra + "\": " + mensajeError);
            }
        }
    }

}
