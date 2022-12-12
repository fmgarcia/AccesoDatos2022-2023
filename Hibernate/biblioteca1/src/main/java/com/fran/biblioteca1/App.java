package com.fran.biblioteca1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.fran.biblioteca1.entidades.Libros;


public class App 
{
	
	static SessionFactory  sessionFactory;
	static Session session;
	
	public static void probarConexion() {
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		if(session!=null) {
			System.out.println("Sesión conectada correctamente");
		} else {
			System.out.println("Fallo al conectar con la base de datos");
		}		
	}
	
	public static void leerLibros() {
		Query<Libros> query = session.createQuery("from Libros");
		List<Libros> libros = query.list();
		libros.stream().forEach(l->System.out.println(l));
	}
	
    public static void main( String[] args )
    {
        probarConexion();
        leerLibros();
    }
}
