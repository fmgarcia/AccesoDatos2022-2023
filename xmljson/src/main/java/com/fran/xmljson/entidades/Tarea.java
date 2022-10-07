package com.fran.xmljson.entidades;

import java.util.Objects;

public class Tarea {
	
	private int id;
	private boolean completed;
	private String title;
	private int userId;

	public Tarea() {
		
	}

	public Tarea(int id, boolean completed, String title, int userId) {
		super();
		this.id = id;
		this.completed = completed;
		this.title = title;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Tarea [id=" + id + ", completed=" + completed + ", title=" + title + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarea other = (Tarea) obj;
		return id == other.id;
	}

}
