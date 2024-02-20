package fes.aragon.profe;

public class Matriz {

    private int indice = 0;
    private int error = -1;
    private int aceptar = 1;

//    Variables del AFD
    private int estado = 0;
    private int columna = 0;
    private int [][] matriz = {{2, 1, -1}, {1, 1, -1}, {2, 2, 1}};

    private String token = "";

    public Matriz() {

    }

    public void setToken(String Token){
        this.token = token;
    }

    private char siguienteCaracter() {
        char c =' ';
        if(indice<token.length()) {
            c = token.charAt(indice);
            indice++;
        }
        return c;
    }

    public int inicio() throws Exception {

        char c = ' ';

        do {
            c = siguienteCaracter();
            if(Herramienta.letra(c)) {
                columna = 0;
            }
            else if (Herramienta.numero(c)) {
                columna = 1;

            }
            else if (Herramienta.finCadena(c)){
                columna = 2;
            }
            else{
                error =0;
            }
            if (error !=0){
                estado = matriz[estado][columna];
            }
            else{
                throw new Exception("Palabra no valida");
            }

        } while(!Herramienta.finCadena(c));
        return estado;
    }

}

