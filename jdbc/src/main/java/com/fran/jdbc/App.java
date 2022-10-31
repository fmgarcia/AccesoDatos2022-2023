package com.fran.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fran.jdbc.utilidades.JdbcUtils;

/**
 * Hello world!
 *
 */
public class App 
{
	
	final static String USER ="postgres";
	final static String PASSWORD ="postgres";
	final static String URI ="jdbc:postgresql://localhost:5432/prueba";
	
    public static void main( String[] args )
    {
        /*Tabla1 t1 = new Tabla1();
        t1.setId(1);
        t1.setNombre("prueba");
        System.out.println(t1);*/
    	
    	/* Ejemplo de Select con PreparedStatement 
    	
    	List<Object> parametros = new ArrayList<Object>();
    	parametros.add(1);
    	parametros.add("Fran");
    	String sql = "Select * from tabla1 where id>? or nombre=?";
    	//ResultSet resultado = JdbcUtils.preparedStatementSelect(sql, parametros);
    	try {
			JdbcUtils.conexion(URI, USER, PASSWORD);
			ResultSet rs = JdbcUtils.preparedStatementSelect(sql, 1, "Fran");
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + " Nombre: " + rs.getString("nombre"));
			}
			JdbcUtils.desconexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    	
    	/*JdbcUtils.conexion(URI, USER, PASSWORD);
		int registros = JdbcUtils.preparedStatementInsertUpdateDelete("UPDATE tabla1 SET nombre=? WHERE nombre=?", "Daniel", "Dani");
		System.out.println("Se ha" + ((registros==1)?"":"n") + " modificado " + registros + " registro" + ((registros==1)?"":"s"));
		JdbcUtils.desconexion();*/
    	try {
			String sql = "Select * from tabla1 where id>? or nombre=?";
			ResultSet rs = JdbcUtils.preparedStatementSelectCompleto(URI, USER, PASSWORD,sql,1,"Fran");
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + " Nombre: " + rs.getString("nombre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
