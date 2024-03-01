package fes.aragon.profe.cadenasTexto;

public class Inicio {

    public static void main(String[] args) {
        Lexico app = new Lexico();
        app.setToken("Mike");
        try {
            int verifica = app.inicio();
            if (verifica == 1) {
                System.out.print("Cadena valida");
            }else {
                System.out.print("Cadena invalida");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
