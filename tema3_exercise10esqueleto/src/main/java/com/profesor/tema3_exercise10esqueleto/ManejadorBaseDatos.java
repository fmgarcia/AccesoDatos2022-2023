package com.profesor.tema3_exercise10esqueleto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ManejadorBaseDatos {
	Scanner sc = new Scanner(System.in);
	String url = "jdbc:postgresql://localhost:5432/gestionpersonal";
	String usuario = "postgres";
	String password = "postgres";
	
	public ManejadorBaseDatos() 
		throws ClassNotFoundException, SQLException
	{
		checkDataBaseIsCreated();
	}
	
	private void checkDataBaseIsCreated() throws 
		ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5432/";
		String usuario = "postgres";
		String password = "postgres";
		
		Connection con = DriverManager.getConnection(url, usuario, password);
		Statement statement = con.createStatement();
		
		String sentenciaSQL = 
				"SELECT * FROM pg_database WHERE datname LIKE 'gestionpersonal';";
		
		ResultSet rs = statement.executeQuery(sentenciaSQL);
		
		if (!rs.next()) {
			boolean exit = false;
			System.out.println("No se ha encontrado base de datos ");
			do
			{
				System.out.println("Crear base de datos automaticamente? (si/no)");
				String answer = sc.nextLine();
				
				if (answer.equals("si")) 
				{
					createDataBase();
					exit = true;
				}
				else if(answer.equals("no"))
				{
					System.out.println("No se creó la base de datos");
					System.exit(0);
				}
				else
				{
					System.out.println("Error de escritura prueba con (si/no)");
				}
			}
			while(!exit);
		}
		else
		{
			System.out.println("Se ha encontrado la base de datos ");
			boolean exit = false;
			do
			{
				System.out.println("Borrar y crear un nueva base de datos "
						+ "automaticamente? (si/no)");
				String answer = sc.nextLine();
				
				if (answer.equals("si")) 
				{
					createDataBase();
					exit = true;
				}
				else if(answer.equals("no"))
				{
					checkTablesAreCreated();
					exit = true;
				}
				else
				{
					System.out.println("Error de escritura prueba con (si/no)");
				}
			}
			while(!exit);
			
		}
	}
	
	private void checkTablesAreCreated() 
		throws ClassNotFoundException, SQLException {
		Connection con = conection();
		Statement statement = con.createStatement();
		
		String sentenciaSQL = 
				"SELECT count(*) " + 
				"FROM information_schema.tables " + 
				"WHERE table_type = 'BASE TABLE' " + 
				"AND table_name = 'personas' " + 
				"OR table_name = 'clientes' " + 
				"OR table_name = 'funcionarios';";
		
		ResultSet rs = statement.executeQuery(sentenciaSQL);
		rs.next();
		if(rs.getInt(1) != 3)
		{
			System.out.println("No se ha encontrado tablas ");
			System.out.println("Crear tablas automaticamente? (si/no)");
			String answer = sc.nextLine();
			
			if (answer.equals("si")) {
				createTables();
			}
			else
			{
				System.out.println("No se crearon las tablas");
				System.exit(0);
			}
		}
		else {
			System.out.println("Tablas cargadas con exito");
		}
		
	}
	
	public void createTables() 
			throws ClassNotFoundException, SQLException
	{
		Connection con = conection();
		Statement statement = con.createStatement();
		
		/* En el siguiente String tendremos que concatenar
		 * las diferentes instrucciones de borrado de tablas y su creación
		 * los tipos de datos que queramos hacer y las tablas heredadas
		 * Es el Script de creación de nuestra base de datos
		 */
		String sentenciaSQL = "CREATE TYPE estadotype AS ENUM ('activo', 'pendiente', 'inactivo');"; 
		sentenciaSQL += "CREATE TYPE tipoclientetype AS ENUM ('normal', 'premium');";
		sentenciaSQL += "CREATE TYPE grupotype AS ENUM ('A1', 'A2', 'C1', 'C2', 'AP');";
		sentenciaSQL += "CREATE TYPE cargotype AS (cargo grupotype,codigo character varying(5));";
		sentenciaSQL += "";
		
		try {
			statement.executeUpdate(sentenciaSQL);
			
		} catch (Exception e) {
			System.out.println("Problemas creando las tablas");
		} finally {
			con.close();
		}
		
		/*
		 * Aquí crearemos los datos de prueba iniciales que queramos insertar
		 * en nuestra base de datos
		 */
		String pruebaPersonas = "";
		String pruebaCliente = "";
		String pruebaFuncionario = "";
		
		update(pruebaPersonas);
		update(pruebaCliente);
		update(pruebaFuncionario);
	}

	private void createDataBase() throws 
	ClassNotFoundException, SQLException 
	{
		System.out.println("Creando base de datos GestionPersonal");
		
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5432/";
		String usuario = "postgres";
		String password = "postgres";
		
		Connection con = DriverManager.getConnection(url, usuario, password);
		Statement statement = con.createStatement();
		
		String sentenciaSQL = "DROP DATABASE IF EXISTS gestionpersonal; "
				+ "CREATE DATABASE gestionpersonal;";
		
		try {
			int errorCode = statement.executeUpdate(sentenciaSQL);
			
			if (errorCode == 0) {
				System.out.println("Se ha creado con exito la base de datos");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			con.close();
		}
		
		createTables();
	}
	
	private Connection conection() throws
	ClassNotFoundException, SQLException
	{
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(url, usuario, password);
	}
	
	
	public void update(String sentenceSQL) 
		throws ClassNotFoundException, SQLException
	{
		Connection con = conection();
		Statement statement = con.createStatement();
		
		try {
			statement.executeUpdate(sentenceSQL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			con.close();
		}
	}
	
	public ResultSet select(String sentenciaSQL) 
		throws ClassNotFoundException, SQLException
	{
		Connection con = conection();
		Statement statement = con.createStatement();
		
		ResultSet rs = statement.executeQuery(sentenciaSQL);
		
		con.close();
		
		return rs;
	}
}
                           
                           
  