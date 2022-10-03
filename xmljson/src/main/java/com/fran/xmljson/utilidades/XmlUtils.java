package com.fran.xmljson.utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.fran.xmljson.entidades.Asignatura;

public class XmlUtils {

	public static List<Asignatura> procesarXmlSax(String directorio, String nombreArchivo) {
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			DefaultHandler manejadorEventos = new DefaultHandler() {
				String etiquetaActual = "";
				String contenido = "";
				Asignatura nueva;

				// Método que se llama al encontrar inicio de etiqueta: '<'
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					// Si el nombre es "asignatura",
					// empieza una nueva y mostramos su id
					// Si no, memorizamos el nombre para mostrar después
					etiquetaActual = qName;
					if (etiquetaActual.equals("asignatura")) {
						//System.out.println("Asignatura: " + attributes.getValue("id"));
						nueva = new Asignatura(attributes.getValue("id"));
					}

				}

				// Obtiene los datos entre '<' y '>'
				public void characters(char ch[], int start, int length) throws SAXException {
					contenido = new String(ch, start, length);
				}

				// Llamado al encontrar un fin de etiqueta: '>'
				public void endElement(String uri, String localName, String qName) throws SAXException {
					/*if (etiquetaActual != "") {
						System.out.println(" " + etiquetaActual + ": " + contenido);
						etiquetaActual = "";
					}*/	
					//System.out.println(qName);
					if(qName.equals("nombre"))
						nueva.setNombre(contenido);
					if(qName.equals("cicloFormativo"))
						nueva.setCicloFormativo(contenido);
					if(qName.equals("curso"))
						nueva.setCurso(contenido);
					if(qName.equals("profesor")) {
						nueva.setProfesor(contenido);					
					}
					if(qName.equals("asignatura")) {
						asignaturas.add(nueva);					
					}
				}
			};
			// Cuerpo de la función: trata de analizar el fichero deseado
			// Llamará a startElement(), endElement() y character()
			saxParser.parse(directorio + "/" + nombreArchivo, manejadorEventos);
			return asignaturas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void mostrarXmlDom(String rutaCompleta) {
		try {
			File inputFile = new File(rutaCompleta);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);  // Comprueba que es un XML valido
			doc.getDocumentElement().normalize();
			System.out.println("Elemento base : " + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("asignatura");
			System.out.println();
			System.out.println("Recorriendo asignaturas...");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Codigo: " + eElement.getAttribute("id"));
					System.out.println("Nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
					System.out.println(
							"Ciclo: " + eElement.getElementsByTagName("cicloFormativo").item(0).getTextContent());
					System.out.println("Curso: " + eElement.getElementsByTagName("curso").item(0).getTextContent());
					System.out
							.println("Profesor: " + eElement.getElementsByTagName("profesor").item(0).getTextContent());
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Asignatura> procesarXmlDom(String directorio, String nombreArchivo) {
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		try {
			File inputFile = new File(directorio + "/" + nombreArchivo);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);  // Comprueba que es un XML valido
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("asignatura");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					asignaturas.add(new Asignatura(eElement.getAttribute("id"),
							eElement.getElementsByTagName("nombre").item(0).getTextContent(),
							eElement.getElementsByTagName("cicloFormativo").item(0).getTextContent(),
							eElement.getElementsByTagName("curso").item(0).getTextContent(),
							eElement.getElementsByTagName("profesor").item(0).getTextContent()));
				}
			}
			return asignaturas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*public static List<Noticia> procesarMarcaDom(String cadena) {
		
	}*/

}
