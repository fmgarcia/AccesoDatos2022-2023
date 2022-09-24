package tema1_ad19_20;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
	import org.w3c.dom.NodeList;
	import org.w3c.dom.Node;
	import org.w3c.dom.Element;

public class ReadXmlDOM {
	    public static void main(String[] args){
	        try {
	            File inputFile = new File("asignaturas.xml");
	            DocumentBuilderFactory dbFactory 
	                = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(inputFile);
	            doc.getDocumentElement().normalize();
	            NodeList nList = doc.getElementsByTagName("asignatura");
	            
	            System.out.println("Buscando asignaturas de segundo..."); 
	            for (int temp = 0; temp < nList.getLength(); temp++) {
	                Node nNode = nList.item(temp);
	                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                    Element eElement = (Element) nNode;
	                    if(eElement.getElementsByTagName("curso")
	                            .item(0).getTextContent().equals("Segundo")) {
	                        System.out.println("Nombre: " 
	                            + eElement.getElementsByTagName("nombre")
	                            .item(0).getTextContent());
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


