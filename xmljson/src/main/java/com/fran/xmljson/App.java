package com.fran.xmljson;

import com.fran.xmljson.utilidades.XmlUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        XmlUtils.procesarXml("c:/ficheros", "asignaturas.xml").stream()
        	.forEach(a->System.out.println(a));
    }
}
