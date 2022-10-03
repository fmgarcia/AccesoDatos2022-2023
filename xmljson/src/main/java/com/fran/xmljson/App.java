package com.fran.xmljson;

import com.fran.xmljson.utilidades.InternetUtils;
import com.fran.xmljson.utilidades.XmlUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*XmlUtils.procesarXmlSax("c:/ficheros", "asignaturas.xml").stream()
        	.forEach(a->System.out.println(a));*/
    	//XmlUtils.mostrarXmlDom("c:/ficheros/asignaturas.xml");
    	/*XmlUtils.procesarXmlDom("c:/ficheros", "asignaturas.xml").stream()
        	.forEach(a->System.out.println(a));*/
    	System.out.println(InternetUtils.readUrl("https://e00-marca.uecdn.es/rss/portada.xml"));
    }
}
