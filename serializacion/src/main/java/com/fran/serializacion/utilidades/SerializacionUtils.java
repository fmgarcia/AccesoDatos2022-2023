package com.fran.serializacion.utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.fran.serializacion.entidades.Persona;

public class SerializacionUtils {
	
	public static boolean serializarPersona(String directorio, String nombreArchivo, Persona p) {
		
		File fichero = new File(directorio + "/" + nombreArchivo);
		try {
			FileOutputStream ficheroSalida = new FileOutputStream(fichero);
			ObjectOutputStream ficheroObjetos = new ObjectOutputStream(ficheroSalida);
			ficheroObjetos.writeObject(p);  // Serializa
			ficheroObjetos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static <T> boolean serializarObjeto(String directorio, String nombreArchivo, T p) {
		
		File fichero = new File(directorio + "/" + nombreArchivo);
		try {
			FileOutputStream ficheroSalida = new FileOutputStream(fichero);
			ObjectOutputStream ficheroObjetos = new ObjectOutputStream(ficheroSalida);
			ficheroObjetos.writeObject(p);  // Serializa
			ficheroObjetos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean serializarListaPersonas(String directorio, String nombreArchivo, List<Persona> personas) {
		
		File fichero = new File(directorio + "/" + nombreArchivo);
		try {
			ObjectOutputStream ficheroObjetos = new ObjectOutputStream(new FileOutputStream(fichero));
			ficheroObjetos.writeObject(personas);  // Serializa
			ficheroObjetos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static <T> boolean serializarListaObjetos(String directorio, String nombreArchivo, List<T> objetos) {
		
		File fichero = new File(directorio + "/" + nombreArchivo);
		try {
			ObjectOutputStream ficheroObjetos = new ObjectOutputStream(new FileOutputStream(fichero));
			ficheroObjetos.writeObject(objetos);  // Serializa
			ficheroObjetos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Persona desSerializarPersona(String directorio, String nombreArchivo) {
		
		File fichero = new File(directorio + "/" + nombreArchivo);
		try {
			FileInputStream ficheroSalida = new FileInputStream(fichero);
			ObjectInputStream ficheroObjetos = new ObjectInputStream(ficheroSalida);
			Persona p = (Persona) ficheroObjetos.readObject();  // DesSerializa
			ficheroObjetos.close();
			return p;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static <T> T desSerializarObjeto(String directorio, String nombreArchivo) {
		
		File fichero = new File(directorio + "/" + nombreArchivo);
		try {
			FileInputStream ficheroSalida = new FileInputStream(fichero);
			ObjectInputStream ficheroObjetos = new ObjectInputStream(ficheroSalida);
			T objeto = (T) ficheroObjetos.readObject();  // DesSerializa
			ficheroObjetos.close();
			return objeto;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static List<Persona> desSerializarListaPersonas(String directorio, String nombreArchivo) {
		
		File fichero = new File(directorio + "/" + nombreArchivo);
		try {
			FileInputStream ficheroSalida = new FileInputStream(fichero);
			ObjectInputStream ficheroObjetos = new ObjectInputStream(ficheroSalida);
			List<Persona> p = (List<Persona>) ficheroObjetos.readObject();  // DesSerializa
			ficheroObjetos.close();
			return p;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public static <T> List<T> desSerializarListaObjetos(String directorio, String nombreArchivo) {
	
	File fichero = new File(directorio + "/" + nombreArchivo);
	try {
		FileInputStream ficheroSalida = new FileInputStream(fichero);
		ObjectInputStream ficheroObjetos = new ObjectInputStream(ficheroSalida);
		List<T> objetos = (List<T>) ficheroObjetos.readObject();  // DesSerializa
		ficheroObjetos.close();
		return objetos;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} 
	return null;
}


}
