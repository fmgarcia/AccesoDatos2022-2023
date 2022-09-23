package com.fran.ficheros;

import com.fran.ficheros.utilidades.Ficheros;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/*Ficheros.leerFichero8("c:/ficheros", "datos.txt")
        .forEach(l->System.out.println(l));*/
    	//Ficheros.leerFichero8("prueba.txt").forEach(l->System.out.println(l));
    	Ficheros.leerFichero8("c:/ficheros/datos.txt")
    		.forEach(l->System.out.println(l));

    }
}
