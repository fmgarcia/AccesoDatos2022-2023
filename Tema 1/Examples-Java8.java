package tema1_ad19_20;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8 {
	static ArrayList<Persona> personas = new ArrayList<Persona>();

	public static void cargarDatos() {
		personas.add(new Persona("Fran","a@iessanvicente.com","01/01/2019",20));
		personas.add(new Persona("Fran","b@iessanvicente.com","01/01/2018",40));
		personas.add(new Persona("Dani","c@iessanvicente.com","01/01/2017",50));
		personas.add(new Persona("Arturo","a@iessanvicente.com","01/04/2016",19));
		personas.add(new Persona("Fernando","a@iessanvicente.com","05/02/2019",38));
		personas.add(new Persona("Antonio","a@iessanvicente.com","01/01/2015",46));
		personas.add(new Persona("José","a@iessanvicente.com","01/01/2016",12));
		personas.add(new Persona("Fran","c@iessanvicente.com","01/01/2017",19));
		
		
	}
	
	public static void streams() {
		Stream<Persona> stream;
		// Filtrar datos e imprimir
		stream = personas.stream();
		stream.filter(p->p.getNombre().equals("Fran") && 
				p.getMail().equals("a@iessanvicente.com"))
		.forEach(p -> System.out.println(p.toString()));
		// Ya no utilizamos la clase Stream
		// sino directarmente el ArrayList
		// Filtrar datos, contar e imprimir		
		long numeroPersonas = personas.stream()
				.filter(p->p.getNombre().equals("Fran") && 
				p.getMail().equals("a@iessanvicente.com"))
				.count();
		System.out.println("las personas son: " + numeroPersonas);
		System.out.println("las personas son: " + personas.stream()
		.filter(p->p.getNombre().equals("Fran") && 
		p.getMail().equals("a@iessanvicente.com"))
		.count());
		// Ordenar e imprimir
		personas.stream()
		.sorted((p,q)->p.getNombre().compareTo(q.getNombre()))
		.forEach(p -> System.out.println(p.toString()));
		// Ordenar, asignar a otra lista e imprimir la nueva lista
		List<Persona> personasOrdenadas=personas.stream()
				.sorted((p,q)->p.getNombre().compareTo(q.getNombre()))
				.collect(Collectors.toList());	// Crea una lista a partir de otra
		for(Persona p: personasOrdenadas) {
			System.out.println(p.toString());
		}
		
		// Funciones matemáticas
		
		Optional<Integer> sueldoMayor = personas.stream()
				.map(person->person.getSueldo())
				.reduce(Integer::max);
		System.out.println("Sueldo mayor es: " + sueldoMayor.get());
		int sueldoMayorB = personas.stream()
				.map(person->person.getSueldo())
				.reduce(0,Integer::max);
		System.out.println("Sueldo mayorB es: " + sueldoMayorB);
		Optional<Integer> sueldoMenor = personas.stream()
				.filter(p->p.getSueldo()!=0)
				.map(person->person.getSueldo())
				.min((p,q)->p.compareTo(q));
		System.out.println("Sueldo menor es: " + sueldoMenor.get());
		
		// Funciones booleanas
		boolean ganaMas60 = personas.stream()
				.anyMatch(p->p.getSueldo()>60);
		System.out.println(ganaMas60);
		boolean ganaMas30 = personas.stream()
				.anyMatch(p->p.getSueldo()>30);
		System.out.println(ganaMas30);
		boolean todosganaMas60 = personas.stream()
				.allMatch(p->p.getSueldo()>60);
		boolean ningunoganaMas60 = personas.stream()
				.noneMatch(p->p.getSueldo()>60);
		
	}
	
	public static void constructorCopia() {
		// Como funciona el constructor de copia
				Persona p1 = new Persona("Fran","a@iessanvicente.com","01/01/2019");
				Persona q1 = p1; // Esto no crea una nueva persona
				Persona nueva = new Persona(p1);  // Esto crea una nueva persona
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cargarDatos();	// Relleno el ArrayList original
		constructorCopia();
		streams();	
		
	}

}
