package fes.aragon.profe;

import java.util.ArrayList;

public class Par_UnoFinal {
    public static void main(String[] args){

        ArrayList<String> palabras = new ArrayList<>();
        palabras.add("1010011");
        palabras.add("1100101");
        palabras.add("0101010");
        palabras.add("011101m");
        palabras.add("011101m");
        palabras.add("01a01");

        for (String palabra : palabras) {
            int indice = 0;
            int estado = 0;
            String mensajeError = "";
            int columna = 1;

            while (indice <= palabra.length()-1) {
                char c = palabra.charAt(indice);

                if (c != '0' && c != '1') {
                    mensajeError = "contiene el Caracter no válido: " + c + " en la columna " + columna;
                    break;
                }

                switch (estado) {
                    case 0:
                        if (c == '1'){
                            estado = 1;
                        }

                        else if (c == '0') {
                            estado = 0;
                        }
                        break;
                    case 1:
                        if (c == '1'){
                            estado = 0;
                        }

                        else if (c == '0') {
                            estado = 1;
                        }
                        break;
                }
                indice++;
                columna++;
            }

            if (mensajeError.isEmpty()) {
                if(estado == 0){
                    System.out.println("La Palabra \"" + palabra + "\" es Valida");
                }
                else{
                    System.out.println("La Palabra \"" + palabra + "\" No es Valida");
                }
            } else {
                System.out.println("La Palabra \"" + palabra + "\": " + mensajeError);
            }
        }
    }
}
