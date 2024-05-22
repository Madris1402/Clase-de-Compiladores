package fes.aragon.compilador;

import fes.aragon.herramientas.refactorizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Stack;

public class Analizador_Sintactico_DP {
    private final String[][] tabla = {
            {"D(5)","ERROR","ERROR","D(4)","ERROR","ERROR","Ir(1)","Ir(2)","Ir(3)"},
            {"ERROR","D(6)","ERROR","ERROR","ERROR","ACEPTAR","ERROR","ERROR","ERROR"},
            {"ERROR","R(2)","D(7)","ERROR","R(2)","R(2)","ERROR","ERROR","ERROR"},
            {"ERROR","R(4)","R(4)","ERROR","R(4)","R(4)","ERROR","ERROR","ERROR"},
            {"D(5)","ERROR","ERROR","D(4)","ERROR","ERROR","Ir(8)","Ir(2)","Ir(3)"},
            {"ERROR","R(6)","R(6)","ERROR","R(6)","R(6)","ERROR","ERROR","ERROR"},
            {"D(5)","ERROR","ERROR","D(4)","ERROR","ERROR","ERROR","Ir(9)","Ir(3)"},
            {"D(5)","ERROR","ERROR","D(4)","ERROR","ERROR","ERROR","ERROR","Ir(10)"},
            {"ERROR","D(6)","ERROR","ERROR","D(11)","ERROR","ERROR","ERROR","ERROR","ERROR"},
            {"ERROR","R(1)","D(7)","ERROR","R(1)","R(1)","ERROR","ERROR","ERROR"},
            {"ERROR","R(3)","R(3)","ERROR","R(3)","R(3)","ERROR","ERROR","ERROR"},
            {"ERROR","R(4)","R(5)","ERROR","R(5)","R(5)","ERROR","ERROR","ERROR"}
    };

    private int fila, columna;
    private int Contador = 0;
    private String symbol;
    private boolean Valido = false;
    private boolean SigToken = true;
    private Stack<Integer> PilaNumeros = new Stack<>();
    private Stack<String> PilaSimbolos = new Stack<>();
    public Tokens tokens;

    public Analizador_Sintactico_DP() {
        this.fila = 0;
        this.PilaNumeros.push(0);
    }

    public String ruta() throws URISyntaxException {
        return System.getProperty("user.dir") + File.separator + "src" + File.separator + "fes" + File.separator + "aragon" + File.separator + "compilador" + File.separator +  "fuente.ref";
    }

    public void Inicio() throws URISyntaxException, IOException {

        refactorizer refac = new refactorizer();
        refac.refactorizer();

        Lexical_Analyzer lex = new Lexical_Analyzer(new FileInputStream(this.ruta()));
        String result = "";
        while (true) {
            if (SigToken) {
                tokens = lex.yylex();
                if(tokens == null) {
                	return;
                }
            }
            if (!SigToken){
                Contador++;
                if (Contador == 200){ //Cambiar a 200
                    PilaNumeros.clear();
                }
            }
            if (Contador == 200){//Cambiar a 200
                PilaNumeros.push(1);
            }
            SigToken = false;
            if (isSyntaxValid()) {
                System.out.println("La sintaxis es correcta\n");
                reinicio();
                continue;
                //return;
            }
            setFila();
            setSimbolo(tokens);
            setColumna();
            String action = Accion();
            Ejecutar(action);
            if (PilaSimbolos.isEmpty()){
                System.out.println("La sintaxis es invalida\n");
                reinicio();
                continue;
                //return;
            }
        }
    }
    
    private void reinicio() {
        PilaNumeros.clear();
        PilaSimbolos.clear();
        PilaNumeros.push(0);
        Contador = 0;
        columna = 0;
        SigToken = true;
        Valido = false;
    }

    private void setFila() {
        if (PilaSimbolos.isEmpty()){
            fila = 0;
            return;
        }
        fila = PilaNumeros.peek();
    }

    private String setSimbolo(Tokens token) {
    	if(Valido) {
    		symbol = "id";
    		Valido = false;
    	}else {
    		switch (token){
            case ID -> {
                symbol = "id";
            }
            case Mas -> {
                symbol = "+";
            }
            case Por -> {
                symbol = "*";
            }
            case ParA -> {
                symbol = "(";
            }
            case ParC -> {
                symbol = ")";
            }
            case Punto_coma -> {
                symbol = ";";
            }
          }
    	}
        return symbol;
    }

    private void setColumna() {
        switch (symbol){
            case "id" -> columna = 0;
            case "+" -> columna = 1;
            case "*" -> columna = 2;
            case "(" -> columna = 3;
            case ")" -> columna = 4;
            case ";" -> columna = 5;
        }
    }

    private String Accion() {
        String nextAction = tabla[fila][columna];
        if (Objects.equals(nextAction, "ERROR")){
            String goTo = PilaSimbolos.peek();
            switch (goTo){
                case "E" -> nextAction = tabla[fila][6];
                case "T" -> nextAction = tabla[fila][7];
                case "F" -> nextAction = tabla[fila][8];
            }
        }
        return nextAction;
    }

    private void Ejecutar(String action){
        String AccionRegla;
        switch (action){
            case "D(5)" -> {
                PilaNumeros.push(5);
                PilaSimbolos.push("id");
                SigToken = true;
            }
            case "D(6)" -> {
                PilaNumeros.push(6);
                PilaSimbolos.push("+");
                SigToken = true;
            }
            case "D(7)" -> {
                PilaNumeros.push(7);
                PilaSimbolos.push("*");
                SigToken = true;
            }
            case "D(4)" -> {
                PilaNumeros.push(4);
                PilaSimbolos.push("(");
                SigToken = true;
            }
            case "D(11)" -> {
                PilaNumeros.push(11);
                PilaSimbolos.push(")");
                SigToken = true;
            }
            case "R(1)" -> {
                for (int i = 0; i < 3; i++){
                    PilaSimbolos.pop();
                }
                PilaSimbolos.push("E");
                PilaNumeros.pop();
                columna = 6;
                setFila();
                AccionRegla = Accion();
                Ejecutar(AccionRegla);
            }
            case "R(2)" -> {
                PilaSimbolos.pop();
                PilaSimbolos.push("E");
                PilaNumeros.pop();
                columna = 6;
                setFila();
                AccionRegla = Accion();
                Ejecutar(AccionRegla);
            }
            case "R(3)" -> {
                for (int i = 0; i < 3; i++){
                    PilaSimbolos.pop();
                }
                PilaSimbolos.push("T");
                PilaNumeros.pop();
                columna = 7;
                setFila();
                AccionRegla = Accion();
                Ejecutar(AccionRegla);
            }
            case "R(4)" -> {
                PilaSimbolos.pop();
                PilaSimbolos.push("T");
                PilaNumeros.pop();
                columna = 7;
                setFila();
                AccionRegla = Accion();
                Ejecutar(AccionRegla);
            }
            case "R(5)" -> {
                for (int i = 0; i < 3; i++) {
                    PilaSimbolos.pop();
                }
                PilaSimbolos.push("F");
                PilaNumeros.pop();
                columna = 8;
                setFila();
                AccionRegla = Accion();
                Ejecutar(AccionRegla);
            }
            case "R(6)" -> {
                PilaSimbolos.pop();
                PilaSimbolos.push("F");
                PilaNumeros.pop();
                columna = 8;
                setFila();
                AccionRegla = Accion();
                Ejecutar(AccionRegla);
            }
            case "Ir(1)" -> {
                PilaNumeros.push(1);
            }
            case "Ir(2)" -> {
                PilaNumeros.push(2);
            }
            case "Ir(3)" -> {
                PilaNumeros.push(3);
            }
            case "Ir(8)" -> {
                PilaNumeros.push(8);
            }
            case "Ir(9)" -> {
                PilaNumeros.push(9);
            }
            case "Ir(10)" -> {
                PilaNumeros.push(10);
            }
            case "ERROR" -> {
                PilaNumeros.pop();
            }
            case "ACEPTAR" -> {
                Valido = true;
            }
        }
    }

    private boolean isSyntaxValid() {
        return Valido;
    }


}
