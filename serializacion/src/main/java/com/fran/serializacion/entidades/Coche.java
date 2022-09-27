package com.fran.serializacion.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Coche implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String matricula;
	String marca;
	String modelo;
	LocalDate fechaCompra;

	
	public Coche() {
		
	}

	public Coche(String matricula, String marca, String modelo, LocalDate fechaCompra) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.fechaCompra = fechaCompra;
	}
	
	public Coche(Coche c) {
		super();
		this.matricula = c.matricula;
		this.marca = c.marca;
		this.modelo = c.modelo;
		this.fechaCompra = c.fechaCompra;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", fechaCompra="
				+ fechaCompra + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return Objects.equals(matricula, other.matricula);
	}
	

}
