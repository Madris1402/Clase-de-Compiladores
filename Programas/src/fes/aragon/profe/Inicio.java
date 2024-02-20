package fes.aragon.profe;

public class Inicio {

    public static void main(String[] args) {
        Matriz app = new Matriz();
        app.setToken("mike");
        try {
            int verifica = app.inicio();
            if (verifica == 1) {
                System.out.println("Cadena valida");
            }else {
                System.out.println("Cadena invalida");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e);
        }
    }

}
