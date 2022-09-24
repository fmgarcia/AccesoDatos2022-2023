// Hollow rectangle + "finally"
// Adrián Fernández Arnal

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise0104a {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        
        System.out.print("Width? ");
        int width = s.nextInt();
        System.out.print("Height? ");
        int height = s.nextInt();
        s.close();
        
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("rectangle.txt");
            for(int i=1;i<=height;i++) {
                for(int j=1;j<=width;j++){
                    if(i==1 || i==height || j==1 || j==width)
                        printWriter.print("*");
                    else 
                        printWriter.print(" ");
                }
                printWriter.println();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            if(printWriter != null) {printWriter.close();}
        }
    }
}
