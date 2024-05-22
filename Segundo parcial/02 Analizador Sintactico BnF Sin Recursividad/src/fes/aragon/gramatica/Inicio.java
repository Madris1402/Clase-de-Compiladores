package fes.aragon.gramatica;

import java.io.*;
import java.util.Stack;

import fes.aragon.exception.wordException;
import fes.aragon.herramientas.refactorizer;


public class Inicio {
	private Lexico lexico;
	private Tokens token;
	Stack<String> errores = new Stack<String>();
	Stack<String> simbolos = new Stack<String>();
	private static boolean acceptada = true;
	

	public static void main(String[] args) throws wordException {
		Inicio app = new Inicio();
		refactorizer refact = new refactorizer();
		try {
			refact.refactorizer();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Reader rd = new BufferedReader(new FileReader(
					System.getProperty("user.dir") + File.separator + "fuente.ref"));
			app.lexico = new Lexico(rd);
			app.token = app.lexico.yylex();
			while(!(app.token == null)) {
				app.S();
				if(acceptada) {
					System.out.println("Palabra acceptada\n");
				}else {
					System.out.println("Palabra no acceptada\n");
					acceptada = true;
				}
				
				
				app.token = app.lexico.yylex();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void S() throws IOException, wordException {
		simbolos.push("S");
		E();
		if (token != Tokens.PUNTOYCOMA) {
			errores.push("Error, se esperaba ;");
			while(!errores.isEmpty()) {
				System.out.println(errores.pop());
			}
			throw new wordException("Palabra no acceptada");
		}
		simbolos.pop();
		return;
	}

	private void E() throws IOException {
		simbolos.push("E");
		T();
		Ep();
		simbolos.pop();
		return;
	}

	private void Ep() throws IOException {
		simbolos.push("Ep");
		if (token == Tokens.OR) {
			token = lexico.yylex();
			T();
			Ep();
		} else if (token != Tokens.AND) {
			errores.push("Error, se esperaba OR");

		} else {
			simbolos.pop();
			return;
		}
	}

	private void T() throws IOException {
		simbolos.push("T");
		try {
			F();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (wordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tp();
	}

	private void Tp() throws IOException {
		simbolos.push("Tp");
		if (token == Tokens.AND) {
			token = lexico.yylex();
			try {
				F();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (wordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Tp();
		} else if (token != Tokens.OR) {
			errores.push("Error, se esperaba AND");
		} else {
			simbolos.pop();
			return;
		}
	}

	private void F() throws IOException, wordException {
		simbolos.push("F");
		if (token == Tokens.NOT) {
			token = lexico.yylex();
			F();
		} else if (token == Tokens.TRUE || token == Tokens.FALSE) {
			token = lexico.yylex();
			simbolos.pop();
			return;
		} else if (token == Tokens.ABREPARENTESIS) {
			token = lexico.yylex();
			E();
			if (token == Tokens.CIERREPARENTESIS) {
				token = lexico.yylex();
				simbolos.pop();
				return;
			}
		} else {
			errores.push("Error, caracter no esperado");
			while(!errores.isEmpty()) {
				System.out.println(errores.pop());
			}
			acceptada = false;
			throw new wordException("Palabra no acceptada");
		}
	}

}