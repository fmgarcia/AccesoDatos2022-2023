package com.fran.springboot.backend.mvc.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	@Column(name="nombre", nullable=false)
	private String nombre;
	private String apellido;
	@Column(name="email", nullable=false, unique=true)
	private String email;
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private LocalDate createAt;
	
}
