package com.fran.programacionfuncional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	
	public static void partitionigBy() {
		// Final
		// Parte la lista original en dos sublistas. Una que cumple la condición de partición y otra que no.
		Map<Boolean,List<Usuario>> sublistas = usuarios.stream()
		.collect(Collectors.partitioningBy(e->e.getSueldo()>10000));
		
		// Los que ganan +10000
		System.out.println("Usuarios que ganan más de 10000 Euros:");
		sublistas.get(true).forEach(e->System.out.println(e));
		// Los que ganan -10000
		System.out.println("Usuarios que ganan más de 10000 Euros:");
		sublistas.get(false).forEach(e->System.out.println(e));	
	}
	
	public static void groupingBy() {
		// Agrupar por el elemento que pongamos. Tendremos tantas listas como elementos distintos
		Map<Character,List<Usuario>> letras = usuarios.stream()
				.collect(Collectors.groupingBy(e->new Character(e.getNombre().charAt(0))));
		// acceder a los elementos
		letras.get('P').forEach(e->System.out.println(e));
		System.out.println(letras.containsKey('A')?
				"Hay personas que empiezan por A":"No hay personas que empiezan por A");
		
		// Elementos desde posición incial a final en una lista
		usuarios.stream()
		.filter(e->e.getNombre().charAt(0)>='A' && e.getNombre().charAt(0)<='F')
		.forEach(e->System.out.println(e));
		
		// Elementos desde posición incial a final en un mapa
		for(int i = (int)'A';i<=(int)'F';i++) {
			if(letras.containsKey((char)i))
				letras.get((char)i).forEach(e->System.out.println(e));
		}		
	}
	
	public static void count() {
		long sueldoNegativo = usuarios.stream()
				.filter(e->e.getSueldo()<0)
				.count();
		System.out.println("Número de empleados con sueldo negativo: " + sueldoNegativo);
	}
	
	public static void skipYLimit() {
		// ambas son no finales
		// skip salta un número de resultados.
		// limit limita el número de elementos devueltos.
		
		// Los usuarios 3,4 y 5 que más ganan en la empresa
		List<Usuario> usuarios345 = usuarios.stream()
				.sorted(Comparator.comparingDouble(Usuario::getSueldo).reversed())
				.skip(2)
				.limit(3)
				.collect(Collectors.toList());
		usuarios345.forEach(e->System.out.println(e));
		
		// Solo mostrar
		usuarios.stream()
		.sorted(Comparator.comparingDouble(Usuario::getSueldo).reversed())
		.skip(2)
		.limit(3)
		.forEach(e->System.out.println(e));
	}
	
	public static void maxYmin() {
		// Max devuelve el máximo valor de un campo
		Optional<Usuario> masgana = usuarios.stream()
				.max(Comparator.comparingDouble(Usuario::getSueldo));
		if(masgana.isPresent())
			System.out.println(masgana.get().toString());
		
		Usuario menosgana = usuarios.stream()
				.min(Comparator.comparingDouble(Usuario::getSueldo))
				.orElse(new Usuario(1,"Fran",0));
		System.out.println(menosgana.toString());
		
	}
	
	public static void disctint() {
		// No final. Saca elementos diferentes.
		// Saca los id's diferentes porque coge el equals de la clase
		long idsdistintos = usuarios.stream()
				.distinct()
				.count();
		System.out.println("Número de usuarios distintos: "+ idsdistintos);
		
		long nombresdistintos = usuarios.stream()
				.map(e->e.getNombre())
				.distinct()
				.count();
		System.out.println("Número de usuarios con nombres distintos: "+ nombresdistintos);
		System.out.println("Los nombres distintos son: ");
		usuarios.stream()
		.map(e->e.getNombre())
		.distinct()
		.forEach(e->System.out.println(e));
	}
	
	public static void match() {
		// Finales. Devuelven booleanos
		// anyMatch. True si existe alguno que cumpla la condición
		// allMatch. True si todos cumplen la condición
		// noneMatch. True si ninguno cumple la condición
		
		// ¿Alguien gana más de 100000?
		boolean ganaMas100000 = usuarios.stream()
				.anyMatch(e->e.getSueldo()>100000);
		
		// ¿Todos ganan más de cero?
		boolean ganaMas0 = usuarios.stream()
				.allMatch(e->e.getSueldo()>0);
		
		// ¿Todos ganan más de cero?
		boolean ganaMas0b = usuarios.stream()
				.noneMatch(e->e.getSueldo()<0);
		
		System.out.println(ganaMas100000 + " " + ganaMas0 + " " + ganaMas0b);		
	}
	
	public static void offtopic1() {
		// Crea una lista de números con los valores inicial y final
		IntStream.rangeClosed(1, 10).forEach(e->System.out.println(e + "*7=" + (e*7)));		
	}
	
	public static void summarizingDouble() {
		// Final.
		// Recolecciona todas las estadísticas de un campo numérico
		DoubleSummaryStatistics estadisticas = usuarios.stream()
		.collect(Collectors.summarizingDouble(Usuario::getSueldo));
		System.out.println("Media: " + estadisticas.getAverage());
		System.out.println("Máximo: " + estadisticas.getMax());
		System.out.println("Míximo: " + estadisticas.getMin());
		System.out.println("Suma: " + estadisticas.getSum());
		System.out.println("Cuenta: " + estadisticas.getCount());
	}
	
	public static void reduce() {
		// Reduce : Reduce los datos que tengamos a un ÚNICO valor 
		double sumaSueldos = usuarios.stream()
				.mapToDouble(e->e.getSueldo())
				.reduce(1000, (a,b)->a*b);  // multiplica todos los sueldos tomando como valor inicial el del primer parámetro
				//.reduce(0,Double::sum);
		System.out.println(sumaSueldos);
	}
	
	public static void joining() {
		String nombresSeparados = usuarios.stream()
			.map(e->e.getNombre().toLowerCase())
			.distinct()
			.sorted()
			.collect(Collectors.joining(", ")).toString();
		System.out.println(nombresSeparados);
	}
	
	public static void parallelStream() {
		long tiempo1 = System.currentTimeMillis();
		usuarios.forEach(e->convertirMayusculas(e.getNombre()));
		long tiempo2 = System.currentTimeMillis();
		System.out.println("El tiempo es: " + (tiempo2-tiempo1));
		tiempo1 = System.currentTimeMillis();
		usuarios.parallelStream().forEach(e->convertirMayusculas(e.getNombre()));
		tiempo2 = System.currentTimeMillis();
		System.out.println("Tiempo en paralelo: " + (tiempo2-tiempo1));
	}
	
	private static String convertirMayusculas(String nombre) {
		try {
			Thread.sleep(1000); // Un segundo
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre.toUpperCase();
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
        //peek();
        //partitionigBy();
        //groupingBy();
        //count();
        //skipYLimit();
        //maxYmin();
        //disctint();
        //match();
        //offtopic1();
        //summarizingDouble();
        //reduce();
        //joining();
        parallelStream();
    }
}
