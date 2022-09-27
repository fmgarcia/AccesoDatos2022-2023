package com.fran.serializacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fran.serializacion.entidades.Coche;
import com.fran.serializacion.entidades.Persona;
import com.fran.serializacion.utilidades.SerializacionUtils;


public class App 
{
    public static void main( String[] args )
    {
    	List<Persona> personas = new ArrayList<Persona>();
        Persona fran = new Persona("111X","Fran");
        //SerializacionUtils.serializarPersona("c:/ficheros", "datospersonas.txt", fran);
        Persona fran2 = new Persona("222X","Fran2");
        //SerializacionUtils.serializarPersona("c:/ficheros", "datospersonas.txt", fran2);
        personas.add(fran);
        personas.add(fran2);
        //SerializacionUtils.serializarListaPersonas("c:/ficheros", "datospersonas.txt", personas);
        //System.out.println(SerializacionUtils.serializarListaObjetos("c:/ficheros", "datospersonas.dat", personas)?"Serialización correcta":"Fallo en la serialización");
        List<Coche> coches = new ArrayList<Coche>();
        Coche coche1= new Coche("1111AAA","Seat","Panda",LocalDate.now());
        Coche coche2= new Coche("2222BBB","BMW","Z3",LocalDate.now());
        coches.add(coche1);
        coches.add(coche2);
        //System.out.println(SerializacionUtils.serializarListaObjetos("c:/ficheros", "datoscoches.dat", coches)?"Serialización correcta":"Fallo en la serialización");
        // Serializo una persona y la desSerializo después y la imprimo
        /*SerializacionUtils.serializarPersona("c:/ficheros", "datospersonas.txt", fran);  // Serializo
        Persona nueva = SerializacionUtils.desSerializarPersona("c:/ficheros", "datospersonas.txt"); // DesSerializo
        System.out.println(nueva); // imprimo*/
        // Serializo una lista de personas y la desSerializo después y la imprimo
        /*SerializacionUtils.serializarListaPersonas("c:/ficheros", "datospersonas.txt", personas);  // Serializo lista
        List<Persona> listaNueva = SerializacionUtils.desSerializarListaPersonas("c:/ficheros", "datospersonas.txt"); // DesSerializo lista
        listaNueva.forEach(e->System.out.println(e));*/
        // Serializo una lista de personas y la desSerializo después y la imprimo con métodos genéricos
        SerializacionUtils.serializarListaObjetos("c:/ficheros", "datospersonas.txt", personas);  // Serializo lista genérica
        List<Persona> listaNueva = SerializacionUtils.desSerializarListaObjetos("c:/ficheros", "datospersonas.txt"); // DesSerializo lista genérica
        listaNueva.forEach(e->System.out.println(e));
        SerializacionUtils.serializarListaObjetos("c:/ficheros", "datoscoches.txt", coches);  // Serializo lista genérica
        List<Coche> listaCoches = SerializacionUtils.desSerializarListaObjetos("c:/ficheros", "datoscoches.txt"); // DesSerializo lista genérica
        listaCoches.forEach(e->System.out.println(e));
    }
    
}
