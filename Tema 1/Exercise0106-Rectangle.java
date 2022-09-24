package tema1_ad19_20;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Rectangulo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Filas: ");
		int filas = scan.nextInt();
		System.out.print("Columnas: ");
		int columnas = scan.nextInt();
		
		PrintWriter fich = null;
		try {
			fich = new PrintWriter("rectangulo.txt");
			for ( int fila = 0; fila < filas; fila++ ) {
				for ( int col = 0; col < columnas; col++) {
					if(fila==0 || fila == filas -1 || col==0 || col== columnas -1)
						fich.print("*");
					else {
						fich.print(" ");
					}
				}
				fich.println();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			fich.close();
		}				

	}

}
