// Adrián Navarro Gabino

/*
 * Create a program that asks the user for the name of a BMP file and says
 * if it is compressed (you should look for information on how the header
 * of a BMP is, which you should read as a block).
 */

import java.io.*;
import java.util.*;

public class Exercise0115
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = sc.nextLine();

        if (! (new File(filename)).exists() ) {
            System.out.println(filename + " not found");
            return;
        }
        try
        {
            FileInputStream file = new FileInputStream(
                new File(filename));

            char firstChar = (char) file.read();
            char secondChar = (char) file.read();
            
            if(firstChar == 'B' && secondChar == 'M')
            {
                System.out.println("It seems a valid BMP file");
                file.skip(28);
                int compressed1 = file.read();
                int compressed2 = file.read();
                int compressed3 = file.read();
                int compressed4 = file.read();
                if(compressed1 == 0 && compressed1 == 0 && compressed1 == 0 &&
                    compressed1 == 0)
                    System.out.println("It is NOT compressed");
                else
                    System.out.println("It is compressed");
            }
            else
                System.out.println("It isn't a BMP file");

            file.close();
        }
        catch(Exception e)
        {
            System.out.println( "There were some problems: " +
                e.getMessage() );
        }
    }
}
