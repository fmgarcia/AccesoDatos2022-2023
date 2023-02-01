package com.fran.springboot.backend.eventos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fran.springboot.backend.eventos.entidades.DatosUsuario;

public interface IDatosUsuario extends JpaRepository<DatosUsuario,Integer> {

}
