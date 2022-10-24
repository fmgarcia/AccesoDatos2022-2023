package com.fran.jdbc;

import com.fran.jdbc.entidades.Tabla1;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Tabla1 t1 = new Tabla1();
        t1.setId(1);
        t1.setNombre("prueba");
        System.out.println(t1);
    }
}
