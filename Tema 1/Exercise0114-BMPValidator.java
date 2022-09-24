// María González Martínez

import java.io.*;
import java.util.Scanner;

public class BMPValidator 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
	    String name;
	    FileInputStream ficheroBMP;
	      
		if(args.length != 0)
		{
			name = args[0];
		}
		else
		{
			System.out.print("Enter file name: ");
			name = sc.nextLine();
		}
		
		try
		{
			ficheroBMP = new FileInputStream(new File(name));
			int dato1 = ficheroBMP.read();
			
			if(dato1 != 'B')
			{
				System.out.println("Isn't a valid BMP");
				return;
			}
			else
			{
				int dato2 = ficheroBMP.read();
				if(dato2 != 'M')
				{
					System.out.println("Isn't a valid BMP");
					return;
				}
				else
				{
					System.out.println("Is a valid BMP");
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		sc.close();
	}
}
