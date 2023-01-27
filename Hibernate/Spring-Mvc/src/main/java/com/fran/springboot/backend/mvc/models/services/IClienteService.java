package com.fran.springboot.backend.mvc.models.services;

import java.util.List;

import com.fran.springboot.backend.mvc.models.entity.Cliente;

public interface IClienteService {
	
	// GET. Buscar todos y buscar uno
	public List<Cliente> findAll();
	public Cliente findById(Long id);
	// POST y PUT. Insertar y actualizar usan el mismo
	public Cliente save(Cliente cliente);
	// Delete. Borra un registro de la base de datos
	public void deleteById(Long id);
}
