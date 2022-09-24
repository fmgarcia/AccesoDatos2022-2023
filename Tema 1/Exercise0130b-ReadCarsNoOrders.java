// Adrián Navarro Gabino

/*
 * Create a program that shows the model of cars of a brand requested to the
 * user by screen, for the file "cars.json", using the Models API, the
 * Streaming API and the GSON API. To do this, it will take out a menu that
 * allows the user which of the 3 to choose API's to use and then the brand
 * that they want to search. The program will run until the user enters an
 * option to exit. In addition, the program must allow the user to put upper
 * or lower case letters and find the mark regardless of this. Sort the output
 * of the results by displacement.
 */

import java.io.*;
import java.nio.file.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.*;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import javax.json.Json;
import javax.json.stream.JsonParser;

public class Exercise0130
{
	public static void readModels(String inputBrand)
	{
		boolean carFound = false;
		
		Object obj;
		List<String> models = new ArrayList<>();
		try
        {
			
			obj = new JSONParser().parse(new FileReader("cars.json"));
			
			JSONObject jo = (JSONObject) obj;
			
			JSONObject cars = (JSONObject) jo.get("coches");
			
			JSONArray car = (JSONArray) cars.get("coche");
			
			Iterator itr1 = car.iterator();
			while(itr1.hasNext())
			{
				JSONObject c = (JSONObject)itr1.next();
				if(((String) c.get("marca")).equalsIgnoreCase(inputBrand))
				{
					carFound = true;
					System.out.println((String)c.get("modelo"));
				}
			}
		}
        catch (FileNotFoundException e) 
        {
			e.printStackTrace();
		}
        catch (IOException e) 
        {
			e.printStackTrace();
		}
        catch (ParseException e)
        {
			e.printStackTrace();
		}
		
		if(!carFound)
		{
			System.out.println("Cars not found");
		}
	}
	
	public static void readStreaming(String inputBrand)
	{
		boolean carFound = false;
		
		JsonParser parser;
		String brand = "";
		boolean isBrand = false;
		String model = "";
		boolean isModel = false;
		
		try
        {
			parser = Json.createParser(new FileReader("cars.json"));
			while (parser.hasNext()) 
            {
				JsonParser.Event event = parser.next();
				switch (event) {
					case KEY_NAME:
						switch(parser.getString())
						{
							case "marca":
								isBrand = true;
								break;
							case "modelo":
								isModel = true;
								break;
						}
						break;
					case VALUE_STRING:
						if(isBrand)
						{
							brand = parser.getString();
							isBrand = false;
						}
						else if(isModel)
						{
							model = parser.getString();
							isModel = false;
						}
						break;
				}
				
				if(!brand.equals("") && !model.contentEquals(""))
				{
					carFound = true;
					if(brand.equalsIgnoreCase(inputBrand))
						System.out.println(model);
					brand = "";
					model = "";
				}
			}
		}
        catch (FileNotFoundException e)
        {
		e.printStackTrace();
		}
		
		if(!carFound)
		{
			System.out.println("Cars not found");
		}
	}
	
	public static void readGSON(String inputBrand)
	{
		boolean carFound = false;
		
		String data = null;
		
		try
        {
            Path path = Paths.get("cars.json");
            data = Files.readAllLines(path).stream()
                                           .collect(Collectors.joining(""));
        }
        catch (IOException e)
        {
            System.out.println(
                "There were problems: " +
                e.getMessage() );
        }
		
		com.google.gson.JsonParser parser = new com.google.gson.JsonParser();

        com.google.gson.JsonObject gsonOb = parser.parse(data)
                                                  .getAsJsonObject();
		
        com.google.gson.JsonObject gsonOb2 = gsonOb.get("coches")  
                                                   .getAsJsonObject();
		
        com.google.gson.JsonArray gsonArr = gsonOb2.get("coche")
                                                   .getAsJsonArray();
		
		for (com.google.gson.JsonElement obj : gsonArr)
		{
			com.google.gson.JsonObject gsonObj = obj.getAsJsonObject();
			
			if(gsonObj.get("marca").getAsString().equalsIgnoreCase(inputBrand))
			{
				carFound = true;
				System.out.println(gsonObj.get("modelo").getAsString());
			}
		}
		
		if(!carFound)
		{
			System.out.println("Cars not found");
		}
	}
	
	public static int showMenu()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. Models API");
		System.out.println("2. Streaming API");
		System.out.println("3. GSON");
		System.out.println("0. Exit");
		System.out.print("Choose an option: ");
		return sc.nextInt();
	}
	
	public static void main(String[] args)
	{
		int option;
		String brand = null;
		
		do
		{
			option = showMenu();
			
			if(option != 0)
			{
				Scanner sc = new Scanner(System.in);
				System.out.print("Choose a brand: ");
				brand = sc.nextLine();
			}
			
			System.out.println();
			
			switch(option)
			{
				case 1: readModels(brand); break;
				case 2: readStreaming(brand); break;
				case 3: readGSON(brand); break;
				case 0: System.out.println("See you!"); break;
				default: System.out.println("Wrong option"); break;
			}
			System.out.println();
		}while(option != 0);
	}
}
