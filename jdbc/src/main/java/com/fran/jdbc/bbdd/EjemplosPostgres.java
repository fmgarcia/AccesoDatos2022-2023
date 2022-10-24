package com.fran.jdbc.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fran.jdbc.entidades.Tabla1;

public class EjemplosPostgres {
		
	final static String USER ="postgres";
	final static String PASSWORD ="postgres";
	final static String URI ="jdbc:postgresql://localhost:5432/prueba";
	
	public static Connection con;
	public static Statement st;
	public static ResultSet rs;
	
	public static void conexion() {
		con = null;
		try {
			con = DriverManager.getConnection(URI, USER, PASSWORD);
			System.out.println("Conexión realizada correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en la conexión");
		} 
	}
	
	public static void desconexion() {
		try {
			if(!con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void conexion2() {
		try(Connection con2 = DriverManager.getConnection(URI, USER, PASSWORD)){
			System.out.println("Conexión realizada correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en la conexión");
		}
	}
	
	public static void selectBasica() {
		
		String sql = "Select * from tabla1";
		int registros = 0;
		try {
			st = con.createStatement(); // Poder hacer consultar sobre la conexión
			rs = st.executeQuery(sql);  // ejecutar la consulta
			while(rs.next()) {  // mientras haya registros a tratar
				registros++;
				System.out.println("ID: " + rs.getInt("id") + " Nombre: " + rs.getString("nombre"));
				//System.out.println("ID: " + rs.getInt(1) + " Nombre: " + rs.getString(2));
			}
			System.out.println("El total de registros es: " + registros);
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}  		
	}
	
	public static List<Tabla1> devolverRegistros(String sql) {		
		List<Tabla1> registros = new ArrayList<Tabla1>();
		try {
			st = con.createStatement(); // Poder hacer consultar sobre la conexión
			rs = st.executeQuery(sql);  // ejecutar la consulta
			while(rs.next()) {  // mientras haya registros a tratar
				registros.add(new Tabla1(rs.getInt("id"),rs.getString("nombre")));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}  	
		return registros;
	}
	
	public static void main(String[] args) {
		
		//conexion2();
		conexion();
		//selectBasica();
		//devolverRegistros("Select * from tabla1").forEach(e->System.out.println(e));
		// Usuarios que empiecen por 'F'
		//devolverRegistros("Select * from tabla1 where nombre like 'F%'").forEach(e->System.out.println(e));
		devolverRegistros("Select * from tabla1").stream()
		.filter(e->e.getNombre().charAt(0)=='F')
		.forEach(e->System.out.println(e));
		desconexion();
	}

}
