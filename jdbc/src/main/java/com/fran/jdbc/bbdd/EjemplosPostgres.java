package com.fran.jdbc.bbdd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fran.jdbc.entidades.Tabla1;

public class EjemplosPostgres {

	final static String USER = "postgres";
	final static String PASSWORD = "postgres";
	final static String URI = "jdbc:postgresql://localhost:5432/prueba";

	public static Connection con;
	public static Statement st;
	public static ResultSet rs;
	public static PreparedStatement ps;

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
			if (!con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void conexion2() {
		try (Connection con2 = DriverManager.getConnection(URI, USER, PASSWORD)) {
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
			rs = st.executeQuery(sql); // ejecutar la consulta
			while (rs.next()) { // mientras haya registros a tratar
				registros++;
				System.out.println("ID: " + rs.getInt("id") + " Nombre: " + rs.getString("nombre"));
				// System.out.println("ID: " + rs.getInt(1) + " Nombre: " + rs.getString(2));
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
			rs = st.executeQuery(sql); // ejecutar la consulta
			while (rs.next()) { // mientras haya registros a tratar
				registros.add(new Tabla1(rs.getInt("id"), rs.getString("nombre")));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registros;
	}

	public static ResultSet devolverResultSet(String sql) {
		try {
			st = con.createStatement();
			return st.executeQuery(sql); // devolver resultados
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int modificarRegistros(String sql) {
		try {
			st = con.createStatement(); // Poder hacer consultar sobre la conexión
			return st.executeUpdate(sql); // ejecutar la consulta
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static void preparedStatementBasica(int numero, String nombre) {
		String sql = "Select * from tabla1 where id>? or nombre=?";
		int registros = 0;
		try {
			ps = con.prepareStatement(sql); // Primero preparo la consulta
			ps.setInt(1, numero); // número de ? 1
			ps.setString(2, nombre); // string de ? 2
			rs = ps.executeQuery(); // ejecutar la consulta
			while (rs.next()) { // mientras haya registros a tratar
				registros++;
				System.out.println("ID: " + rs.getInt("id") + " Nombre: " + rs.getString("nombre"));
				// System.out.println("ID: " + rs.getInt(1) + " Nombre: " + rs.getString(2));
			}
			System.out.println("El total de registros es: " + registros);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int preparedStatementDML(int numero, String nombre) {
		String sql = "UPDATE tabla1 SET nombre=? where id=?";
		int registros = 0;
		try {
			ps = con.prepareStatement(sql); // Primero preparo la consulta
			ps.setString(1, nombre); // string de ? 1
			ps.setInt(2, numero); // número de ? 2
			registros = ps.executeUpdate(); // ejecutar la sentencia de modificación
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registros;
	}

	public static void ejemploCallableStatement() {
		try {
			CallableStatement cStmt = con.prepareCall("{call cantidadpersonas(?)}");
			cStmt.registerOutParameter(1, Types.INTEGER);  // Registro que devolverá el PL
			cStmt.setString(1, "%a%"); // Cambio las ? por sus valores
			cStmt.execute(); // Ejecuto el PL
			int resultado = cStmt.getInt(1);  // Obtengo el resultado
			System.out.println("Resultado: " + resultado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int ejemploCallableStatementV2(String nombreProcedimiento,Object...objects) {
		try {
			CallableStatement cStmt = con.prepareCall("{call " + nombreProcedimiento +"}");
			cStmt.registerOutParameter(1, Types.INTEGER);  // Registro que devolverá el PL
			for(int i=0;i<objects.length;i++)
				cStmt.setObject(i+1, objects[i]); // Cambio las ? por sus valores
			cStmt.execute(); // Ejecuto el PL
			return cStmt.getInt(1);  // Obtengo el resultado
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static void ejemploCallableTabla() {
		try {
			CallableStatement cStmt = con.prepareCall("{call listar_tabla1(?)}");
			cStmt.setString(1, "%i%"); // Cambio las ? por sus valores
			ResultSet rs = cStmt.executeQuery();  // Obtengo el resultado
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt("id_dev") 
				+ " Nombre: " + rs.getString("nombre_dev"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// conexion2();
		conexion();
		// selectBasica();
		// devolverRegistros("Select * from tabla1").forEach(e->System.out.println(e));
		// Usuarios que empiecen por 'F'
		// devolverRegistros("Select * from tabla1 where nombre like
		// 'F%'").forEach(e->System.out.println(e));
		/*
		 * devolverRegistros("Select * from tabla1").stream()
		 * .filter(e->e.getNombre().charAt(0)=='F') .forEach(e->System.out.println(e));
		 */
		// Hacer UPDATE, DELETE o INSERT sobre una base de datos
		/*
		 * System.out.println("El número de registros actualizados es: " +
		 * modificarRegistros("UPDATE tabla1 SET nombre='Francisco' WHERE nombre='Fran'"
		 * )); System.out.println("El número de registros borrados es: " +
		 * modificarRegistros("DELETE FROM tabla1 WHERE nombre='Francisco'"));
		 * System.out.println("El número de registros insertados es: " +
		 * modificarRegistros("INSERT INTO tabla1(id,nombre) VALUES(10,'Francisco')"));
		 */
		// Ejemplo de Sql Injection
		/*
		 * System.out.println("Introduzca nombre a borrar:"); String nombre =
		 * sc.nextLine(); modificarRegistros("DELETE FROM tabla1 WHERE nombre='" +
		 * nombre + "'"); // Prueba a introducir el siguiente nombre: a' or 'a'='a
		 */
		// preparedStatementBasica(2,"Fran");
		// preparedStatementBasica(2,"Fran or 'a'='a'"); // No provoca sql injection
		//preparedStatementDML(1, "Francisco");
		//ejemploCallableStatement();
		//System.out.println("Los resultados son: " + ejemploCallableStatementV2("cantidadpersonas(?)","%a%"));
		ejemploCallableTabla();
		desconexion();
	}

}
