package com.fran.ficheros.utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Ficheros {
	
	
	/**
	 * Crea un fichero en caso de no existir. Si existe solamente informa de su
	 * existencia pero no lo reemplaza. Si no existe la carpeta que contiene el
	 * fichero también la crea.
	 * 
	 * @param directorio    Directorio donde queremos crear el fichero
	 * @param nombreFichero Nombre del fichero con la extensión incluida
	 * @throws IOException
	 * @author Fran
	 */
	public static boolean crearFichero(String directorio, String nombreFichero) throws IOException {
		File ruta = new File(directorio);
		File f = new File(ruta, nombreFichero);
		if (!f.exists()) { // El fichero no existe
			if (!ruta.exists()) { // La carpeta no existe
				if (ruta.mkdir()) { // La carpeta la he podido crear
					if (f.createNewFile()) { // El fichero se ha creado
						System.out.println("Fichero " + f.getName() + " creado");
						return true;
					} else {
						System.out.println("No se ha podido crear el fichero " + f.getName());
						return false;
					}
				} else { // La carpeta no se ha podido crear
					System.out.println("No he podido crear la carpeta " + ruta.getName());
					return false;
				}
			} else { // La carpeta si existe
				if (f.createNewFile()) { // El fichero se ha creado
					System.out.println("Fichero " + f.getName() + " creado");
					return true;
				} else {
					System.out.println("No se ha podido crear el fichero " + f.getName());
					return false;
				}
			}
		} else { // El fichero existe
			System.out.println("Fichero " + f.getName() + " ya existe");
			System.out.println("Tamaño " + f.length() + " bytes");
			return false;
		}
	}

	/**
	 * Crea un fichero en caso de no existir. Si existe solamente informa de su
	 * existencia pero no lo reemplaza. Si no existe la carpeta que contiene el
	 * fichero también la crea.
	 * 
	 * @param rutaCompleta Será la ruta completa incluido el nombre del fichero con
	 *                     su extensión (ex. "c:/ficheros/prueba.txt")
	 * @throws IOException
	 * @author Fran
	 */
	public static void crearFichero(String rutaCompleta) throws IOException {
		File fichero = new File(rutaCompleta);
		crearFichero(fichero.getParent(), fichero.getName());
	}

	public static void eliminarFichero(String directorio, String nombreFichero) {
		File ruta = new File(directorio);
		File f = new File(ruta, nombreFichero);
		if (f.exists()) { // borramos el fichero
			if (f.isFile()) { // Nos aseguramos que solo borra ficheros y no carpetas
				if (f.delete()) { // Ha borrado el fichero
					System.out.println("Fichero eliminado");
				} else {
					System.out.println("No he podido eliminar el fichero " + f.getName());
				}
			} else {
				System.out.println("El nombre " + f.getName() + " no es un fichero simple");
			}
		} else { // El fichero no existe, no borramos nada
			System.out.println("El fichero " + f.getName() + " no existe.");
		}
	}

	public static void eliminarFichero(String rutaCompleta) {
		File fichero = new File(rutaCompleta);
		eliminarFichero(fichero.getParent(), fichero.getName());
	}

	public static void renombrarFichero(String directorio, String nombreFichero, String nuevoNombre) {
		File ruta = new File(directorio);
		File f = new File(ruta, nombreFichero);
		if(f.exists()) {
			if (f.isFile()) {
				if (f.renameTo(new File(ruta, nuevoNombre))) {
					System.out.println("Se ha cambiado el nombre correctamente");
				} else {
					System.out.println("No he podido cambiar el nombre");
				} 
			}else {
				System.out.println("El nombre " + f.getName() + " no es un fichero simple");
			}
		} else {
			System.out.println("El fichero " + f.getName() + " no existe");
		}
	}
	
	public static void leerDirectorio(String directorio) {
		File ruta = new File(directorio);
		if(ruta.exists()) {
			String[] lista = ruta.list();
			//  ahora recorreré el array
			for (int i = 0; i < lista.length; i++) {
				System.out.println(lista[i]);
			}
			
		} else {
			System.out.println("El directorio " + directorio + " no existe");
		}
	}
	
	public static String[] obtenerDatosDirectorio(String directorio) {
		File ruta = new File(directorio);
		if(ruta.exists()) {
			return ruta.list();			
		} else {
			return null;
		}
	}

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
	 * @param charSet Conjunto de caracteres
	 * @return Lista de líneas
	 */
	public static List<String> leerFichero8(String directorio, String nombreFichero, Charset caracteres){
		try {
			return Files.readAllLines(Paths.get(directorio + "/" + nombreFichero),caracteres);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Leer un Fichero en Java 8
	 * Dado un directorio y un nombre de fichero devolverá sus líneas
	 * @param directorio ruta del fichero
	 * @param nombreFichero Nombre del fichero
	 * @return Lista de líneas
	 */
	public static List<String> leerFichero8(String directorio, String nombreFichero){
		return leerFichero8(directorio,nombreFichero,Charset.defaultCharset());
	}
	
	/**
	 * Leer un Fichero en Java 8
	 * Dada una ruta completa devolverá sus líneas
	 * @param ruta ruta del fichero
	 * @return Lista de líneas
	 */
	public static List<String> leerFichero8(String rutaCompleta){
		File fichero = new File(rutaCompleta);
		return leerFichero8(fichero.getParent(),fichero.getName());
	}
	
	/**
	 * Leer un Fichero en Java 11
	 * Dada una ruta completa devolverá sus líneas como un único String usando el Charset por defecto
	 * @param ruta ruta del fichero
	 * @return String del fichero completo
	 */
	public static String leerFichero11(String rutaCompleta){
		try {
			return Files.readString(Paths.get(rutaCompleta),StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static boolean anyadirFichero(String directorio, String nombreFichero, List<String> lineas) {
		try {
			Files.write(Paths.get(directorio + "/" + nombreFichero), lineas, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	public static void escribirFichero(String rutaCompleta,List<String> lineas){
		try {
			Files.write(Paths.get(rutaCompleta), lineas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
