//Sergio Ruescas
import java.util.*;
import java.io.*;

public class addTexts{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstFileName, secondFileName, fileNameWrite;
        PrintWriter fileWrite = null;
        BufferedReader firstFileRead, secondFileRead;
        List<String> texts = new ArrayList<String>();

        if(args.length == 3){
            firstFileName = args[0];
            secondFileName = args[1];
            fileNameWrite = args[2];
        } else if(args.length == 2){
            firstFileName = args[0];
            secondFileName = args[1];
            System.out.print("Enter output file: ");
            fileNameWrite = sc.nextLine();
        } else{
            System.out.print("Enter first file name: ");
            firstFileName = sc.nextLine();
            System.out.print("Enter second file name: ");
            secondFileName = sc.nextLine();
            System.out.print("Enter output file: ");
            fileNameWrite = sc.nextLine();
        }

        if(new File(firstFileName).exists() && new File(secondFileName).exists()){
            try{
                firstFileRead = new BufferedReader(new FileReader(new File(firstFileName)));
                secondFileRead = new BufferedReader(new FileReader(new File(secondFileName)));
                String line;

                while((line = firstFileRead.readLine()) != null){
                    texts.add(line);
                }
                firstFileRead.close();

                while((line = secondFileRead.readLine()) != null){
                    texts.add(line);
                }
                secondFileRead.close();
                
                fileWrite = new PrintWriter(fileNameWrite);
                Collections.sort(texts);
                for(int i = 0; i < texts.size() ; i++){
                    fileWrite.println(texts.get(i));
                }
            } catch(IOException e){
                e.printStackTrace();
            } finally{
                fileWrite.close();
            }
        }


    }
}