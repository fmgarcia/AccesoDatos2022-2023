package com.fran.programacionfuncional;

import java.util.ArrayList;
import java.util.List;

import com.fran.programacionfuncional.entidades.Usuario;

/**
 * Hello world!
 *
 */
public class App 
{
	static List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public static void tearUp() {
		usuarios.add(new Usuario(1,"Fran",25000.50));
		usuarios.add(new Usuario(2,"Dani",15000));
		usuarios.add(new Usuario(3,"Paco",-8000));
		usuarios.add(new Usuario(4,"Consu",51000));
		usuarios.add(new Usuario(1,"Francisco",2000));
		usuarios.add(new Usuario(5,"Pablo",30000));
		usuarios.add(new Usuario(6,"Javi",25000.50));
	}
	
	public static void tearDown() {
		usuarios.clear();
	}
	
    public static void main( String[] args )
    {
        
    }
}
