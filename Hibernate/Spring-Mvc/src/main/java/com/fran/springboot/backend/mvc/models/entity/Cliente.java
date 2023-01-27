package com.fran.springboot.backend.mvc.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor  // Anotaciones Lombok
@Entity
@Table(name="clientes")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="el nombre no puede estar vacio")
	@Size(min=4, max=12, message="el nombre debe contener entre 4 y 12 caracteres")
	@Column(name="nombre", nullable=false)
	private String nombre;
	@JsonIgnore
	private String apellido;
	@NotEmpty(message="el email no puede estar vacio")
	@Email(message="La dirección de correo no está bien formada")
	@Column(name="email", nullable=false, unique=true)
	private String email;
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private LocalDate createAt;
		
}
