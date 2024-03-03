package fes.aragon.tareas.tarea03;

import fes.aragon.tareas.tarea01.Gestor;


public class Impares {
    public static void main(String[] args) {

        for (String palabra : Gestor.leer("Impares.fes")) {
            int indice = 0;
            int estadoActual = 0;
            int columna = 1;
            String mensajeError = "";
            int contadorCeros = 0;

            while (indice <= palabra.length() - 1) {
                char c = palabra.charAt(indice);

                if (c != '0' && c != '1') {
                    mensajeError = "Caracter no válido: " + c + " en la columna " + columna;
                    break;
                }

                if (c == '0') {
                    contadorCeros++;
                }

                switch (estadoActual) {
                    case 0:
                        if (c == '1') {
                            estadoActual = 1;
                        } else if (c == '0') {
                            estadoActual = 0;
                        }
                        break;
                    case 1:
                        if (c == '1') {
                            estadoActual = 0;
                        } else if (c == '0') {
                            estadoActual = 1;
                        }
                        break;
                }
                indice++;
                columna++;
            }

            if (mensajeError.isEmpty()) {
                if (contadorCeros % 2 == 1) {
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
