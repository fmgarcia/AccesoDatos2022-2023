package com.fran.xmljson.entidades;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class People2 {

	String name;
	String height;
	String mass;
	String hair_color;
	String skin_color;
	String eye_color;
	String birth_year;
	String gender;
	String homeworld;
	List<String> films;
	List<String> species;
	List<String> vehicles;
	List<String> starships;
	String created;
	String edited;
	String url;
	
	public People2() {
		
	}



	public People2(String name, String height, String mass, String hair_color, String skin_color, String eye_color,
			String birth_year, String gender, String homeworld, List<String> films, List<String> species,
			List<String> vehicles, List<String> starships, String created, String edited, String url) {
		super();
		this.name = name;
		this.height = height;
		this.mass = mass;
		this.hair_color = hair_color;
		this.skin_color = skin_color;
		this.eye_color = eye_color;
		this.birth_year = birth_year;
		this.gender = gender;
		this.homeworld = homeworld;
		this.films = films;
		this.species = species;
		this.vehicles = vehicles;
		this.starships = starships;
		this.created = created;
		this.edited = edited;
		this.url = url;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getHeight() {
		return height;
	}



	public void setHeight(String height) {
		this.height = height;
	}



	public String getMass() {
		return mass;
	}



	public void setMass(String mass) {
		this.mass = mass;
	}



	public String getHair_color() {
		return hair_color;
	}



	public void setHair_color(String hair_color) {
		this.hair_color = hair_color;
	}



	public String getSkin_color() {
		return skin_color;
	}



	public void setSkin_color(String skin_color) {
		this.skin_color = skin_color;
	}



	public String getEye_color() {
		return eye_color;
	}



	public void setEye_color(String eye_color) {
		this.eye_color = eye_color;
	}



	public String getBirth_year() {
		return birth_year;
	}



	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getHomeworld() {
		return homeworld;
	}



	public void setHomeworld(String homeworld) {
		this.homeworld = homeworld;
	}



	public List<String> getFilms() {
		return films;
	}



	public void setFilms(List<String> films) {
		this.films = films;
	}



	public List<String> getSpecies() {
		return species;
	}



	public void setSpecies(List<String> species) {
		this.species = species;
	}



	public List<String> getVehicles() {
		return vehicles;
	}



	public void setVehicles(List<String> vehicles) {
		this.vehicles = vehicles;
	}



	public List<String> getStarships() {
		return starships;
	}



	public void setStarships(List<String> starships) {
		this.starships = starships;
	}



	public String getCreated() {
		return created;
	}



	public void setCreated(String created) {
		this.created = created;
	}



	public String getEdited() {
		return edited;
	}



	public void setEdited(String edited) {
		this.edited = edited;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
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
		People2 other = (People2) obj;
		return Objects.equals(url, other.url);
	}



	@Override
	public String toString() {
		return "People2 [name=" + name + ", height=" + height + ", mass=" + mass + ", hair_color=" + hair_color
				+ ", skin_color=" + skin_color + ", eye_color=" + eye_color + ", birth_year=" + birth_year + ", gender="
				+ gender + ", homeworld=" + homeworld + ", films=" + films + ", species=" + species + ", vehicles="
				+ vehicles + ", starships=" + starships + ", created=" + created + ", edited=" + edited + ", url=" + url
				+ "]";
	}
	
	
	
}
