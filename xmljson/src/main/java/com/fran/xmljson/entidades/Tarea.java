package com.fran.xmljson.entidades;

import java.util.Objects;

public class Tarea {
	
	private long id;
	private boolean completed;
	private String title;
	private long userId;

	public Tarea() {
		
	}

	public Tarea(long id, boolean completed, String title, long userId) {
		super();
		this.id = id;
		this.completed = completed;
		this.title = title;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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
