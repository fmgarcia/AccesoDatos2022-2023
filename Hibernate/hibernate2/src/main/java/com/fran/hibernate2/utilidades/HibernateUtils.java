package com.fran.hibernate2.utilidades;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
	
	public static int saveNoTransaction(List<Object> objects) {
		int[] contador= {0};
		objects.forEach(e->contador[0] += save(e)?1:0);
		return contador[0];
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
	
	/**
	 * Método que actualiza un registro de la base de datos mediante Hibernate a
	 * partir de un objeto con los nuevos datos.
	 * 
	 * @param <T>   Clase con la que trabajaremos
	 * @param clase Nombre del objeto de tipo clase.
	 * @param where Condición de búsqueda en la clase.
	 * @param datos Objeto con los nuevos datos, actualiza todo lo que no sea null
	 *              (no se puede actualizar un campo a null)
	 * @return True si ha sido correcta la actualización de todos los registros. False en caso contrario.
	 */
	public static <T> boolean update(Class<T> clase, String where, T datos) {
		Transaction trans = null;
		try {
			List<T> resultados = session.createQuery("FROM " + clase.getSimpleName() + " WHERE " + where).list();
			trans = session.beginTransaction();
			Arrays.asList(clase.getDeclaredFields()).forEach(f -> {
				resultados.forEach(r -> {
					f.setAccessible(true);
					try {
						if (f.get(datos) != null && !Modifier.isStatic(f.getModifiers()))
							f.set(r, f.get(datos));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					f.setAccessible(false);
				});
			});
			resultados.forEach(r -> session.merge(r));  // antes de la versión 6 era update
			trans.commit();
			return true;
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Método que actualiza un registro de la base de datos mediante Hibernate a
	 * partir de los campos que queremos actualizar que pasaremos en un mapa de
	 * datos.
	 * 
	 * @param <T>   Clase con la que trabajaremos
	 * @param clase Nombre del objeto de tipo clase.
	 * @param where Condición de búsqueda en la clase.
	 * @param datos Mapa con la clave-valor de los atributos que queremos modificar
	 *              y su valor.
	 * @return True si ha sido correcta la actualización de TODOS los registros. False en caso contrario.
	 */
	@SuppressWarnings("unchecked")
	public static <T> boolean update(Class<T> clase, String where, Map<String, Object> datos) {
		Transaction trans = null;
		try {
			
			List<T> resultados = session.createQuery("FROM " + clase.getSimpleName() + " WHERE " + where).list();
			trans = session.beginTransaction();

			Arrays.asList(clase.getDeclaredFields()).forEach(f -> {
				resultados.forEach(r -> {
					f.setAccessible(true);
					try {

						if (datos.containsKey(f.getName()))
							f.set(r, datos.get(f.getName()));

					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					f.setAccessible(false);
				});
			});

			resultados.forEach(r -> session.merge(r)); // antes era update

			trans.commit();
			return true;
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
			return false;
		}
	}

}
