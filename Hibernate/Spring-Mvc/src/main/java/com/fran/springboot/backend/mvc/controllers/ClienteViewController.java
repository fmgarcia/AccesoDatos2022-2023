package com.fran.springboot.backend.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fran.springboot.backend.mvc.models.services.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteViewController {
	
	@Autowired
	IClienteService clienteService;
	
	@GetMapping("")
	public String indexClientes(Model model)
	{
		model.addAttribute("titulo", "Clientes");
		return "clientes/index";
	}
	
	@GetMapping("/listar")
	public String listarClientes(Model model)
	{
		model.addAttribute("titulo", "Listar clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "clientes/listar";
	}

}
