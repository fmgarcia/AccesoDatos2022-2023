package com.fran.xmljson.utilidades;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fran.xmljson.entidades.FootballPlayer;
import com.fran.xmljson.entidades.Tarea;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

	/**
	 * Ejemplo de librería json-simple con un fichero profesor.json que se encuentra
	 * en los apuntes del tema
	 * 
	 * @param cadenaCompleta
	 */
	public static void leerJsonDesdeFichero(String cadenaCompleta) {
		Object obj;
		try {
			// parseado el fichero "profesor.json"
			obj = new JSONParser().parse(new FileReader(cadenaCompleta));
			// casteando obj a JSONObject
			JSONObject jo = (JSONObject) obj;
			// cogiendo el nombre y el apellido
			String nombre = (String) jo.get("nombre");
			String apellido = (String) jo.get("apellido");
			System.out.println(nombre);
			System.out.println(apellido);
			// cogiendo la edad como long
			long edad = (long) jo.get("edad");
			System.out.println(edad);
			// cogiendo direccion
			Map domicilio = ((Map) jo.get("domicilio"));
			// iterando direccion Map
			Iterator<Map.Entry> itr1 = domicilio.entrySet().iterator();
			while (itr1.hasNext()) {
				Map.Entry pareja = itr1.next();
				System.out.println(pareja.getKey() + " : " + pareja.getValue());
			}
			// cogiendo números de teléfonos
			JSONArray ja = (JSONArray) jo.get("numerosTelefonos");
			// iterando números de teléfonos
			Iterator itr2 = ja.iterator();
			while (itr2.hasNext()) {
				itr1 = ((Map) itr2.next()).entrySet().iterator();
				while (itr1.hasNext()) {
					Map.Entry pareja = itr1.next();
					System.out.println(pareja.getKey() + " : " + pareja.getValue());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void leerTareasInternet(String url) {
		Object obj;
		try {
			obj = new JSONParser().parse(InternetUtils.readUrl(url));	
			// cogiendo el array como elemento principal
			JSONArray ja = (JSONArray) obj;
			// recorremos los elementos del array
			Iterator<?> elementos = ja.iterator();
			elementos.forEachRemaining(e -> {
				Iterator<Map.Entry> campos = ((Map) e).entrySet().iterator(); // recorremos los campos de cada elemento del array
				campos.forEachRemaining(campo -> System.out.println(campo.getKey() + ": " + campo.getValue()));
			});
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Tarea> devolverTareasInternet(String url) {
		Object obj;
		List<Tarea> resultado = new ArrayList<Tarea>();
		try {
			obj = new JSONParser().parse(InternetUtils.readUrl(url));	
			// cogiendo el array como elemento principal
			JSONArray ja = (JSONArray) obj;
			// recorremos los elementos del array
			Iterator<?> elementos = ja.iterator();
			elementos.forEachRemaining(e -> {
				JSONObject elementoObjeto = (JSONObject) e;
				resultado.add(new Tarea((long) elementoObjeto.get("id"),
						(boolean) elementoObjeto.get("completed"),
						(String) elementoObjeto.get("title"),
						(long) elementoObjeto.get("id")));
			});
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static <T> List<T> devolverArrayInternetGenerico(String url) {
		Object obj;
		List<T> resultado = new ArrayList<T>();
		try {
			obj = new JSONParser().parse(InternetUtils.readUrl(url));	
			// cogiendo el array como elemento principal
			JSONArray ja = (JSONArray) obj;
			// recorremos los elementos del array
			Iterator<?> elementos = ja.iterator();
			elementos.forEachRemaining(e -> {
				JSONObject elementoObjeto = (JSONObject) e;
				insertarElemento(resultado,elementoObjeto);
			});
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static <T> void insertarElemento(List<T> resultado, JSONObject elementoObjeto) {
		resultado.add((T)new Tarea((long) elementoObjeto.get("id"),
				(boolean) elementoObjeto.get("completed"),
				(String) elementoObjeto.get("title"),
				(long) elementoObjeto.get("id")));
	}

	public static void escribirJsonSimple() {
		// creando JSONObject
		JSONObject jo = new JSONObject();

		// poniendo los primeros datos en JSONObject
		jo.put("nombre", "Fran");
		jo.put("apellido", "Garcia");
		jo.put("edad", 25);

		// para la dirección primero hay que crear un LinkedHashMap
		Map m = new LinkedHashMap(4);
		m.put("direccion", "Lillo Juan, 128");
		m.put("ciudad", "San Vicente del Raspeig");
		m.put("comunidad", "Valenciana");
		m.put("codigoPostal", "03690");

		// domicilio a JSONObject
		jo.put("domicilio", m);

		// para los números de teléfono primero crear el JSONArray
		JSONArray ja = new JSONArray();

		m = new LinkedHashMap(2);
		m.put("tipo", "casa");
		m.put("numero", "666 666 666");
		// añadiendo a la lista
		ja.add(m);
		m = new LinkedHashMap(2);
		m.put("tipo", "movil");
		m.put("numero", "777 777 777");
		// añadiendo a la lista
		ja.add(m);

		// añadiendo los números de teléfono al JSONObject
		jo.put("numerosTelefonos", ja);

		// Escribiendo el:"profesor.json" in cwd
		PrintWriter pw;
		try {
			pw = new PrintWriter("JSONExample.json");
			pw.write(jo.toJSONString());
			pw.flush();
			pw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void gson1() {
		String json1 = "[{\"dorsal\":6," + "\"name\":\"Iniesta\","
				+ "\"demarcation\":[\"Right winger\",\"Midfielder\"]," + "\"team\":\"FC Barcelona\"}]";

		JsonParser parser = new JsonParser();

		// Obtain Array
		JsonArray gsonArr = parser.parse(json1).getAsJsonArray();

		// for each element of array
		for (JsonElement obj : gsonArr) {

			// Object of array
			JsonObject gsonObj = obj.getAsJsonObject();

			// Primitives elements of object
			int dorsal = gsonObj.get("dorsal").getAsInt();
			String name = gsonObj.get("name").getAsString();
			String team = gsonObj.get("team").getAsString();

			// List of primitive elements
			JsonArray demarcation = gsonObj.get("demarcation").getAsJsonArray();
			List listDemarcation = new ArrayList();
			for (JsonElement demarc : demarcation) {
				listDemarcation.add(demarc.getAsString());
			}

			// Object Constructor
			FootballPlayer iniesta = new FootballPlayer(dorsal, name, listDemarcation, team);
			System.out.println(iniesta);
		}

	}
}
