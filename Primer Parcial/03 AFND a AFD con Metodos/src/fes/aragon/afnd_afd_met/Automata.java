package fes.aragon.afnd_afd_met;


import fes.aragon.herramientas.Gestor;

import  java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Automata{
    private static String archivo = "fuente.txt";
    private int indice=0;
    private String cadena="";
    private final int error=-1;
    private final int aceptado=1;

    public static void main(String[] args) {
        Automata app=new Automata();

        for (String palabra : Gestor.leer(archivo)) {
            app.cadena = palabra;
            int valor = app.estado_A();
            if (valor == app.aceptado) {
                System.out.println(palabra + ": Cadena Valida");
                app.reinicioIndice();
            } else {
                System.out.println(palabra + ": Cadena Invalida");
                app.reinicioIndice();
            }
        }
    }

    private void reinicioIndice() {
        indice = 0;
    }
    private char siguienteCaracter() {
        char caracter = ' ';
        if (indice < cadena.length()) {
            caracter = cadena.charAt(indice);
            indice++;
        }
        return caracter;
    }

    private int estado_A() {
        char c=siguienteCaracter();
        switch(c){
            case '0': return estado_B();
            case '1': return estado_C();
            default: return error;
        }
    }

    private int estado_B() {
        char c=siguienteCaracter();
        switch(c){
            case '0': return estado_B();
            case '1': return estado_C();
            default: return error;
        }
    }

    private int estado_C() {
        char c=siguienteCaracter();
        switch(c){
            case '0': return estado_B();
            case '1': return estado_D();
            default: return error;
        }
    }

    private int estado_D() {
        char c=siguienteCaracter();
        switch(c){
            case '0': return estado_E();
            case '1': return estado_D();
            case ' ': return aceptado;
            default: return error;
        }
    }

    private int estado_E() {
        char c=siguienteCaracter();
        switch(c){
            case '0': return estado_E();
            case '1': return estado_C();
            case ' ': return aceptado;
            default: return error;
        }
    }


}