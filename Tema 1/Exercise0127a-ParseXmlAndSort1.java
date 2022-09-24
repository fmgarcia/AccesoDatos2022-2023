/*
 * Néstor Rosario Escolano
 * 25 / 10 / 2018
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

class Coche {
    
    private int cilindrada;
    private String modelo;
    
    public Coche(int cilindrada, String modelo) {
        this.cilindrada = cilindrada;
        this.modelo = modelo.equals("") ? "(Desconocido)" : modelo;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public String getModelo() {
        return modelo;
    }
    
    @Override
    public String toString() {
        return "Modelo: " + modelo + " (" + cilindrada + ")";
    }
    
}

public class Exercise0127 {

    public static void main(String[] args) {
        
        String fileName = "coches.xml";
        String buscarMarca;
        Scanner scn = new Scanner(System.in);
        ArrayList<Coche> list = new ArrayList<>();
        
        System.out.print("Introduce la marca a buscar: ");
        buscarMarca = scn.nextLine();
        
        try {
            
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("coche");
            System.out.println();
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                
                Node nNode = nList.item(temp);
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    
                    Element eElement = (Element) nNode;
                    
                    if(eElement.getElementsByTagName("marca")
                        .item(0).getTextContent()
                        .equalsIgnoreCase(buscarMarca)) {
                        
                        list.add(new Coche(Integer.parseInt(eElement
                                .getElementsByTagName("cilindrada").item(0)
                                .getTextContent()),
                                eElement.getElementsByTagName("modelo").item(0)
                                .getTextContent()));
                    }
                    
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Comparator<Coche> comp = (Coche c1, Coche c2) -> {
            if(c2.getCilindrada() == c1.getCilindrada())
                return c2.getModelo().compareTo(c1.getModelo());
            
            return c2.getCilindrada() - c1.getCilindrada();
        };
        
        list.stream()
            .sorted(comp)
            .forEach(System.out::println);

    }
}
