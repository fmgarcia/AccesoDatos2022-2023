package com.fran.xmljson.entidades;

public class Asignatura {

	String id;
	String nombre;
	String cicloFormativo;
	String curso;
	String profesor;
	
	public Asignatura() {
		
	}
	
	public Asignatura(String id) {
		this.id = id;
	}

	public Asignatura(String id, String nombre, String cicloFormativo, String curso, String profesor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cicloFormativo = cicloFormativo;
		this.curso = curso;
		this.profesor = profesor;
	}
	
	public Asignatura(Asignatura a) {
		super();
		this.id = a.id;
		this.nombre = a.nombre;
		this.cicloFormativo = a.cicloFormativo;
		this.curso = a.curso;
		this.profesor = a.profesor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCicloFormativo() {
		return cicloFormativo;
	}

	public void setCicloFormativo(String cicloFormativo) {
		this.cicloFormativo = cicloFormativo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Asignatura [id=" + id + ", nombre=" + nombre + ", cicloFormativo=" + cicloFormativo + ", curso=" + curso
				+ ", profesor=" + profesor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asignatura other = (Asignatura) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
