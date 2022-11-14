package com.profesor.tema3_exercise10esqueleto;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Ejercicio0310 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main (String[] args) throws ClassNotFoundException, SQLException{
		ManejadorBaseDatos manejador = new ManejadorBaseDatos();
		
		boolean exit = false;
		
		do
		{
			ShowMenu();
			switch (getOption()) {
			case 1:
				addPersona(manejador);
				break;
			case 2:
				addCliente(manejador);		
				break;
			case 3:
				addFuncionario(manejador);			
				break;
			case 4:
				modifyPersona(manejador);			
				break;
			case 5:
				modifyCliente(manejador);			
				break;
			case 6:
				modifyFuncionario(manejador);			
				break;
			case 7:
				showPersonas(manejador);			
				break;
			case 8:
				showClientes(manejador);			
				break;
			case 9:
				showFuncionarios(manejador);			
				break;
			case 0:
				exit = true;
				break;
			default:
				break;
			}
		}
		while(!exit);
	}
	
	private static int getOption() {
		System.out.println("Option:");
		int option = sc.nextInt();
		sc.nextLine();
		return option;
	}

	private static void ShowMenu()
	{
		System.out.println("1.-Añadir persona");
		System.out.println("2.-Añadir cliente");
		System.out.println("3.-Añadir funcionario");
		System.out.println("4.-Modificar persona");
		System.out.println("5.-Modificar cliente");
		System.out.println("6.-Modificar funcionario");
		System.out.println("7.-Ver personas");
		System.out.println("8.-Ver clientes");
		System.out.println("9.-Ver funcionarios");
		System.out.println("0.-Salir");
	}
	
	
	private static void addPersona(ManejadorBaseDatos manejador) 
			throws ClassNotFoundException, SQLException 
		{

		}

	private static void addCliente(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{

	}
	
	private static void addFuncionario(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{

	}
	
	private static void modifyPersona(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{

	}
	
	private static void modifyCliente(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{

	}
	
	private static void modifyFuncionario(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{

	}
	
	private static void showPersonas(ManejadorBaseDatos manejador) 
		throws SQLException, ClassNotFoundException 
	{

	}
	
	private static void showClientes(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{

	}
	
	private static void showFuncionarios(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{

	}
	



}