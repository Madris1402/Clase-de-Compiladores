// Tarea 01 - Compiladores  - Madrigal Urencio Ricardo - 2609

package fes.aragon.tareas.tarea01;

import java.io.*;
import java.util.*;

public class Crear {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String cadena = "";
        System.out.println("Ingresa las cadenas que quieres guardar (termina con una cadena vacía): ");
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            cadena += input + System.lineSeparator();
        }
        System.out.println("Ingresa el nombre del archivo: ");
        String archivo = scanner.nextLine();

        try {
            FileWriter crear = new FileWriter(System.getProperty("user.dir") + File.separator + "files" + File.separator + archivo + ".fes");
            BufferedWriter buffer = new BufferedWriter(crear);
            buffer.write(cadena);
            buffer.close();
            System.out.println("Archivo creado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}