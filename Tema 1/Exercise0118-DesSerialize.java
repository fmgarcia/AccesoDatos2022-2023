package tema1_ad19_20;

import java.io.*;

public class DesSerializar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File fichero = new File("personas.dat");
		try {
		FileInputStream ficheroSalida = new FileInputStream(fichero);
		ObjectInputStream ficheroObjetos = new ObjectInputStream(ficheroSalida);
		Persona p = (Persona) ficheroObjetos.readObject();
		System.out.println(p.toString());
		p = (Persona) ficheroObjetos.readObject();
		System.out.println(p.toString());
		ficheroObjetos.close();
		}catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
