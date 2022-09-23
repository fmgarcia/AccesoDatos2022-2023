package com.fran.ficheros;

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
    	Ficheros.escribirFichero("c:/ficheros/pruebaEscritura.txt", lineas);

    }
}
