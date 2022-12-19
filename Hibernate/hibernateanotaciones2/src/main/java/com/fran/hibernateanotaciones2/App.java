package com.fran.hibernateanotaciones2;

import java.util.HashSet;
import java.util.Set;

import org.jboss.logging.Logger;

import com.fran.hibernateanotaciones2.entidades.Autores;
import com.fran.hibernateanotaciones2.entidades.Libros;
import com.fran.hibernateanotaciones2.utilidades.HibernateUtils;

public class App 
{
	
	public static void probarConexion() {
		if(HibernateUtils.abrirConexion()) {
			System.out.println("Conexión abierta correctamente");
			HibernateUtils.cerrarConexion();
		} else {
			System.out.println("No se ha podido abrir la conexión");
		}
	}
	
	public static void listarLibros() {
		HibernateUtils.abrirConexion();
		HibernateUtils.devolverListaObjetos(Libros.class)
			.forEach(l->System.out.println(l));
		HibernateUtils.cerrarConexion();
	}
	
	public static void listarAutores() {
		HibernateUtils.abrirConexion();
		HibernateUtils.devolverListaObjetos(Autores.class)
			.forEach(a->System.out.println(a));
		HibernateUtils.cerrarConexion();
	}
	
	public static void insertarLibro() {
		HibernateUtils.abrirConexion();
		Set<Autores> autoresLibro = new HashSet<Autores>();
		autoresLibro.add(new Autores("2"));
		autoresLibro.add(new Autores("3"));		
		if(HibernateUtils.save(new Libros(4,"Ejemplo Insertar",autoresLibro))) {
			System.out.println("Libro insertado correctamente");
		} else {
			System.out.println("Error al insertar el libro");
		}
		HibernateUtils.cerrarConexion();
	}
	
	public static void borrarLibro() {
		HibernateUtils.abrirConexion();
		if(HibernateUtils.deleteById(Libros.class, "id=4")==1) {
			System.out.println("Libro borrado correctamente");
		} else {
			System.out.println("Error al borrar el libro o libro inexistente");
		}
		HibernateUtils.cerrarConexion();
	}
	
	public static void modificarLibro() {
		HibernateUtils.abrirConexion();
		if(HibernateUtils.update(Libros.class, "id=1",new Libros(1,"Nombre actualizado",null))) {
			System.out.println("Libro actualizado correctamente");
		} else {
			System.out.println("Error al actualizar el libro");
		}
		HibernateUtils.cerrarConexion();
	}
	
    public static void main( String[] args )
    {  
    	java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
    	//probarConexion();
    	//listarLibros();
    	//listarAutores();
    	//insertarLibro();
    	//borrarLibro();
    	modificarLibro();
    }
}
