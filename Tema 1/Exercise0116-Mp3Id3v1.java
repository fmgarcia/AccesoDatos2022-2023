//Adrián Navarro Gabino

/*
 * Create a program that displays information contained in the tags of an MP3
 * file with ID3 header version 1.
 */

import java.io.*;
import java.util.*;

class Exercise0116
{
    public static void main( String[] args )
    {
        int headerSize = 128;

        Scanner sc = new Scanner(System.in);
        System.out.print("Filename: ");
        String filename = sc.nextLine();

        if (! (new File(filename)).exists() ) {
            System.out.println(filename + " not found");
            return;
        }

        try
        {
            File auxFilename = new File(filename);
            int size = (int)auxFilename.length();
            InputStream file =
                new FileInputStream(auxFilename);
            
            final int BUFFER_SIZE = size;
            
            byte[] buf = new byte[BUFFER_SIZE];
            file.read(buf, 0, BUFFER_SIZE);

            if((char)buf[buf.length - 128] == 'T' &&
                (char)buf[buf.length - 127] == 'A' &&
                (char)buf[buf.length - 126] == 'G')
            {
                System.out.print("Title: ");
                for(int i = buf.length - 125; i < buf.length - 95; i++)
                {
                    System.out.print((char)buf[i]);
                }
                System.out.println();
                
                System.out.print("Artist: ");
                for(int i = buf.length - 95; i < buf.length - 65; i++)
                {
                    System.out.print((char)buf[i]);
                }
                System.out.println();

                System.out.print("Album: ");
                for(int i = buf.length - 65; i < buf.length - 35; i++)
                {
                    System.out.print((char)buf[i]);
                }
                System.out.println();

                System.out.print("Year: ");
                for(int i = buf.length - 35; i < buf.length - 31; i++)
                {
                    System.out.print((char)buf[i]);
                }
                System.out.println();

                System.out.print("Genre: ");
                System.out.println((char)buf[buf.length - 31]);
            }
            else
            {
                System.out.println("It is NOT a valid MP3 file");
            }
            
            file.close();
        }
        catch (Exception e)
        {
            System.out.println("There were some problems: " + e.getMessage());
        }
    }

    public static int readLittleEndianInteger(byte[] buffer)
                                          throws IOException {
        int result = (buffer[3] << 24) | (buffer[2] << 16) |
                    (buffer[1] << 8)  | buffer[0];
        return result;
    }
}
