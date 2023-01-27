package com.fran.springboot.backend.mvc.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fran.springboot.backend.mvc.models.entity.Cliente;
import com.fran.springboot.backend.mvc.models.services.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	/*@GetMapping("")
	public List<Cliente> findAll(){
		return clienteService.findAll();
	}*/
	
	@GetMapping("")
	public ResponseEntity<?> index(){
		
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Map<String,Object> response = new HashMap<>();
		
		try {
			listaClientes = clienteService.findAll();
		} catch (DataAccessException e) {  // Error al acceder a la base de datos
			response.put("mensaje", "Error al conectar con la base de datos");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Cliente>>(listaClientes,HttpStatus.OK);
	}
	
	/*@GetMapping("/{id}")
	public Cliente show(@PathVariable Long id){
		return clienteService.findById(id);
	}*/
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		
		Cliente cliente = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {  // Error al acceder a la base de datos
			response.put("mensaje", "Error al conectar con la base de datos");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(cliente==null) { // El id no existe
			response.put("mensaje", "El cliente con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		// El cliente existe
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente insertar(@RequestBody Cliente cliente) {
		cliente.setCreateAt(LocalDate.now());
		return clienteService.save(cliente);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void borrar(@PathVariable Long id) {
		clienteService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id){
		Cliente clienteActualizar = clienteService.findById(id);
		clienteActualizar.setNombre(cliente.getNombre());
		clienteActualizar.setApellido(cliente.getApellido());
		clienteActualizar.setEmail(cliente.getEmail());
		clienteService.save(clienteActualizar);
		return clienteActualizar;
	}
}
