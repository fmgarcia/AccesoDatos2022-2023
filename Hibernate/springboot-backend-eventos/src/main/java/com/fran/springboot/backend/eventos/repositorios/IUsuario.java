package com.fran.springboot.backend.eventos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fran.springboot.backend.eventos.entidades.Usuario;

public interface IUsuario extends JpaRepository<Usuario,Integer> {

}
