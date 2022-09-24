package tema1_ad19_20;

import java.io.Serializable;

public class Persona implements Serializable {

	String nombre;
	String mail;
	String fecha;
	
	public Persona() {
		
	}
	
	
	public Persona(String nombre, String mail, String fecha) {
		this.nombre = nombre;
		this.mail = mail;
		this.fecha = fecha;
	}

	/**
	 * Esta función obtiene el nombre de la persona
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", mail=" + mail + ", fecha=" + fecha + "]";
	}

	
	
}
