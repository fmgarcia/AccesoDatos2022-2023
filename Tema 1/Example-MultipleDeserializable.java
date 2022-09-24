package tema1_ad19_20;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DesSerializableConArrayList {
	static ArrayList<Persona> personas = new ArrayList<Persona>();
	static ArrayList<Persona> ret = new ArrayList<Persona>();
	
	public static void serialize() {
		personas.add(new Persona("Fran","a@a.com","01/01/2019"));
		personas.add(new Persona("Dani","b@b.com","02/01/2019"));
		try {
			FileOutputStream ficheroSalida = new FileOutputStream(new File("personasMasivo.dat"));
			ObjectOutputStream ficheroObjetos =
					 new ObjectOutputStream(ficheroSalida);
			ficheroObjetos.writeObject(personas);
            
			ficheroObjetos.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void desSerialize() {
		try {
			FileInputStream ficheroSalida = new FileInputStream(new File("personasMasivo.dat"));
			ObjectInputStream ficheroObjetos = new ObjectInputStream(ficheroSalida);
		
			ret = (ArrayList<Persona>)ficheroObjetos.readObject();
			
			// No habría que imprimir aquí. Es solo para comprobar que es correcto
			/*for(Persona p: ret) {
				System.out.println(p.toString());
			}*/
	        
			ficheroObjetos.close();
			}catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void buscar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escriba la palabra a buscar: ");
		String palabra = sc.nextLine();
		List<Persona> busqueda =ret.stream()
        .filter((f) -> f.getNombre().toLowerCase().contains(palabra.toLowerCase())
                || f.getMail().toLowerCase().contains(palabra.toLowerCase())
                || f.getFecha().toLowerCase().contains(palabra.toLowerCase()))
        .collect(Collectors.toList());
		for(Persona p: busqueda) {
			System.out.println(p.toString());
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Serializar
		serialize();		
		// DesSerializar
		desSerialize();
		// Buscar
		buscar();


	}

}
