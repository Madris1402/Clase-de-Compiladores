package fes.aragon.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

import fes.aragon.extras.MusicaCiclica;
import fes.aragon.extras.Sonidos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static java.lang.Integer.parseInt;

public class Fondo extends ComponentesJuego {
    private int yy = 0;
    private int xx = 0;
    private int my = 305;
    private int mx = 205;
    private int mxx = 0;
    private int myy = 0;
    private int py = 305;
    private int px = 205;
    private int pxx = 0;
    private int pyy = 0;
    private Image arribaImg;
    private Image abajoImg;
    private Image derechaImg;
    private Image izquierdaImg;
    private Image imagen;
    private Image imagen2;
    private Image imagen3;
    private Image manzana;
    private Image manzana2;
    private Image ave;
    private Image ave2;
    private Stage ventana;
    private ArrayList<String> comandos = new ArrayList<>();
    private ArrayList<String> tempcom = new ArrayList<>();
    private int ancho = 40;
    private int alto = 40;
    private boolean iniciar = false;
    private GraphicsContext graficos;
    private int indice = 0;
    private int moverCuadros = 0;
    private String comando = "";
    private boolean arriba = false;
    private boolean abajo = false;
    private boolean derecha = false;
    private boolean izquierda = false;

    private Thread hiloFondo;
    ///
    private Integer variableX;
    private String operadorLogico;
    private Integer limiteVariableX;

    @SuppressWarnings("deprecation")
    @Override
    public void stop() throws Exception {
        hiloFondo.stop();
    }
    public Fondo(int x, int y, String imagen, int velocidad, Stage ventana) {
        super(x, y, imagen, velocidad);
        try{
            System.out.println(this.getClass().getResource(imagen).getFile());
            this.derechaImg = new Image(new File(this.getClass().getResource(imagen).getFile()).toURI().toString());
            this.izquierdaImg = new Image(new File(this.getClass().getResource("/fes/aragon/imagenes/izquierda.png").getFile()).toURI().toString());
            this.arribaImg = new Image(new File(this.getClass().getResource("/fes/aragon/imagenes/arriba.png").getFile()).toURI().toString());
            this.abajoImg = new Image(new File(this.getClass().getResource("/fes/aragon/imagenes/abajo.png").getFile()).toURI().toString());
            this.manzana = new Image(new File(this.getClass().getResource("/fes/aragon/imagenes/manzana.png").getFile()).toURI().toString());
            this.manzana2 = new Image(new File(this.getClass().getResource("/fes/aragon/imagenes/manzana2.png").getFile()).toURI().toString());
            this.ave = new Image(new File(this.getClass().getResource("/fes/aragon/imagenes/ave.png").getFile()).toURI().toString());
            this.ave2 = new Image(new File(this.getClass().getResource("/fes/aragon/imagenes/ave2.png").getFile()).toURI().toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.randpos();
        this.imagen = derechaImg;
        this.imagen2 = this.manzana;
        this.imagen3 = this.ave;
        this.ventana = ventana;
    }
    public int randpos(){

        Random random = new Random();

        this.px = 55 + 50 * (random.nextInt(8) + 2);
        this.py = 55 + 50 * (random.nextInt(8) + 2);
        System.out.println("ubicacion del ave: x = " + (((px - 55) / 50) + 1)  + " y = " + (((py - 55) / 50) + 1));

        this.mx = 55 + 50 * (random.nextInt(8) + 2);
        this.my = 55 + 50 * (random.nextInt(8) + 2);
        System.out.println("ubicacion de la manzana: x = " + (((mx - 55) / 50) + 1)  + " y = " + (((my - 55) / 50) + 1));


        while ((my == py) && (mx == px))  {
            this.py = 55 + 50 * (random.nextInt(6) + 2);
            System.out.println("ERROR 'ubicaciones iguales': nueva ubicacion del ave: x = " + (((px - 55) / 50) + 1)  + " y = " + (((py - 55) / 50) + 1));;

            this.mx = 55 + 50 * (random.nextInt(8) + 2);
            System.out.println("ERROR 'ubicaciones iguales': nueva ubicacion de la manzana: x = " + (((mx - 55) / 50) + 1)  + " y = " + (((my - 55) / 50) + 1));
        }
        return 0;
    }
    @Override
    public void pintar(GraphicsContext graficos) {
        // TODO Auto-generated method stub
        this.graficos = graficos;
        int xx = 50;
        int yy = 50;
        for (int j = 1; j <= 10; j++) {
            for (int i = 1; i <= 10; i++) {
                graficos.strokeRect(xx, yy, 50, 50);
                xx += 50;
            }
            xx = 50;
            yy += 50;
        }
        graficos.drawImage(imagen2, mx, my, ancho, alto);
        graficos.drawImage(imagen3, px, py, ancho, alto);
        graficos.drawImage(imagen, x, y, ancho, alto);
        if (!comandos.isEmpty()) {
            if (indice < comandos.size()) {
                graficos.fillText(comandos.get(indice), 55, 40);
                graficos.clearRect(0, 0, 55, 40);
            }
        }

//        graficos.drawImage(imagen3, (px+(ancho+10)*2), y,ancho,alto);
//
//        graficos.drawImage(imagen, x, (y+(alto+10)*2),ancho,alto);
//
//        graficos.drawImage(imagen, (x+(ancho+10)*2), (y+(alto+10)*2),ancho,alto);
    }

    @Override
    public void teclado(KeyEvent evento, boolean presiona) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        if (presiona) {
            switch (evento.getCode().toString()) {
                case "A":
                    try {
                        this.abrirArchivo();
                        graficos.clearRect(0, 0, 600, 600);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case "R":
                    iniciar();
                    this.ejecutar();
                    this.iniciar = true;
                    break;

                case "C":
                    randpos();
                    graficos.clearRect(0, 0, 600, 600);
                    break;
            }
        }
    }

    @Override
    public void raton(KeyEvent evento) {

    }

    private void avecapt(){ // Ave captura gusano
        if (x == px && y == py){
            this.comandos.clear();
            System.out.println("te capturo el ave");
            this.comandos = (ArrayList<String>) this.tempcom.clone();
            this.imagen = null;
            graficos.clearRect(0, 0, 600, 600);
            this.imagen3 = this.ave2;
            this.iniciar = false;
            this.indice = 1;



            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Interprete");
            alert.setHeaderText("Has Perdido");
            alert.setContentText("Te capturo el Ave");
            alert.show();
            Sonidos perder = new Sonidos("perder");
            hiloFondo = new Thread(perder);
            hiloFondo.start();
        }
    }

    @Override
    public void logicaCalculos() throws IOException, ClassNotFoundException {
        if (iniciar) {
            switch (this.comando) {

                case "arriba", "abajo", "izquierda", "derecha", "repetir", "coloca", "manzana", "ave":
                    indice++;
                    graficos.clearRect(0, 0, 600, 600);
                    this.ejecutar();
                    break;

                case "mover":
                    //determinar limites

                    if (y < 55){ // limite superior
                        System.out.println("limite alcanzado");
                        y = 55;
                        System.out.println("error en la ejecucion");
                        this.iniciar = false;
                        this.indice = 1;
                        this.Limerror();
                        break;
                    }

                    if (y > 505){ // limite inferior
                        System.out.println("limite alcanzado");
                        y = 505;
                        System.out.println("error en la ejecucion");
                        this.iniciar = false;
                        this.indice = 1;
                        this.Limerror();
                        break;
                    }

                    if (x < 55){ // limite izquierdo
                        System.out.println("limite alcanzado");
                        x = 55;
                        System.out.println("error en la ejecucion");
                        this.iniciar = false;
                        this.indice = 1;
                        this.Limerror();
                        break;
                    }

                    if (x > 505){ // limite derecho
                        System.out.println("limite alcanzado");
                        x = 505;
                        System.out.println("error en la ejecucion");
                        this.iniciar = false;
                        this.indice = 1;
                        this.Limerror();
                        break;
                    }

                    this.avecapt();

                    // acciones

                    if (arriba) {
                        if (y > yy) {
                            y -= velocidad;
                            graficos.clearRect(0, 0, 600, 600);
                        } else {
                            indice++;
                            this.ejecutar();
                        }
                    }
                    if (abajo) {
                        if (y < yy) {
                            y += velocidad;
                            graficos.clearRect(0, 0, 600, 600);
                        } else {
                            indice++;
                            this.ejecutar();
                        }
                    }
                    if (izquierda) {
                        if (x > xx) {
                            x -= velocidad;
                            graficos.clearRect(0, 0, 600, 600);
                        } else {
                            indice++;
                            this.ejecutar();
                        }
                    }
                    if (derecha) {
                        if (x < xx) {
                            x += velocidad;
                            graficos.clearRect(0, 0, 600, 600);
                        } else {
                            indice++;
                            this.ejecutar();
                        }
                    }

            }
        }
    }

    private void Limerror(){


        graficos.clearRect(0, 0, 600, 600);
        Sonidos limite = new Sonidos("mensaje");
        hiloFondo = new Thread(limite);
        hiloFondo.start();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Interprete");
        alert.setHeaderText("Limirte alcanzado");
        alert.setContentText("No puedes moverte mas");
        alert.show();
    }
    private void ejecutar() throws IOException, ClassNotFoundException {

        this.avecapt();

        if (x == mx && y == my){
            System.out.println("encontraste la manzana");
            this.imagen = null;
            graficos.clearRect(0, 0, 600, 600);
            this.imagen2 = this.manzana2;
            this.iniciar = false;
            this.indice = 1;
            this.comandos.clear();
            this.comandos = (ArrayList<String>) this.tempcom.clone();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Interprete");
            alert.setHeaderText("Se alcanzo el ultimo comando");
            alert.setContentText("Has encontrado la Manzana");
            alert.show();
            Sonidos ganar = new Sonidos("ganar");
            hiloFondo = new Thread(ganar);
            hiloFondo.start();
        }

        if (indice < comandos.size()) {
            String string = comandos.get(indice);
            String[] datos = string.split(" ");
            System.out.println(datos[0]);

            switch (datos[0]) {

                case "arriba":
                    this.arriba = true;
                    this.abajo = false;
                    this.izquierda = false;
                    this.derecha = false;
                    this.imagen = this.arribaImg;
                    this.comando = "arriba";
                    break;

                case "abajo":
                    this.arriba = false;
                    this.abajo = true;
                    this.izquierda = false;
                    this.derecha = false;
                    this.imagen = this.abajoImg;
                    this.comando = "abajo";
                    break;

                case "izquierda":
                    this.arriba = false;
                    this.abajo = false;
                    this.izquierda = true;
                    this.derecha = false;
                    this.imagen = this.izquierdaImg;
                    this.comando = "izquierda";
                    break;

                case "derecha":
                    this.arriba = false;
                    this.abajo = false;
                    this.izquierda = false;
                    this.derecha = true;
                    this.imagen = this.derechaImg;
                    this.comando = "derecha";
                    break;

                case "repetir":
                    int indiceInicial = indice + 1;
                    int indiceFin = comandos.indexOf("fin");
                    int repeticiones = parseInt(datos[1]) - 1;
                    for (int i = 0; i < repeticiones; i++){
                        int indiceRepetir = indiceInicial;
                        while (!Objects.equals(comandos.get(indiceRepetir), "fin")){
                            comandos.add(indiceFin + 1, comandos.get(indiceRepetir));
                            indiceRepetir++;
                            indiceFin++;
                        }

                    }
                    System.out.println("comandos a repetir: " + repeticiones);
                    this.comando = "repetir";
                    break;

                case "coloca":
                    xx = parseInt(datos[1]);
                    yy = parseInt(datos[2]);
                    this.x = 55 + (50 * (xx - 1));
                    this.y = 55 + (50 * (yy - 1));
                    System.out.println("se coloco gusano en: x = " + (((px - 55) / 50) + 1)  + " y = " + (((py - 55) / 50) + 1));
                    this.comando = "coloca";
                    break;

                case "manzana":
                    mxx = parseInt(datos[1]);
                    myy = parseInt(datos[2]);
                    this.mx = 55 + (50 * (mxx - 1));
                    this.my = 55 + (50 * (myy - 1));
                    System.out.println("se coloco manzana en: x = " + (((px - 55) / 50) + 1)  + " y = " + (((py - 55) / 50) + 1));
                    this.comando = "manzana";
                    break;

                case "ave":
                    pxx = parseInt(datos[1]);
                    pyy = parseInt(datos[2]);
                    this.px = 55 + (50 * (pxx - 1));
                    this.py = 55 + (50 * (pyy - 1));
                    System.out.println("se coloco ave en: x = " + (((px - 55) / 50) + 1)  + " y = " + (((py - 55) / 50) + 1));
                    this.comando = "ave";
                    break;

                case "mover":
                    moverCuadros = parseInt(datos[1]);
                    if (arriba) {
                        yy = (y - (alto + 10) * moverCuadros);
                    }
                    if (abajo) {
                        yy = (y + (alto + 10) * moverCuadros);
                    }
                    if (izquierda) {
                        xx = (x - (ancho + 10) * moverCuadros);
                    }
                    if (derecha) {
                        xx = (x + (ancho + 10) * moverCuadros);
                    }
                    this.comando = "mover";
                    break;

                default:
                    break;
            }

        } else {

            this.avecapt();

            System.out.println("termino comandos");
            graficos.clearRect(0,0,600,600);
            this.iniciar = false;
            this.indice = 1;
            this.comandos.clear();
            this.comandos = (ArrayList<String>) this.tempcom.clone();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Interprete");
            alert.setHeaderText("Se alcanzo el ultimo comando");
            alert.setContentText("No has encontrado la Manzana");
            alert.show();
            Sonidos mensaje = new Sonidos("mensaje");
            hiloFondo = new Thread(mensaje);
            hiloFondo.start();
        }

    }

    private void abrirArchivo() throws IOException, ClassNotFoundException {
        FileChooser archivos = new FileChooser();
        archivos.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos compilador", "*.fes"));
        archivos.setTitle("Abrir archivo de Compilador");
        archivos.setInitialDirectory(new File(System.getProperty("user.dir")));
        File ruta = archivos.showOpenDialog(this.ventana);
        if (ruta != null) {
            FileReader fr = new FileReader(ruta);
            BufferedReader buff = new BufferedReader(fr);
            String cad;
            this.tempcom.clear();
            tempcom.add("derecha");
            this.iniciar();
            while ((cad = buff.readLine()) != null) {
                tempcom.add(cad);
                tempcom.removeAll(Collections.singleton(""));
            }
            this.comandos = (ArrayList<String>) this.tempcom.clone();
            buff.close();
            fr.close();
        }

    }

    private void iniciar() {
        x = 55;
        y = 55;
        xx = 0;
        yy = 0;
        indice = 0;
        this.imagen = this.derechaImg;
        this.imagen2 = this.manzana;
        this.imagen3 = this.ave;
        moverCuadros = 0;
        comando = "";
        arriba = false;
        abajo = false;
        derecha = false;
        izquierda = false;
    }

}

