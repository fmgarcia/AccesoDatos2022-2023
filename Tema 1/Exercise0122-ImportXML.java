/*
 * N?stor Rosario Escolano
 * 18 / 10 / 2018
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Friend implements Serializable{
    protected String name;
    protected short age;
    protected String mail;
    protected String comment;
    
    public Friend(String name,short age,String mail,String comment) {
        this.name = name;
        this.age = age;
        this.mail = mail;
        this.comment = comment;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setAge(short age) {
        this.age = age;
    }
    
    public short getAge() {
        return age;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getMail() {
        return mail;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getComment() {
        return comment;
    }
    
    
    @Override
    public String toString() {
        return "Friend - Name: " + name + " , Age: " + age + 
                " , E-mail: " + mail + " , Comment: " + comment;
    }
}

// ---------------

public class Exercise0122 {
    static Scanner scanner = new Scanner(System.in);
    
    public static ArrayList<Friend> deserializeFriendList(){
        ArrayList<Friend> ret = new ArrayList<Friend>();

        if(!(new File("friends.dat").exists())) {
            return new ArrayList<Friend>();
        }
        
        try {
            ObjectInputStream objectFile = 
                    new ObjectInputStream(new FileInputStream(
                    new File("friends.dat")));
            
            ret = (ArrayList<Friend>)objectFile.readObject();
            
            objectFile.close();
        }
        catch(FileNotFoundException e) {
            System.err.println("Error " + e.getMessage());
        }
        catch(ClassNotFoundException e) {
            System.err.println("Error " + e.getMessage());
        }
        catch(IOException e) { 
            System.err.println("Error " + e.getMessage());
        }
        
        return (ret != null) ? ret : new ArrayList<Friend>();
    }
    

    public static void serializeFriendList(ArrayList<Friend> friends) {
        try {
            ObjectOutputStream objectFile = 
                    new ObjectOutputStream(new FileOutputStream
                    (new File("friends.dat")));
            
            objectFile.writeObject(friends);
            
            objectFile.close();
        }
        catch(FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
        catch(IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    
    public static boolean tryParseInt(String number) {
        try {
            Integer.parseInt(number);
            return true;
        }
        catch(Exception e) {}
        
        return false;
    }

    
    public static void addFriend(ArrayList<Friend> friends) {
        System.out.println("Friend " + (friends.size() + 1) + ".");
        
        String name = "";
        do {
            System.out.println("Enter the name: ");
            name = scanner.nextLine();
            
            if(name.equals(""))
                System.out.println("The name cannot be empty.");
        }while(name.equals(""));
        
        int age = 0;
        
        do {
            System.out.println("Enter the age: ");
            String strAge = scanner.nextLine();
            
            if(tryParseInt(strAge)) {
                age = Integer.parseInt(strAge);
                if(age < 1 || age > 105)
                    System.out.println("The age must be between 1 and 105.");
            }
            else {
                System.out.println("The age must be a number.");
                age = 0;
            }
        }while(age < 1 || age > 105);
        
        System.out.println("Enter the e-mail: ");
        String mail = scanner.nextLine();
        
        System.out.println("Enter the comment: ");
        String comment = scanner.nextLine();
        
        friends.add(new Friend(name, (short)age, mail, comment));
        
        System.out.println("Friend added successfully.");
    }
    
    public static void showAllNames(ArrayList<Friend> friends) {
        friends.forEach((friend) -> {
            System.out.println("Name : " + friend.getName());
        });
    }
    
    public static void searchFriend(ArrayList<Friend> friends) {
        System.out.println("Enter the key words: ");
        String keyWords = scanner.nextLine().toLowerCase();
        
        List<Friend> friendsFound = friends.stream()
                .filter((f) -> f.getName().toLowerCase().contains(keyWords)
                        || f.getMail().toLowerCase().contains(keyWords)
                        || f.getComment().toLowerCase().contains(keyWords))
                .collect(Collectors.toList());
        
        if(friendsFound.size() == 0)
            System.out.println("Friends not found.");
        else
            friendsFound.forEach(System.out::println);
    }
    
    public static void exportXML(ArrayList<Friend> friends) {
        
        PrintWriter printWriter = null;
        
        try {
            printWriter = new PrintWriter("friends.xml");
            
            printWriter.println("<friends>");
            
            for(Friend friend : friends) {
                printWriter.println("\t<friend>");
                
                printWriter.println("\t\t<name>" + 
                        friend.getName() + "</name>");
                printWriter.println("\t\t<age>" + 
                        friend.getAge() + "</age>");
                printWriter.println("\t\t<email>" + 
                        friend.getMail() + "</email>");
                printWriter.println("\t\t<comments>" +
                        friend.getComment() + "</comments>");
                
                printWriter.println("\t</friend>");
            }
            
            printWriter.println("</friends>");
            
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
            
        }
        finally {
            if(printWriter != null)
                printWriter.close();
        }
        
    }
    
    public static void readXML(ArrayList<Friend> friends) {
        
        int lineNumber, indexOpenAngle, indexCloseAngle;
        boolean dataFound = false, insideLabel = false;
        String line, label = "", endLabel = "";
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader bufferedReader = null;
        
        friends.clear();
        
        try {
            
            bufferedReader = new BufferedReader(new FileReader(
                    new File("friends.xml")));
            
            line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line.trim());
                line = bufferedReader.readLine();
            }
            
            if(lines.size() > 0)
                dataFound = true;
            
            bufferedReader.close();
            
        } catch (IOException ioe) {
            dataFound = false;
            System.out.println("File not found");
        }
        
        if(dataFound) {
            
            lineNumber = 1;
            while(lineNumber<lines.size()-1) {
                
                if(!insideLabel) {
                    label = lines.get(lineNumber);
                    endLabel = "</" + label.substring(1, label.length());
                    insideLabel = true;
                }
                else if(lines.get(lineNumber).equals(endLabel)) {
                    insideLabel = false;
                }
                else {
                    
                    short age;
                    String name, email, comment;
                    
                    indexOpenAngle = lines.get(lineNumber).indexOf(">")+1;
                    indexCloseAngle = lines.get(lineNumber).lastIndexOf("</");
                    name = lines.get(lineNumber)
                            .substring(indexOpenAngle, indexCloseAngle);
                    
                    indexOpenAngle = lines.get(++lineNumber).indexOf(">")+1;
                    indexCloseAngle = lines.get(lineNumber).lastIndexOf("</");
                    age = Short.parseShort(lines.get(lineNumber)
                            .substring(indexOpenAngle, indexCloseAngle));
                    
                    indexOpenAngle = lines.get(++lineNumber).indexOf(">")+1;
                    indexCloseAngle = lines.get(lineNumber).lastIndexOf("</");
                    email = lines.get(lineNumber)
                            .substring(indexOpenAngle, indexCloseAngle);
                    
                    indexOpenAngle = lines.get(++lineNumber).indexOf(">")+1;
                    indexCloseAngle = lines.get(lineNumber).lastIndexOf("</");
                    comment = lines.get(lineNumber)
                            .substring(indexOpenAngle, indexCloseAngle);
                    
                    friends.add(new Friend(name, age, email, comment));
                    
                }
                
                lineNumber++;
            }
            
        }
        
    }

    
    public static void printMenu() {
        System.out.println("1- Add friend.");
        System.out.println("2- Show friend names.");
        System.out.println("3- Search.");
        System.out.println("4- Export to XML file");
        System.out.println("5- Import from XML");
        System.out.println("0- Exit.");
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String option = "";
        
        ArrayList<Friend> friends = deserializeFriendList();
        
        do {
            printMenu();
            option = sc.nextLine();
            
            switch(option) {
                case "1":
                    addFriend(friends);
                    friends.sort((f1,f2) 
                        -> f1.getName().compareTo(f2.getName()));
                    serializeFriendList(friends);
                    break;
                    
                case "2":
                    showAllNames(friends);
                    break;
                    
                case "3":
                    searchFriend(friends);
                    break;
                    
                case "4":
                    exportXML(friends);
                    break;
                    
                case "5":
                    readXML(friends);
                    serializeFriendList(friends);
                    break;
                    
                case "0":
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Unknown option.");
                    break;
            }
        }while(!option.equals("0"));
        scanner.close();
    }
}