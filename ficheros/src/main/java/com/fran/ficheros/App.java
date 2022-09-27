package com.fran.ficheros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fran.ficheros.utilidades.Ficheros;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	List<String> lineas = new ArrayList<String>();
    	lineas.add("primera línea");
    	lineas.add("segunda línea");
    	
    	/*Ficheros.leerFichero8("c:/ficheros", "datos.txt")
        .forEach(l->System.out.println(l));*/
    	//Ficheros.leerFichero8("prueba.txt").forEach(l->System.out.println(l));
    	//Ficheros.leerFichero8("c:/ficheros/datos.txt").forEach(l->System.out.println(l));
    	//Ficheros.anyadirFichero("c:/ficheros", "pruebaTexto.txt", lineas);
    	//Ficheros.escribirFichero("c:/ficheros/pruebaEscritura.txt", lineas);

		//System.out.println(Ficheros.crearFichero("c:/ficheroooos", "dam2223.txt")?"Todo correcto":"Fallo");
		//System.out.println(Ficheros.eliminarFichero("c:/ficheros/kk.txt")?"Todo correcto":"Fallo");
    	//System.out.println(Ficheros.renombrarFichero("c:/ficheros","kk.txt","kk2.txt")?"Todo correcto":"Fallo");
    	//Ficheros.leerDirectorio("c:/ficheros");
    	// Coge todos los ficheros de un directorio que contengan txt y muestra su contenido por consola
    	//Ficheros.obtenerDatosDirectorio("c:/ficheros").stream()
    	//	.filter(e->e.contains("txt"))
    	//	.forEach(e->System.out.println(e + "\n" + Ficheros.leerFichero11("c:/ficheros/" + e)));
    	

    }
}
