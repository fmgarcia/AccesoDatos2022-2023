// Exercise 01.02 - Sending text to a text file
// You must display the numbers 1, 3, 5, 7, 9, in different lines

// Javier Saorín Vidal.

import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Exercise0102a {

    public static void main(String[] args) {
        try {
            PrintWriter printWriter = new PrintWriter("numbers.txt");
            
            for (int i = 1; i <= 9; i+=2) {
                printWriter.println(i);
            }
            
            printWriter.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
