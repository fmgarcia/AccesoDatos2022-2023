// Gonzalo Martinez

// Crea un programa que muestre el modelo de los coches de marca Seat, para el 
// fichero "coches.xml", usando DOM

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Exercise0126 {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);

        try {
            File inputFile = new File("coches.xml");
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("coche");
            
            // Introduccion de marca
            System.out.print("Marca de coche: ");
            String marcaUsu = sc.nextLine();
            System.out.println();
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if(eElement.getElementsByTagName("marca").
                            item(0).getTextContent().equalsIgnoreCase(marcaUsu))
                    {
                            System.out.println("Modelo: " 
                                + eElement.getElementsByTagName("modelo")
                                .item(0).getTextContent());
                            System.out.println(); 
                    }  
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
