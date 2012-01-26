package grammarp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class GrammarParser {

	// public static String filename = "error2";
	public static String filename = "gramatica.txt";

	// public static String filename = "gramaticaPrueba.txt";

	public Grammar parse(String fileName) throws IOException, ParseException {
		Grammar grammar = new Grammar();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		int cant = 0;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(" ");
			if (tokens.length > 2 || tokens.length < 1) {
				throw new ParseException("Esta mal la gramatica", cant);
			} else if (tokens.length == 1) {// lambda production
				grammar.addProduction(tokens[0], "");
			} else {
				grammar.addProduction(tokens[0], tokens[1]);
			}
		}
		return grammar;
	}

	public static void main(String[] args) {
		GrammarParser grammarParser = new GrammarParser();
		Grammar gramatica = null;
		if (args.length == 0) {
			System.out.println("Se va a utilizar la gramatica que esta en: " + filename);
			try {
				gramatica = grammarParser.parse(filename);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Se va a utilizar la gramatica que esta en: " + args[0]);
			try {
				gramatica = grammarParser.parse(args[0]);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		gramatica.calcularPrimeros();
		gramatica.calcularSiguientes();
		gramatica.calcularSimbolosDirectrices();

		System.out.println("NT: " + gramatica.noTerminalSymbols);
		System.out.println("T: " + gramatica.terminalSimbols);
		/*
		 * for(Production p: gramatica.producctions){
		 * System.out.println(p.noterminal + "->" + p.rightpart); }
		 */
		System.out.println("Primeros " + gramatica.primeros);
		System.out.println("Siguientes " + gramatica.siguientes);
		System.out.println("Directrices: " + gramatica.simbolosDirectrices);

		TablaAnalizador tablaAnalizador = new TablaAnalizador(gramatica,
				gramatica.simbolosDirectrices);
		tablaAnalizador.printTable();

		validarPalabras(gramatica, tablaAnalizador);
	}

	private static void validarPalabras(Grammar gramatica,
			TablaAnalizador tablaAnalizador) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			String str = "";
			while (str != null) {
				System.out.print("Ingrese una palabra: ");
				str = in.readLine();
				boolean pertenece = gramatica.validarPalabra(str,
						tablaAnalizador);
				System.out.println("La palabra " + (pertenece ? "" : "NO ")
						+ "pertenece al lenguaje.");
			}
		} catch (IOException e) {

		}
	}
}
