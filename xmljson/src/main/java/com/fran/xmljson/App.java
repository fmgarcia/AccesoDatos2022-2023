package com.fran.xmljson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fran.xmljson.entidades.Noticia;
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
    	JsonUtils.devolverGsonGenerico("https://jsonplaceholder.typicode.com/todos",Tarea[].class).stream()
    	.filter(e->e.isCompleted()==false)
		.forEach(e->System.out.println(e));
    			
    }
}
