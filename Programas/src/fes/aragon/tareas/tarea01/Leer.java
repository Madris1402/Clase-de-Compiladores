// Tarea 01 - Compiladores  - Madrigal Urencio Ricardo - 2609

package fes.aragon.tareas.tarea01;

import java.io.*;
import java.util.*;

public class Leer {
        public static void main(String[] args) {

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
        }
    }