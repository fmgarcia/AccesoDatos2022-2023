//Pablo Sánchez Alonso
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CopiarBinario
{
    public static void main(String[] args) 
    {
    	Scanner sc = new Scanner(System.in);

    	try
    	{
    		File fichero = new File("welcome8.bmp");
	    	InputStream ficheroEntrada = new FileInputStream(fichero);
	    	int tamanyo = (int)fichero.length();
	    	byte[] buf = new byte[tamanyo];
	    	
	    	OutputStream ficheroSalida = new FileOutputStream(
	    			new File("fichero2.bmp"));

	    	ficheroEntrada.read(buf, 0, tamanyo);
	    	
	    	ficheroSalida.write(buf, 0, tamanyo);;
	    	
	    	ficheroEntrada.close();
	    	ficheroSalida.close();

    	}
    	catch (Exception errorDeFichero)
    	{
	    	System.out.println(
	    	"Ha habido problemas: " +
	    	errorDeFichero.getMessage() );
    	}
    	System.out.println("Terminado!");
    }
}
