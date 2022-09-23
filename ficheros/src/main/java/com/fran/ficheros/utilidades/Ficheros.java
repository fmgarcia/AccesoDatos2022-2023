package com.fran.ficheros.utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Ficheros {

	/**
	 * Leer un Fichero en Java 5
	 * Dado un nombre de fichero devolverá sus líneas hasta un máximo
	 * de 65536
	 * @param nombre Nombre del fichero en la carpeta del proyecto
	 * @return Array de líneas del fichero
	 */
	public static String[] leerFichero5(String nombre) {
		return leerFichero5(nombre,65536);
	}
	/**
	 * Leer un Fichero en Java 5
	 * Dado un nombre de fichero devolverá sus líneas
	 * @param nombre Nombre del fichero en la carpeta del proyecto
	 * @param maxLineas Número de líneas a leer
	 * @return Array de líneas del fichero
	 */
	public static String[] leerFichero5(String nombre, int maxLineas) {
		String[] lineas = new String[maxLineas];
		int cont = 0;
		// Primero vemos si el fichero existe
		if (!(new File(nombre)).exists()) {
			System.out.println("No he encontrado ejemplo.txt");
			return null;
		}
		// En caso de que exista, intentamos leer
		System.out.println("Leyendo fichero...");
		try {
			BufferedReader ficheroEntrada = new BufferedReader(new FileReader(new File("ejemplo.txt")));
			String linea = ficheroEntrada.readLine();
			while (linea != null && cont<maxLineas) {
				lineas[cont] = linea;
				cont++;
				linea = ficheroEntrada.readLine();
			}
			ficheroEntrada.close();
			return lineas;
		} catch (IOException errorDeFichero) {
			System.out.println("Ha habido problemas: " + errorDeFichero.getMessage());
		}
		System.out.println("Fin de la lectura.");
		return lineas;
	}
	
	/**
	 * Leer un Fichero en Java 8
	 * Dado un directorio y un nombre de fichero devolverá sus líneas
	 * @param directorio ruta del fichero
	 * @param nombreFichero Nombre del fichero
	 * @return Lista de líneas
	 */
	public static List<String> leerFichero8(String directorio, String nombreFichero){
		try {
			return Files.readAllLines(Paths.get(directorio + "/" + nombreFichero));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> leerFichero8(String nombreFichero){
		return leerFichero8(".",nombreFichero);
	}
	
}
