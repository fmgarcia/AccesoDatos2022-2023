import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//Kevin Marín Romero
public class VowelsCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int vowelsCount = 0;
		char[] vowels = {'a','e','i','o','u','A','E','I','O','U'};
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a name file: ");
		String fileName = sc.nextLine();
		try {
			BufferedReader fileIn = new BufferedReader(new FileReader(fileName));
			String line = null;
			while((line = fileIn.readLine()) != null) {
				for(int i = 0; i < line.length(); i++) {
					for(int j = 0; j < vowels.length; j++) {
						if(line.charAt(i) == vowels[j]) {
							vowelsCount++;
						}
					}
				}
			}
			System.out.println("The file: " + "\"" + fileName + "\"" + 
					" have " + vowelsCount + " vowels");
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
