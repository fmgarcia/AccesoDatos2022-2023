package com.fran.xmljson.entidades;

import java.time.LocalDate;
import java.util.Objects;

public class Noticia {
	
	String title;
	String description;
	String guid;
	LocalDate pubDate;
	
	public Noticia() {
		
	}

	public Noticia(String title, String description, String guid, LocalDate pubDate) {
		super();
		this.title = title;
		this.description = description;
		this.guid = guid;
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public LocalDate getPubDate() {
		return pubDate;
	}

	public void setPubDate(LocalDate pubDate) {
		this.pubDate = pubDate;
	}

	@Override
	public String toString() {
		return "Noticia [title=" + title + ", description=" + description + ", guid=" + guid + ", pubDate=" + pubDate
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(guid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noticia other = (Noticia) obj;
		return Objects.equals(guid, other.guid);
	}

	
	
	
}
