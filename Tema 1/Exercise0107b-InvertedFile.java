import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//Kevin Marín Romero
public class InvertedFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a name file: ");
		String fileName = sc.nextLine();
		List<String> lines = new ArrayList<>();
		try {
			BufferedReader fileIn = new BufferedReader(new FileReader(fileName));
			String line = null;
			while ((line = fileIn.readLine()) != null) {
				lines.add(line);
			}
			fileIn.close();
			Collections.reverse(lines);
			PrintWriter fileOut = new PrintWriter("inverse." + fileName);
			
			for(int i = 0; i < lines.size(); i++) {
				fileOut.println(lines.get(i));
			}
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
