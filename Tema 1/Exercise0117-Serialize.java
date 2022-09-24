package tema1_ad19_20;

import java.io.*;

public class Exercise17 {
	static Persona p = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File fichero = new File("personas.dat");
		 FileOutputStream ficheroSalida;
		try {
			ficheroSalida = new FileOutputStream(fichero);
			ObjectOutputStream ficheroObjetos =
					 new ObjectOutputStream(ficheroSalida);
			
			p = new Persona("Fran","a@a.com","01/01/2019");
			ficheroObjetos.writeObject(p);
			p = new Persona("Dani","b@b.com","02/01/2019");
			ficheroObjetos.writeObject(p);
			ficheroObjetos.close();		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
