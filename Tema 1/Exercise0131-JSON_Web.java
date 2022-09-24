// Adrián Navarro Gabino

/*
 * Given a URL that offers data in JSON format such as:
 *
 * https://jsonplaceholder.typicode.com/todos
 *
 * Investigate how to access that data directly from the web, to do so use
 * the Java API of the views you prefer to read the data from that URL, and
 * load in an ArrayList of objects of type User (class you must create), of
 * those users whose "userId" field is an even number and the "completed"
 * field has a value of true.
 *
 * Subsequently it takes the results of that ArrayList and serialize it.
 */

package com.adriannavarrogabino.Exercise0131;

import java.io.*;
import java.net.URL;
import java.util.*;

import com.google.gson.*;

class Task implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8429849269665202904L;
	private int userId;
	private int id;
	private String title;
	private boolean completed;
	
	public Task(int userId, int id, String title, boolean completed)
	{
		super();
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.completed = completed;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public boolean isCompleted()
	{
		return completed;
	}

	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}

	@Override
	public String toString()
	{
		return "Task [userId=" + userId + ", id=" + id + ", title=" + title +
				", completed=" + completed + "]";
	}
	
	
}

public class Exercise0131 
{	
	public static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
	public static void serializeTasks(List<Task> tasks)
	{
		try
		{
			ObjectOutputStream objectFile = new ObjectOutputStream(
					new ObjectOutputStream(new FileOutputStream("tasks.dat")));
			objectFile.writeObject(tasks);
			objectFile.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static List<Task> readFromJson()
	{
		try
		{
			String json = readUrl("https://jsonplaceholder.typicode.com/todos");
			
			Gson gson = new Gson();        
			Task[] tasksAux = gson.fromJson(json, Task[].class);
			
			List<Task> tasks = new ArrayList<Task>();
			
			for(Task t: tasksAux)
			{
				if(t.getUserId() % 2 == 0 && t.isCompleted())
				{
					tasks.add(t);
				}
			}
			
			return tasks;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
    public static void main( String[] args ) throws Exception
    {
    	List<Task> tasks = readFromJson();
		
		tasks.stream().forEach(System.out::println);
		
		serializeTasks(tasks);
    }
}
