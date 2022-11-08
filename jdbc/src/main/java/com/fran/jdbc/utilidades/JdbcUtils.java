package com.fran.jdbc.utilidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;


public class JdbcUtils {
	
	public static Connection con;
	public static Statement st;
	public static PreparedStatement ps;
	public static ResultSet rs;	
	private static CallableStatement cs = null;
	
	public static void conexion(String uri, String user, String password) {
		con = null;
		try {
			con = DriverManager.getConnection(uri, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	public static ResultSet devolverResultSet(String sql) {		
		try {
			st = con.createStatement(); 
			return st.executeQuery(sql);  
		} catch (SQLException e) {
			e.printStackTrace();
		}  	
		return null;
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

	// https://stackoverflow.com/questions/22671697/try-try-with-resources-and-connection-statement-and-resultset-closing
	public static ResultSet preparedStatementSelectCompleto(String uri, String user, String password,String sql,List<Object> parametros) {
		if(parametros.size()!=countMatches(sql, '?'))
			return null;
		try (Connection conexion = DriverManager.getConnection(uri, user, password);) {
			PreparedStatement preparedStament = conexion.prepareStatement(sql);
			for(int i=0;i<parametros.size();i++) {
				preparedStament.setObject(i+1, parametros.get(i));				
			}
			ResultSet rs = preparedStament.executeQuery();				
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static ResultSet preparedStatementSelectCompleto(String uri, String user, String password,String sql,Object... parametros) {
		return preparedStatementSelectCompleto(uri,user,password,sql,Arrays.asList(parametros));
	}
	
	/**
	 * Dada una Sql y una Lista de Objectos con sus parametros a cambiar por las '?'
	 * nos devuelve el ResultSet de los resultados de ejecutar la consulta
	 * @param sql String con la SQL con las '?' para que puede ser bindeadas
	 * @param parametros Lista de Objetos a parsear por las '?'
	 * @return ResultSet de la Select
	 */
	public static ResultSet preparedStatementSelect(String sql,List<Object> parametros) {
		if(parametros.size()!=countMatches(sql, '?'))
			return null;
		try {
			ps = con.prepareStatement(sql); 
			for(int i=0;i<parametros.size();i++) {
				ps.setObject(i+1, parametros.get(i));				
			}
			return ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * Dada una Sql y un listado individual de sus parametros a cambiar por las '?'
	 * nos devuelve el ResultSet de los resultados de ejecutar la consulta
	 * @param sql String con la SQL con las '?' para que puede ser bindeadas
	 * @param parametros Objetos a parsear por las '?'
	 * @return ResultSet de la Select
	 */
	public static ResultSet preparedStatementSelect(String sql,Object... parametros) {
		return preparedStatementSelect(sql,Arrays.asList(parametros));
	}
	
	/**
	 * Dada una Sql y una Lista de Objectos con sus parametros a cambiar por las '?'
	 * nos devuelve el número de registros afectados por la Insert, Update o Delete
	 * @param sql String con la SQL con las '?' para que puede ser bindeadas
	 * @param parametros Lista de Objetos a parsear por las '?'
	 * @return -1 en caso de error o el número de registros afectados por la Insert, Update o Delete
	 */
	public static int preparedStatementInsertUpdateDelete(String sql,List<Object> parametros) {
		if(parametros.size()!=countMatches(sql, '?'))
			return -1;
		try {
			ps = con.prepareStatement(sql); 
			for(int i=0;i<parametros.size();i++) {
				ps.setObject(i+1, parametros.get(i));				
			}
			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return -1;
	}
	
	/**
	 * Dada una Sql y una listado individual de Objectos con sus parametros a cambiar por las '?'
	 * nos devuelve el número de registros afectados por la Insert, Update o Delete
	 * @param sql String con la SQL con las '?' para que puede ser bindeadas
	 * @param parametros Lista de Objetos a parsear por las '?'
	 * @return -1 en caso de error o el número de registros afectados por la Insert, Update o Delete
	 */
	public static int preparedStatementInsertUpdateDelete(String sql,Object... parametros) {
		return preparedStatementInsertUpdateDelete(sql,Arrays.asList(parametros));
	}
	
	/**
	 * Método genérico que llama a un CallableStatement y devuelve el resultado que nos envía la base de datos
	 * @param metodo Nombre del procedimiento almacenado en la base de datos junto con sus parámetros indicados por '?'
	 * @param tipoDevuelto Entero que representa un tipo del listado de Types (ej: Types.INTEGER = 4)
	 * @param parametros Lista con los parámetros a cambiar por las '?'
	 * @return Objeto que nos devuelve el Procedimiento almacenado de la Base de datos
	 */
	public static Object ejecutarCallableStatement(String metodo, int tipoDevuelto, List<Object> parametros) {
		try {
			cs = con.prepareCall("{call " + metodo + "}");
			cs.registerOutParameter(1, tipoDevuelto); 													
			for(int i=1;i<=countMatches(metodo, '?');i++) {
				cs.setObject(i, parametros.get(i-1));
			}
			cs.execute();
			return cs.getObject(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Método genérico que llama a un CallableStatement y devuelve el resultado que nos envía la base de datos
	 * @param metodo Nombre del procedimiento almacenado en la base de datos junto con sus parámetros indicados por '?'
	 * @param tipoDevuelto Entero que representa un tipo del listado de Types (ej: Types.INTEGER = 4)
	 * @param parametros Lista de objetos con los parámetros a cambiar por las '?'
	 * @return Objeto que nos devuelve el Procedimiento almacenado de la Base de datos
	 */
	public static Object ejecutarCallableStatement(String metodo, int tipoDevuelto, Object... parametros) {
		return ejecutarCallableStatement(metodo,tipoDevuelto,Arrays.asList(parametros));
	}

	
	/**
	 * Método genérico que llama a un CallableStatement y devuelve el resultado que nos envía la base de datos en un ResultSet
	 * @param metodo Nombre del procedimiento almacenado en la base de datos junto con sus parámetros indicados por '?'
	 * @param parametros Lista con los parámetros a cambiar por las '?'
	 * @return ResultSet que nos devuelve el Procedimiento almacenado de la Base de datos
	 */
	public static ResultSet ejecutarCallableStatement(String metodo, List<Object> parametros) {
		try {
			cs = con.prepareCall("{call " + metodo + "}"); 													
			for(int i=1;i<=countMatches(metodo, '?');i++) {
				cs.setObject(i, parametros.get(i-1));
			}			
			return cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Método genérico que llama a un CallableStatement y devuelve el resultado que nos envía la base de datos en un ResultSet
	 * @param metodo Nombre del procedimiento almacenado en la base de datos junto con sus parámetros indicados por '?'
	 * @param parametros Lista de Objetos con los parámetros a cambiar por las '?'
	 * @return ResultSet que nos devuelve el Procedimiento almacenado de la Base de datos
	 */
	public static ResultSet ejecutarCallableStatement(String metodo, Object... parametros) {
		return ejecutarCallableStatement(metodo,Arrays.asList(parametros));
	}
	
	
	private static int countMatches(String sql, char caracterBuscado) {
		return (int)sql.chars().filter(e->e==caracterBuscado).count();
	}
	
}
