import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

//Kevin Marín Romero
public class FileFragments {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size = 1048576;
		String fileNameIn = "pointillist.bmp";
		String fileNameOut = fileNameIn.substring(0, fileNameIn.lastIndexOf('.'));
		
		try {
			InputStream fileIn = new FileInputStream(new File(fileNameIn));
			byte[] buf = new byte[size];
			int total = 0;
			int i = 1;
			System.out.println("Wait it to fragment...");
			while((total = fileIn.read(buf, 0, size)) > 0) {
				if(total == size) {
					Files.write(Paths.get(fileNameOut + "." + i), buf);
				}
				else {
					byte[] aux = new byte[total];
					System.arraycopy(buf, 0, aux, 0, total);
					Files.write(Paths.get(fileNameOut + "." + i), aux);
				}
				System.out.println(fileNameOut + "." + i + " was created...");
				i++;
			}
			System.out.println("It's Ready");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
