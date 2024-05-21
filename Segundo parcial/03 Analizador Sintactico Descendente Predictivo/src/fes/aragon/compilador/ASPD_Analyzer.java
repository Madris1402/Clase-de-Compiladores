package fes.aragon.compilador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Stack;

public class ASPD_Analyzer {
    private final String[][] canonicalTable = {
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

    private int row,column;
    private int escapeCounter = 0;
    private String symbol;
    private boolean isValid = false;
    private boolean nextToken = true;
    private Stack<Integer> numberStack = new Stack<>();
    private Stack<String> symbolStack = new Stack<>();
    public Tokens tokens;

    public ASPD_Analyzer () {
        this.row = 0;
        this.numberStack.push(0);
    }

    public String getPath() throws URISyntaxException {
        return this.getClass().getResource("/fes/aragon/compilador/fuente.txt").toURI().getPath();
    }

    public void StartAnalysis () throws URISyntaxException, IOException {
        Lexical_Analyzer lex = new Lexical_Analyzer(new FileInputStream(this.getPath()));
        String result = "";
        while (true) {
            if (nextToken) {
                tokens = lex.yylex();
                if(tokens == null) {
                	return;
                }
            }
            if (!nextToken){
                escapeCounter++;
                if (escapeCounter == 200){ //Cambiar a 200
                    numberStack.clear();
                }
            }
            if (escapeCounter == 200){//Cambiar a 200
                numberStack.push(1);
            }
            nextToken = false;
            if (isSyntaxValid()) {
                System.out.println("La sintaxis es correcta\n");
                reinicio();
                continue;
                //return;
            }
            setRow();
            setSymbol(tokens);
            setColumn();
            String action = GetAction();
            ExecuteAction(action);
            if (symbolStack.isEmpty()){
                System.out.println("La sintaxis es invalida\n");
                reinicio();
                continue;
                //return;
            }
        }
    }
    
    private void reinicio() {
        numberStack.clear();
        symbolStack.clear();
        numberStack.push(0);
        escapeCounter = 0;
        column = 0;
        nextToken = true;
        isValid = false;
    }

    private void setRow () {
        if (symbolStack.isEmpty()){
            row = 0;
            return;
        }
        row = numberStack.peek();
    }

    private String setSymbol (Tokens token) {
    	if(isValid) {
    		symbol = "id";
    		isValid = false;
    	}else {
    		switch (token){
            case ID -> {
                symbol = "id";
            }
            case Plus -> {
                symbol = "+";
            }
            case Multiply -> {
                symbol = "*";
            }
            case ParA -> {
                symbol = "(";
            }
            case ParC -> {
                symbol = ")";
            }
            case Semi -> {
                symbol = ";";
            }
          }
    	}
        return symbol;
    }

    private void setColumn () {
        switch (symbol){
            case "id" -> column = 0;
            case "+" -> column = 1;
            case "*" -> column = 2;
            case "(" -> column = 3;
            case ")" -> column = 4;
            case ";" -> column = 5;
        }
    }

    private String GetAction () {
        String nextAction = canonicalTable[row][column];
        if (Objects.equals(nextAction, "ERROR")){
            String goTo = symbolStack.peek();
            switch (goTo){
                case "E" -> nextAction = canonicalTable[row][6];
                case "T" -> nextAction = canonicalTable[row][7];
                case "F" -> nextAction = canonicalTable[row][8];
            }
        }
        return nextAction;
    }

    private void ExecuteAction (String action){
        String ruleAction;
        switch (action){
            case "D(5)" -> {
                numberStack.push(5);
                symbolStack.push("id");
                nextToken = true;
            }
            case "D(6)" -> {
                numberStack.push(6);
                symbolStack.push("+");
                nextToken = true;
            }
            case "D(7)" -> {
                numberStack.push(7);
                symbolStack.push("*");
                nextToken = true;
            }
            case "D(4)" -> {
                numberStack.push(4);
                symbolStack.push("(");
                nextToken = true;
            }
            case "D(11)" -> {
                numberStack.push(11);
                symbolStack.push(")");
                nextToken = true;
            }
            case "R(1)" -> {
                for (int i = 0; i < 3; i++){
                    symbolStack.pop();
                }
                symbolStack.push("E");
                numberStack.pop();
                column = 6;
                setRow();
                ruleAction = GetAction();
                ExecuteAction(ruleAction);
            }
            case "R(2)" -> {
                symbolStack.pop();
                symbolStack.push("E");
                numberStack.pop();
                column = 6;
                setRow();
                ruleAction = GetAction();
                ExecuteAction(ruleAction);
            }
            case "R(3)" -> {
                for (int i = 0; i < 3; i++){
                    symbolStack.pop();
                }
                symbolStack.push("T");
                numberStack.pop();
                column = 7;
                setRow();
                ruleAction = GetAction();
                ExecuteAction(ruleAction);
            }
            case "R(4)" -> {
                symbolStack.pop();
                symbolStack.push("T");
                numberStack.pop();
                column = 7;
                setRow();
                ruleAction = GetAction();
                ExecuteAction(ruleAction);
            }
            case "R(5)" -> {
                for (int i = 0; i < 3; i++) {
                    symbolStack.pop();
                }
                symbolStack.push("F");
                numberStack.pop();
                column = 8;
                setRow();
                ruleAction = GetAction();
                ExecuteAction(ruleAction);
            }
            case "R(6)" -> {
                symbolStack.pop();
                symbolStack.push("F");
                numberStack.pop();
                column = 8;
                setRow();
                ruleAction = GetAction();
                ExecuteAction(ruleAction);
            }
            case "Ir(1)" -> {
                numberStack.push(1);
            }
            case "Ir(2)" -> {
                numberStack.push(2);
            }
            case "Ir(3)" -> {
                numberStack.push(3);
            }
            case "Ir(8)" -> {
                numberStack.push(8);
            }
            case "Ir(9)" -> {
                numberStack.push(9);
            }
            case "Ir(10)" -> {
                numberStack.push(10);
            }
            case "ERROR" -> {
                numberStack.pop();
            }
            case "ACEPTAR" -> {
                isValid = true;
            }
        }
    }

    private boolean isSyntaxValid() {
        return isValid;
    }


}
