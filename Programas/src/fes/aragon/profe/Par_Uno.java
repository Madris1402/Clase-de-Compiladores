package fes.aragon.profe;

public class Par_Uno {
    public static void main(String[] args){

        String palabra = "1010011";
        int indice = 0;
        int estado = 0;
        while (indice <= palabra.length()-1) {
            char c = palabra.charAt(indice);
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
        }
        if(estado == 0){
            System.out.println("Valida");
        }
        else{
            System.out.println("No Valida");
        }
    }
}
