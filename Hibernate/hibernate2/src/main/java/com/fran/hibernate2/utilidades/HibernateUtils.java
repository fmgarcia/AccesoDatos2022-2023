package com.fran.hibernate2.utilidades;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.fran.hibernate2.entidades.Autores;
import com.fran.hibernate2.entidades.Libros;

public class HibernateUtils {
	
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
	
	public static List<?> devolverListaObjetos(String clase) {		
		return session.createQuery("FROM " + clase).list();
	}
	
	public static <T> List<T> devolverListaObjetos(Class<T> clase){
		return session.createQuery("from " + clase.getName()).list();
	}
	
	public static boolean saveAll(List<Object> objects) {
		try {
			Transaction trans = session.beginTransaction();
			objects.forEach(object->session.persist(object)); // antiguamente save. Persist a partir de la versión 6.0  
			trans.commit();	
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	public static boolean save(Object object) {
		try {
			Transaction trans = session.beginTransaction();
			session.persist(object); // antiguamente save. Persist a partir de la versión 6.0  
			trans.commit();	
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	/**
	 * Borramos los objetos de la clase que cumplen la condición Where pasada.
	 * @param <T> Clase con la que trabajamos 
	 * @param clase Objeto de la clase.
	 * @param where Condición de la clave primaria.
	 * @return n si borra n registros, 0 si no borra ningún registro, -1 en caso de error.
	 * (e.g) deleteAll(NombreClase.class,"campo='A'")
	 */
	public static <T> int deleteAll(Class<T> clase, String where) {
		Transaction trans = null;
		try {
			List<T> resultado = session.createQuery("FROM " + clase.getName() + " WHERE " + where).list();
			trans = session.beginTransaction();
			resultado.forEach(e->session.remove(e));  // anteriormente delete
			trans.commit();
			return resultado.size();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return -1;
		}
	}

	/**
	 * Borramos el objeto de la clase que cumplen la condición Where pasada.
	 * @param <T> Clase con la que trabajamos 
	 * @param clase Objeto de la clase.
	 * @param where Condición de la clave primaria.
	 * @return 1 si borra el registro, 0 si no existe, -1 en caso de error.
	 * (e.g) deleteById(NombreClase.class,"id=1")
	 */
	public static <T> int deleteById(Class<T> clase, String where) {
		return deleteAll(clase,where);
	}
	
	

}
