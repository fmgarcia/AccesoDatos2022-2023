import java.io.*;
import java.util.Date;
import java.util.Scanner;

//Jaime Francsico Rebollo Domínguez
public class InvertedFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the name of the file: ");
		String fileName = sc.nextLine();
		String text = "";
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(new File(fileName)));
			
			String line = null;
			while((line = reader.readLine()) != null) {
				text = line + "\n" + text; 
			}
			
			reader.close();
		} catch (IOException e) {
			System.out.println("There was a problem reading the file: "+ e + " Try again.");
		}
		
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter("inverted_" + fileName);
			
			printWriter.println(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			printWriter.close();
		}
	}

}
