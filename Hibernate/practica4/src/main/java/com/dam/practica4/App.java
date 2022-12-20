package com.dam.practica4;

import com.dam.practica4.entidades.Categories;
import com.dam.practica4.utilidades.HibernateUtils;

public class App 
{
	
	public static void probarConexion() {
		if(HibernateUtils.abrirConexion()) {
			System.out.println("Conexión establecida correctamente");
			HibernateUtils.cerrarConexion();
		} else {
			System.out.println("Fallo al establecer la conexión");
		}
	}
	
	/**
	 * Ejemplo de como acceder a la lista de elementos de una clase
	 * En el ejemplo categories y recorrerla
	 */
	public static void listarCategorias() {
		HibernateUtils.abrirConexion();
		HibernateUtils.devolverListaObjetos(Categories.class)
			.forEach(c->System.out.println(c));
		HibernateUtils.cerrarConexion();
	}
	
    public static void main( String[] args )
    {
    	java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
    	//probarConexion();
    	listarCategorias();
    }
}
