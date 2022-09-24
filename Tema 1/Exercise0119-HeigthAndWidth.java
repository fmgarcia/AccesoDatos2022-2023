package tema1_ad19_20;

import java.io.*;

public class Exercise19 {

	public static void main(String[] args) {

		String inputFilename = "welcome8.bmp";
		File file = new File(inputFilename);
		final int BUFFER_SIZE = 40;
		try {

			InputStream inputFile = new FileInputStream(new File(inputFilename));
			byte[] buf = new byte[BUFFER_SIZE];
			inputFile.read(buf, 0, BUFFER_SIZE);
			if ((char) buf[0] == 'B' && (char) buf[1] == 'M') {
				System.out.println("Es un BMP");
				int heigth = getIntLittleEndian(buf, 22);
				int width = getIntLittleEndian(buf, 18);
				System.out.println("Altura: " + heigth + ", Anchura: " + width);
			} else {
				System.out.println("No es un BMP");
			}
			inputFile.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO error" + e.getMessage());
		}
	}

	private static int getIntLittleEndian(byte[] a, int offs) {
		return (a[offs + 3] & 0xff) << 24 | 
				(a[offs + 2] & 0xff) << 16 | 
				(a[offs + 1] & 0xff) << 8 | 
				a[offs] & 0xff;
	}

}
