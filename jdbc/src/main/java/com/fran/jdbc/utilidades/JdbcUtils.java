package com.fran.jdbc.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	
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
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static int StatementDML(String sql) {		
		int registros = 0;
		try {
			st = con.createStatement(); // Poder hacer consultar sobre la conexión
			return st.executeUpdate(sql);  // ejecutar la consulta
		} catch (SQLException e) {
			e.printStackTrace();
		}  	
		return registros;
	}
	

}
