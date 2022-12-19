package com.fran.hibernate2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.fran.hibernate2.entidades.Autores;
import com.fran.hibernate2.entidades.Libros;
import com.fran.hibernate2.utilidades.HibernateUtils;

import org.jboss.logging.Logger;



public class App 
{
	
	static SessionFactory  sessionFactory;
	static Session session;
	
	public static boolean abrirConexion() {		
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		return (session!=null);			
	}
	
	public static boolean cerrarConexion() {
		try {
			if(session!=null)
				session.close();
			if(sessionFactory!=null)
				sessionFactory.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static List<Libros> devolverLibros() {
		try {
			abrirConexion();
			List<Libros> resultado = session.createQuery("from Libros").list();
			//cerrarConexion();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			cerrarConexion();
			return null;
		} 		
	}
	
	public static void leerAutores() {
		session.createQuery("from Autores").list().stream().forEach(e->System.out.println(e));
	}
	
	public static List<Autores> devolverAutores() {
		try {
			abrirConexion();
			List<Autores> resultado = session.createQuery("from Autores").list();
			//cerrarConexion();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			cerrarConexion();
			return null;
		} 
	}
	
	public static boolean anyadirAutor() {
		try {
			abrirConexion();
			Transaction trans = session.beginTransaction();
			session.save(new Autores("FRAN","Francisco García"));		
			trans.commit();	
			cerrarConexion();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			cerrarConexion();
			return false;
		} 
	}
	
	public static boolean anyadirLibro() {		
		try {
			abrirConexion();
			Transaction trans = session.beginTransaction();
			session.save(new Libros(4, new Autores("WSHAK"), "Otello"));		
			trans.commit();
			cerrarConexion();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			cerrarConexion();
			return false;
		} 
	}
	
	
	public static int borrarLibro() {
		abrirConexion();
		List<Libros> resultado = session.createQuery("from Libros where id>=4").list();
		Transaction trans = session.beginTransaction();
		resultado.forEach(e->session.remove(e));  // anteriormente delete
		trans.commit();
		cerrarConexion();
		return resultado.size();
	}
	
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
    	
		//leerLibros();
		//leerAutores();
		//anyadirAutor();
		/*if(anyadirLibro()) {
			List<Libros> libros = devolverLibros();
			if(libros.size()>0) {
				libros.forEach(e->System.out.println(e));
			}
				
		}			
		else
			System.out.println("Fallo a la hora de añadir libro");
       
		cerrarConexion();*/
    	probarListaObjetos();
    	//probarAnyadirObjeto();
    	//System.out.println("Se han borrado " + borrarLibro() + " elemento/s");
    	//probarBorrarObjeto();
    	//probarModificarObjeto();
    	//probarModificarObjetoMap();
    }
}
