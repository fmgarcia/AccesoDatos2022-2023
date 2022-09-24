// Javier Benajes

package tema1_ad_1920;

import java.io.*;
import java.util.Scanner;

public class ExtraerTextoFicheroBinario {

	public static void main(String[] args) {
		
		String nomFich = "";
		Scanner sc = new Scanner(System.in);
		int dato;
		
		if (args.length == 1) {
			nomFich = args[0];
		} else {
			System.out.print("Introduce el nombre del fichero: ");
			nomFich = sc.nextLine();
		}
		
		try {
			FileInputStream fich = new FileInputStream(new File(nomFich + ".bmp"));
			PrintWriter fichSalida = new PrintWriter(nomFich + "_Texto.txt");
			
			while ((dato = fich.read()) != -1) {
				if (dato >= 32 && dato <= 127)
					fichSalida.print((char)dato);
			}
			fich.close();
			fichSalida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
