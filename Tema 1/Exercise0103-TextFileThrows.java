// Triangle of asteriks + "throws"
// Samuel Aldegunde López 2º DAM

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise0103 {

    public static void main(String[] args) 
        throws FileNotFoundException 
    {
        System.out.print("How many asterisks? ");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        PrintWriter printWriter = new PrintWriter ("triangle.txt");
        for (int row = 1; row <= number; row++)
        {
            for(int column = 1; column <= row; column++)
            {
                printWriter.print("*");
            }
            printWriter.println();
        }
        printWriter.close();
    }
}
