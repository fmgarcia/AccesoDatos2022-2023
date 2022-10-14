package com.fran.programacionfuncional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

import com.fran.programacionfuncional.entidades.Usuario;

/**
 * Hello world!
 *
 */
public class App 
{
	static List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public static void tearUp() {
		usuarios.clear();
		usuarios.add(new Usuario(1,"Fran",25000.50));
		usuarios.add(new Usuario(2,"Dani",15000));
		usuarios.add(new Usuario(3,"Paco",-8000));
		usuarios.add(new Usuario(4,"Consu",51000));
		usuarios.add(new Usuario(1,"Francisco",2000));
		usuarios.add(new Usuario(5,"Pablo",30000));
		usuarios.add(new Usuario(6,"Javi",25000.50));
	}
	
	public static void tearDown() {
		usuarios.clear();
	}
	
	public static void forEach() {
		// Final
		// 0 forma
		for (int i = 0; i < usuarios.size(); i++) {
			System.out.println(i + usuarios.get(i).toString());
		}
		
		// 1 forma
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
		
		// 2 forma
		usuarios.forEach(e->System.out.println(e));
		
		// 3 forma
		usuarios.forEach(System.out::println);
		
		// 4 forma
		usuarios.stream().forEach(e->System.out.println(e));
		
		// anexo
		usuarios.stream().forEach(e->{
			System.out.println(e.getNombre());
			System.out.println(e.getSueldo());
		});
		
	}

	public static void filter() {
		// No final
	
		// imprime los usuarios cuyo nombre tenga más de 4 letras
		usuarios.stream()
		.filter(e->e.getNombre().length()>4)
		.forEach(e->System.out.println(e));
		
		// crea una lista con aquellos usuarios cuyo sueldo esté entre 20000 y 30000
		List<Usuario> sueldosMedios = usuarios.stream()
		.filter(e->e.getSueldo()>=20000)
		.filter(e->e.getSueldo()<=30000)
		.collect(Collectors.toList());  // Crea una lista nueva con los elementos
		
		System.out.println("Los usuarios de sueldos medios son: ");
		sueldosMedios.forEach(e->System.out.println(e));
		
	}
	
	public static void map() {
		// No final. A partir de una lista se queda con una parte del objeto.
		List<String> nombres = usuarios.stream()
		.map(e->e.getNombre())
		.collect(Collectors.toList());
		nombres.forEach(e->System.out.println(e));
		
		// Lista de los nombres de aquellos usuarios que ganan +20000 sin nombres repetidos
		Set<String> nombres2 = usuarios.stream()
				.filter(e->e.getSueldo()>20000)
				.map(e->e.getNombre())
				.collect(Collectors.toSet());
		nombres2.forEach(e->System.out.println(e));
	}
	
	public static void sumAvg() {
		double totalSueldos = usuarios.stream()
				.mapToDouble(e->e.getSueldo())
				.sum();
		System.out.println("Suma: " + totalSueldos);
		
		// 1 ejemplo de optional
		OptionalDouble mediaSueldos = usuarios.stream()
				.mapToDouble(e->e.getSueldo())
				.average();
		if(mediaSueldos.isPresent()) {
			System.out.println("Media: " +mediaSueldos.getAsDouble());
		}
		
		// 2 ejemplo de optional (evitar trabajar con los Optional)
		double mediaSueldos2 = usuarios.stream()
				.mapToDouble(e->e.getSueldo())
				.average()
				.orElse(0);  // valor x defecto
		System.out.println("Media: " + mediaSueldos2);
	}
	
	public static void find() {
		// Busca un elemento dentro de la lista
		// final con optional, no final con orElse
		Usuario usuario = usuarios.stream()
				.filter(e->e.getSueldo()>=200000)
				.findAny()
				.orElse(new Usuario(0,"No existe",0));
		System.out.println(usuario);
		
		Optional<Usuario> usuario2 = usuarios.stream()
				.filter(e->e.getSueldo()>=30000)
				.findFirst();
		if(usuario2.isPresent())
			System.out.println(usuario2.get().toString());
	}
	
	public static void flatMap() {
		// A partir de una lista de listas las fusiona
		// FlatMap: Coge una lista de listas y las concatena en una lista única. Pasa de dos dimensiones a una  [][] -> []
		List<String> alumnosDam = new ArrayList<String>(Arrays.asList("Fran","Paco","Dani"));
		List<String> alumnosDaw = new ArrayList<String>(Arrays.asList("Adrián","Federico","Lorena"));
		List<List<String>> alumnos = new ArrayList<List<String>>(Arrays.asList(alumnosDam,alumnosDaw));
		
		/*List<String> todosAlumnos = new ArrayList<String>(alumnosDam);
		todosAlumnos.addAll(alumnosDaw);*/
		alumnos.stream()
		.filter(e->e.size()>2)
		.flatMap(e->e.stream())  // junta las listas
		.sorted()
		.forEach(e->System.out.println(e));		
	}
	
	
	public static void peek() {
		// No final. Igual que el forEach pero no final
		// Subir el sueldo a aquellos usuarios que ganen menos de 20000 y crear una nueva lista con esos datos
		List<Usuario> pobres = usuarios.stream()
		.filter(e->e.getSueldo()<20000)
		.peek(e->e.setSueldo(e.getSueldo()+1000))
		.collect(Collectors.toList());
		pobres.forEach(e->System.out.println(e));
		
	}
	
    public static void main( String[] args )
    {
        tearUp();
        //forEach();
        //filter();
        //map();
        //sumAvg();
        //find();
        //flatMap();
        peek();
    }
}
