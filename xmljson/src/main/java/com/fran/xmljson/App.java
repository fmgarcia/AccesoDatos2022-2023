package com.fran.xmljson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fran.xmljson.entidades.Film;
import com.fran.xmljson.entidades.Noticia;
import com.fran.xmljson.entidades.People;
import com.fran.xmljson.entidades.People2;
import com.fran.xmljson.entidades.Post;
import com.fran.xmljson.entidades.Tarea;
import com.fran.xmljson.utilidades.InternetUtils;
import com.fran.xmljson.utilidades.JsonUtils;
import com.fran.xmljson.utilidades.XmlUtils;

/**
 * Hello world!
 *
 */
public class App 
{
	final static String TOKEN_FOOTBALL_DATA = "6bff5b66e01940a4a4730dad785bbab3";
	
    public static void main( String[] args )
    {
        /*XmlUtils.procesarXmlSax("c:/ficheros", "asignaturas.xml").stream()
        	.forEach(a->System.out.println(a));*/
    	//XmlUtils.mostrarXmlDom("c:/ficheros/asignaturas.xml");
    	/*XmlUtils.procesarXmlDom("c:/ficheros", "asignaturas.xml").stream()
        	.forEach(a->System.out.println(a));*/
    	//System.out.println(InternetUtils.readUrl("https://e00-marca.uecdn.es/rss/portada.xml"));
    	/*InternetUtils.readUrlList("https://e00-marca.uecdn.es/rss/portada.xml").stream()
    	.forEach(l->System.out.println(l));*/
    	// create a formater
    	/*Noticia n = new Noticia("a","b","c",LocalDate.parse("Mon, 03 Oct 2022 05:13:05 +0200",DateTimeFormatter.RFC_1123_DATE_TIME));
    	System.out.println(n);*/
    	/*XmlUtils.procesarMarcaDom("https://e00-marca.uecdn.es/rss/portada.xml").stream()
    	.filter(n->n.getTitle().contains("Madrid") || n.getDescription().contains("Madrid"))
    	.forEach(n->System.out.println(n));*/
    	//JsonUtils.leerJsonDesdeFichero("c:/ficheros/profesor.json");
    	//JsonUtils.escribirJsonSimple();
    	//JsonUtils.leerTareasInternet("https://jsonplaceholder.typicode.com/todos");
    	/*JsonUtils.devolverTareasInternet("https://jsonplaceholder.typicode.com/todos").stream()
    		.filter(e->e.isCompleted()==false)
    		.forEach(e->System.out.println(e));*/
    	/*List<Tarea> tareas = JsonUtils.devolverArrayInternetGenerico("https://jsonplaceholder.typicode.com/todos");
    	tareas.stream()
			.filter(e->e.isCompleted()==false)
			.forEach(e->System.out.println(e));*/
    	//JsonUtils.gson1();
    	//JsonUtils.gson2();
    	//JsonUtils.leerTareasGson("https://jsonplaceholder.typicode.com/todos");
    	/*JsonUtils.devolverTareasGson("https://jsonplaceholder.typicode.com/todos").stream()
    	.filter(e->e.isCompleted()==false)
		.forEach(e->System.out.println(e));*/
    	/*JsonUtils.devolverArrayGsonGenerico("https://jsonplaceholder.typicode.com/todos",Tarea[].class).stream()
    	.filter(e->e.isCompleted()==false)
		.forEach(e->System.out.println(e));*/
    	//System.out.println(JsonUtils.devolverObjetoGsonGenerico("https://jsonplaceholder.typicode.com/posts/1",Post.class));
    	//System.out.println(JsonUtils.devolverObjetoGsonGenerico("https://swapi.dev/api/people/1/?format=json",People.class));		
    	// Obtener las url's de las películas para un personaje
    	/*JsonUtils.devolverObjetoGsonGenerico("https://swapi.dev/api/people/1/?format=json",People2.class)
    	.getFilms()
    	.forEach(e->System.out.println(e));*/
    	// Obtener el nombre de las películas para un personaje
    	/*JsonUtils.devolverObjetoGsonGenerico("https://swapi.dev/api/people/1/?format=json",People2.class)
    	.getFilms()
    	.forEach(e->System.out.println(JsonUtils.devolverObjetoGsonGenerico(e + "?format=json",Film.class).getTitle()));*/	
    	//System.out.println(InternetUtils.readUrl("https://api.openweathermap.org/data/2.5/weather?lat=38.3452&lon=-0.4815&appid=2ab9d0a33b34fdf58a7e0efc92c7ae57"));
    	//System.out.println(InternetUtils.readUrlList("https://api.openweathermap.org/data/2.5/weather?lat=38.3452&lon=-0.4815&appid=2ab9d0a33b34fdf58a7e0efc92c7ae57"));
    	//System.out.println(JsonUtils.crearJson(new Tarea(1,true,"Título de prueba",10)));
    	//System.out.println(JsonUtils.crearJsonPretty(new Tarea(1,true,"Título de prueba",10)));
    	//System.out.println(InternetUtils.readUrl("https://api.football-data.org/v4/teams/86/matches?status=SCHEDULED")); // falla porque no tiene el token
    	//System.out.println(InternetUtils.readUrl("https://api.football-data.org/v4/teams/86/matches?status=SCHEDULED",TOKEN_FOOTBALL_DATA)); 
    	InternetUtils.readUrlList("https://amanosupermercados.com/4-fruta-y-verdura?page=1").stream()
    	.filter(e->e.contains("itemprop"))
    	.forEach(e->System.out.println(e));

    	
    	
    }
}
