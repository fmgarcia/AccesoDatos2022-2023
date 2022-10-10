package com.fran.xmljson.entidades;

import java.util.Objects;

public class Film {
	
	String title;
	String url;
	
	public Film() {
		
	}

	public Film(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Film [title=" + title + ", url=" + url + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(url, other.url);
	}
	
	

}
