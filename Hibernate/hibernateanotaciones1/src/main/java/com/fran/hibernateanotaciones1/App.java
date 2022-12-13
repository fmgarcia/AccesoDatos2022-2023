package com.fran.hibernateanotaciones1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;

import com.fran.hibernateanotaciones1.entidades.Autores;
import com.fran.hibernateanotaciones1.entidades.Libros;
import com.fran.hibernateanotaciones1.utilidades.HibernateUtils;

public class App 
{
	
	public static void probarListaObjetos() {
		HibernateUtils.abrirConexion();
    	List<Libros> libros = (List<Libros>) HibernateUtils.devolverListaObjetos("Libros");
		System.out.println("Imprimiendo libros :");
    	libros.forEach(e->System.out.println(e));
		List<Autores> autores = (List<Autores>) HibernateUtils.devolverListaObjetos("Autores");
		System.out.println("Imprimiendo autores :");
		autores.forEach(e->System.out.println(e));
		libros = HibernateUtils.devolverListaObjetos(Libros.class);
		System.out.println("Imprimiendo libros :");
		libros.forEach(e->System.out.println(e));
		autores = HibernateUtils.devolverListaObjetos(Autores.class);
		System.out.println("Imprimiendo autores :");
		autores.forEach(e->System.out.println(e));
		HibernateUtils.cerrarConexion();
	}
	
	public static void probarAnyadirObjeto() {
		HibernateUtils.abrirConexion();
		if(HibernateUtils.save(new Libros(5,new Autores("WSHAK"),"Hamlet"))) {
			System.out.println("Libro insertado correctamente");
		} else {
			System.out.println("Fallo la inserción del libro");
		}
		if(HibernateUtils.save(new Autores("TOK","Tolkien"))) {
			System.out.println("Autor insertado correctamente");
		} else {
			System.out.println("Fallo la inserción del autor");
		}
	}
	
    public static void probarBorrarObjeto() {
    	HibernateUtils.abrirConexion();
    	System.out.println("Se han borrado " 
    			+ HibernateUtils.deleteAll(Autores.class, "cod='TOK'") + " elemento/s");
    	HibernateUtils.cerrarConexion();
    }
    
    public static void probarModificarObjeto() {
    	HibernateUtils.abrirConexion();
    	if(HibernateUtils.update(Autores.class, "cod='FRAN'", new Autores(null, "Prueba cambio")))
    		System.out.println("Cambio realizado correctamente");
    	else
    		System.out.println("Fallo el update");
    	HibernateUtils.cerrarConexion();
    }
    
    public static void probarModificarObjetoMap() {
    	Map<String,Object> mapa = new HashMap<String,Object>(); 
		mapa.put("nombre", "Prueba cambio mapa"); 
    	HibernateUtils.abrirConexion();
    	if(HibernateUtils.update(Autores.class, "cod='FRAN'", mapa ))
    		System.out.println("Cambio realizado correctamente");
    	else
    		System.out.println("Fallo el update");
    	HibernateUtils.cerrarConexion();
    }
	
	
    public static void main( String[] args )
    {
    	
    	@SuppressWarnings("unused")
    	Logger logger = Logger.getLogger("org.hibernate");
    	java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
    	
    	//probarListaObjetos();
    	//probarAnyadirObjeto();
    	//probarBorrarObjeto();
    	//probarModificarObjeto();
    	//probarModificarObjetoMap();
    }
}
