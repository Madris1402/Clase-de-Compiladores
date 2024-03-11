package fes.aragon.herramientas;

public class Lexico {
	private int indice = 0;
	private int error = -1;

	// Variables del AFD
	private int estado = 0;
	private int columna = 0;

	private String token = "";

	public Lexico() {
		super();
	}

	private void reinicio() {
		indice = 0;
		estado = 0;
		columna = 0;

	}

	public void setToken(String token) {
		this.token = token;
	}

	private char siguienteCaracter() throws Exception {
		char caracter = ' ';
		if (indice < token.length()) {
			caracter = token.charAt(indice);
			if (caracter == ' ' && indice < token.length()) {
				int columna = indice + 1;
				reinicio();
				throw new Exception(
						token + ": Cadena Invalida ---> Espacio entre palabras encontrado en la columna: " + columna);
			}
			indice++;
		}
		return caracter;
	}

	public int inicio(int[][] matriz, Character[] lenguaje) throws Exception {
		char c = ' ';
		reinicio();
		do {
			boolean caracterEncontrado = false;
			boolean caracterUsuarioEncontrado = false;
			c = siguienteCaracter();
			if (Herramienta.numero(c) || Herramienta.letra(c)) {
				for (int j = 0; j < lenguaje.length; j++) {
					if (c == lenguaje[j]) { // Lenguaje usuario
						columna = j;
						caracterEncontrado = true;
						caracterUsuarioEncontrado = true;
					}
				}
				if (!caracterUsuarioEncontrado) {
					Character numero = 'D';
					for (int j = 0; j < lenguaje.length; j++) {
						if (numero == lenguaje[j] && !caracterUsuarioEncontrado) {
							columna = j;
							caracterEncontrado = true;
						}
					}
					Character letra = 'L';
					for (int j = 0; j < lenguaje.length; j++) {
						if (letra == lenguaje[j] && !caracterUsuarioEncontrado) {
							columna = j;
							caracterEncontrado = true;
						}
					}
				}

			} else if (!Herramienta.numero(c) && !Herramienta.letra(c) && !Herramienta.finCadena(c)) {
				for (int j = 0; j < lenguaje.length; j++) {
					if (c == lenguaje[j]) { // Lenguaje usuario
						columna = j;
						caracterEncontrado = true;
					}
				}
			} else if (Herramienta.finCadena(c)) {

				columna = matriz[0].length - 1;
				caracterEncontrado = true;
			}
			if (caracterEncontrado) {
				estado = matriz[estado][columna];
			} else {
				throw new Exception(token + ": Caracter invalido: " + c + " en la columna: " + (indice));
			}

		} while (!Herramienta.finCadena(c));
		return estado;
	}

}