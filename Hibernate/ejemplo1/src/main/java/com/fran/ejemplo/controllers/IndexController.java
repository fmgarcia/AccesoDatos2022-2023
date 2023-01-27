package com.fran.ejemplo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fran.ejemplo.entidades.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@GetMapping({"/index","","/","/home"})
	public String index(Model model) {
		model.addAttribute("titulo","Este es el título de la página");
		model.addAttribute("contenido","Este es el contenido de la página");
		return "index";
	}
	
	@GetMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario("Fran", "García","fran@iessanvicente.com");
		//Usuario usuario = new Usuario("Fran", "García");
		model.addAttribute("titulo","Perfil");
		model.addAttribute("usuario",usuario);
		return "perfil";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		/*List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("Fran", "García"));
		usuarios.add(new Usuario("Dani", "García","dani@ua.es"));
		usuarios.add(new Usuario("Paco", "García","paco@paco.com"));*/
		model.addAttribute("titulo","Listar");
		//model.addAttribute("usuarios",usuarios);
		return "listar";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("Fran", "García"));
		usuarios.add(new Usuario("Dani", "García","dani@ua.es"));
		usuarios.add(new Usuario("Paco", "García","paco@paco.com"));
		return usuarios;
	}
	

}
