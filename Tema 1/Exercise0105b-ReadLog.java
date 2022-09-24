package tema1_ad19_20;

import java.io.*;
import java.util.Scanner;

public class LeerLog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Primero vemos si el fichero existe
		
		Scanner sc = new Scanner(System.in);
		BufferedReader ficheroEntrada = null;
		try {			
			if ((new File("log.txt")).exists()) {
				ficheroEntrada = new BufferedReader(new FileReader(new File("log.txt")));
			}
			else {
				System.out.println("Escriba nombre de fichero: ");
				String file = sc.nextLine();
				ficheroEntrada = new BufferedReader(new FileReader(new File(file)));
			}
			
			String linea = ficheroEntrada.readLine();
			while (linea != null) {
				System.out.println(linea);
				linea = ficheroEntrada.readLine();
			}
			ficheroEntrada.close();
		} catch (IOException errorDeFichero) {
			System.out.println("Ha habido problemas: " + errorDeFichero.getMessage());
		}

	}

}
