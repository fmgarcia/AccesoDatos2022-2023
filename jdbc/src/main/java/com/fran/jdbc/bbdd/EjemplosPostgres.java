package com.fran.jdbc.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EjemplosPostgres {
		
	final static String USER ="postgres";
	final static String PASSWORD ="postgres";
	final static String URI ="jdbc:postgresql://localhost:5432/prueba";
	
	public static void conexion() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URI, USER, PASSWORD);
			System.out.println("Conexión realizada correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en la conexión");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void conexion2() {
		try(Connection con = DriverManager.getConnection(URI, USER, PASSWORD)){
			System.out.println("Conexión realizada correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en la conexión");
		}
	}
	
	public static void main(String[] args) {
		//conexion();
		conexion2();
	}

}
