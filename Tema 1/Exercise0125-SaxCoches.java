// Gonzalo Martinez

// Crea un programa que muestre el modelo de los coches de marca Seat, para el 
// fichero "coches.xml", usando SAX

import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class ReadXml1 extends DefaultHandler{
 
    public void procesarXml(String marcaUsu){
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler manejadorEventos = new DefaultHandler(){

            String etiquetaActual = "";
            String contenido = "";
            String marca = "";
            String modelo = "";

            // Método que se llama al encontrar inicio de etiqueta: '<'
            public void startElement(String uri, String localName,
                    String qName, Attributes attributes) 
                    throws SAXException {
                
                etiquetaActual = qName;
            }

            // Obtiene los datos entre '<' y '>'
            public void characters(char ch[], int start, int length)
                    throws SAXException {

                contenido = new String(ch, start, length);
            }

            // Llamado al encontrar un fin de etiqueta: '>'
            public void endElement(String uri, String localName, String qName)
                    throws SAXException {
                
                if(!etiquetaActual.equals("")) {
                    if(etiquetaActual.equals("marca"))
                        marca = contenido;
                    if(etiquetaActual.equals("modelo"))
                        modelo = contenido;
                        
                    if(!marca.equals("") && !modelo.equals("")) {
                        if(marca.equalsIgnoreCase(marcaUsu))
                            System.out.println(modelo + "-" + marca);
                    }
                    
                    etiquetaActual = "";
                }
            }
        };

        // Cuerpo de la función: trata de analizar el fichero deseado
        // Llamará a startElement(), endElement() y character() 
        saxParser.parse("coches.xml", manejadorEventos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



public class Exercise0125 {
    public static void main(String args[]) {
        
        Scanner sc = new Scanner(System.in);
        ReadXml ficheroXml = new ReadXml();
        
        System.out.print("Marca de coche: ");
        String marca = sc.nextLine();
        
        ficheroXml.procesarXml(marca);
    }
}
