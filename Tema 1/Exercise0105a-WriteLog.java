package tema1_ad19_20;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Ejercicio5a {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("Escriba su oración: ");
		String sentence = sc.nextLine();

		PrintWriter printWriter = null;

		try {
			printWriter = new PrintWriter(
					new BufferedWriter(
							new FileWriter("log.txt", true)));
			Calendar date = Calendar.getInstance();
			DateFormat hourdateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			printWriter.println(hourdateFormat.format(date) + " " + sentence);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}

	}

}
